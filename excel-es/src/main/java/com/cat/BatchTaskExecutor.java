package com.cat;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Getter
public class BatchTaskExecutor {
    /**
     * 每一批提交数据的大小
     */
    private int batchSize;
    /**
     * 读取起始行
     */
    private int start;
    /**
     * 结束行，基0
     */
    private int end;
    /**
     * 总长度，包括头部
     */
    private int totalSize;
    /**
     * 数据实际长度
     */
    private int dataSize;
    /**
     * 批次数
     */
    private int round;

    /**
     * 初始化和计算实例的处理数据的各个指针
     *
     * @param batchSize 每一批提交数据的大小
     * @param start     读取起始行
     * @param end       结束行，基0
     */
    public BatchTaskExecutor(int batchSize, int start, int end) {
        this.batchSize = batchSize;
        this.start = start;
        this.end = end;
        this.totalSize = end + 1;
        this.dataSize = end - start + 1;
        this.round = (int) Math.ceil((double) dataSize / batchSize);

        log.info(String.format("创建BatchTaskHandle 处理批量任务：每批数据大小 [%s], 读取起始行 [%s], 结束行 [%s], 总行数 [%s], 数据行数 [%s], 执行批次数 [%s]",
                batchSize, start, end, totalSize, dataSize, round));
    }

    /**
     * 每一批数据的起始行
     */
    public int getStartRow(int i) {
        return start + (batchSize * i);
    }

    /**
     * 每一批数据的结束行
     */
    public int getEndRow(int i) {
        return (i == round - 1) ? totalSize : (getStartRow(i) + batchSize);
    }

    /**
     * 将每次读取的单行数据组成一批数据，提交一批数据进行操作，下一批数据同样的流程
     *
     * @param fetchData 根据索引获取一条数据
     * @param execution 提交一批数据的操作
     */
    public void doExecute(Function<Integer, JSONObject> fetchData, Consumer<List<JSONObject>> execution) {
        for (int i = 0; i < round; i++) {
            int startRow = getStartRow(i);
            int endRow = getEndRow(i);
            List<JSONObject> batchData = new ArrayList<>();
            for (int j = startRow; j < endRow; j++) {
                JSONObject rowJson = fetchData.apply(j);
                batchData.add(rowJson);
            }
            log.info("----------------------------------------------------");
            log.info(String.format("开始更新第 [%s] 批数据", i + 1));
            execution.accept(batchData);
        }
        log.info("全部更新完毕！");
    }
}
