package com.cat;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExcelWriteToElasticService {
    /**
     * ES 舆情索引名
     */
    private static final String INDEX = "public_opinion";
    private static final String TYPE = "index";
    /**
     * 每批数据的大小
     */
    private static final int BATCH_SIZE = 1000;

    private EsClient esClient;

    @Autowired
    public void setEsClient(EsClient esClient) {
        this.esClient = esClient;
    }

    /**
     * Poi Excel 数据 分批写入到Elasticsearch
     *
     * @param workbook 上传文件
     */
    public void excelBatchWriteToElastic(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        //excel 首行 header
        Row firstRow = sheet.getRow(0);
        //根据索引获取一行数据的 lambda 函数
        Function<Integer, JSONObject> fetchExcelRowData = j -> {
            Row row = sheet.getRow(j);
            return ExcelUtil.excelRowToJson(firstRow, row);
        };
        //对批量数据进行的批量操作 lambda 函数
        Consumer<List<JSONObject>> execution = excelRowData -> {
            try {
                checkExcelAndElasticValue(excelRowData);
                esClient.bulkUpdateInsert(INDEX, TYPE, excelRowData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        //实例化批量任务处理器，初始每批数据的大小，读取起始行和结束行
        BatchTaskExecutor batchTaskExecutor = new BatchTaskExecutor(BATCH_SIZE, 1, sheet.getLastRowNum());
        //组织批量数据和执行批量数据操作
        batchTaskExecutor.doExecute(fetchExcelRowData, execution);
    }

    /**
     * 检查Excel json 与 ES 文档 变更字段值
     */
    public void checkExcelAndElasticValue(List<JSONObject> batchData) throws IOException {
        List<String> ids = batchData.stream().map(e -> e.getString("id")).collect(Collectors.toList());
        String[] idsArr = ids.toArray(new String[]{});
        List<JSONObject> esExistDataList = esClient.idsQuery(INDEX, TYPE, idsArr, BATCH_SIZE);
        JsonUtil.checkListJsonValue(batchData, esExistDataList);
    }
}
