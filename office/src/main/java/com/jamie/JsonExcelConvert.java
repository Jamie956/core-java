package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JsonExcelConvert {
    public static void main(String[] args) throws Exception {
        //json2Excel
        JSONArray jsonArray = new JSONArray();
        JSONObject json1 = new JSONObject();
        json1.put("name", "tim");
        json1.put("age", "11");
        JSONObject json2 = new JSONObject();
        json2.put("name", "tom");
        json2.put("age", "21");
        jsonArray.add(json1);
        jsonArray.add(json2);

        json2Excel(jsonArray, "D:/test.xlsx");

        //excel2json
        excel2json("C:\\Users\\tgwzz\\Downloads\\heimao2.xls",
                "C:\\Users\\tgwzz\\Downloads\\heimao_output.txt");
    }

    /**
     * json array 转 execel
     */
    public static void json2Excel(JSONArray jsonArray, String destPath) throws IOException {
        try (FileOutputStream out = new FileOutputStream(new File(destPath));
             XSSFWorkbook wb = new XSSFWorkbook();
        ) {
            int rowNum = 0;
            int column = 0;
            List<JSONObject> list = jsonArray.toJavaList(JSONObject.class);

            Sheet sheet = wb.createSheet("sheet0");
            Row row = sheet.createRow(rowNum++);
            //标题
            Set<String> keys = list.get(0).keySet();
            for (String key : keys) {
                row.createCell(column++).setCellValue(key);
            }
            //每行json值 转excel 行
            column = 0;
            for (JSONObject json : list) {
                row = sheet.createRow(rowNum++);
                for (String key : keys) {
                    row.createCell(column++).setCellValue(json.getString(key));
                }
                column = 0;
            }
            wb.write(out);
        }
    }

    /**
     * excel文件 转成json 文件
     */
    public static void excel2json(String srcPath, String destPath) throws Exception {
        try (InputStream inputStream = new FileInputStream(srcPath);
             HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
             FileWriter fileWriter = new FileWriter(destPath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            //读取索引0的sheet
            HSSFSheet sheet = workbook.getSheetAt(0);
            //表头
            List<String> headers = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    for (Cell cell : row) {
                        String header = cell.getStringCellValue();
                        if (StringUtils.isNotBlank(header)) {
                            if (headers.contains(header)) {
                                throw new RuntimeException("存在重复表头");
                            }
                            headers.add(cell.getStringCellValue());
                        }
                    }
                    continue;
                }
                JSONObject json = new JSONObject();
                for (int i = 0; i < headers.size(); i++) {
                    String key = headers.get(i);
                    String value = row.getCell(i).getRichStringCellValue().toString();
                    json.put(key, value);
                }
                bufferedWriter.write(json.toJSONString() + "\r\n");
            }
            bufferedWriter.flush();
        }
    }

}
