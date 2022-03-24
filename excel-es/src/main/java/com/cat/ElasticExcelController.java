package com.cat;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author zjm
 */
@RestController
public class ElasticExcelController {
    private ExcelWriteToElasticService excelBatchWriteToElastic;

    @Autowired
    public void setExcelBatchWriteToElastic(ExcelWriteToElasticService excelBatchWriteToElastic) {
        this.excelBatchWriteToElastic = excelBatchWriteToElastic;
    }

    /**
     * 上传Excel 接口
     * <p>
     * 使用 Postman 上传文件
     * curl --location --request POST 'localhost:8844/up' \
     * --header 'Content-Type: multipart/form-data' \
     * --form 'file=@"/C:/Users/tgwzz/Desktop/22.xls"'
     */
    @PostMapping("up")
    public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        Workbook workbook = ExcelUtil.checkAndCreateWorkbook(file);
        excelBatchWriteToElastic.excelBatchWriteToElastic(workbook);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
