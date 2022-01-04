package com.cat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Set;

public class JsonExcelConvert {
    /**
     * Json array 转 Excel
     * @param jsonArray Json array 数据
     * @param destPath Excel 输出路径
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
     * Excel文件 转成 Json文件
     * @param srcPath Excel 文件 输入路径
     * @param destPath Json 文件 输出路径
     */
    public static void excel2json(String srcPath, String destPath) throws Exception {
        try (InputStream inputStream = new FileInputStream(srcPath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
             FileWriter fileWriter = new FileWriter(destPath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(0);

            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                JSONObject json = excelRowToJson(firstRow, row);
                bufferedWriter.write(json.toJSONString() + "\r\n");
            }
            bufferedWriter.flush();
        }
    }

    /**
     * Excel文件 转成 Json Array
     * @param srcPath Excel 文件 输入路径
     */
    public static JSONArray excel2jsonFile(String srcPath) throws Exception {
        try (InputStream inputStream = new FileInputStream(srcPath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        ) {
            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(0);

            JSONArray items = new JSONArray();
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
                Row row = sheet.getRow(i);
                JSONObject item = excelRowToJson(firstRow, row);
                items.add(item);
            }
            return items;
        }
    }

    /**
     * 将excel 的一行数据转成json
     */
    private static JSONObject excelRowToJson(Row headers, Row row) {
        JSONObject json = new JSONObject();

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            String header = headers.getCell(i).getStringCellValue();
            if (cell != null) {
                CellType cellType = cell.getCellType();
                if (cellType.equals(CellType.NUMERIC)) {
                    json.put(header, cell.getNumericCellValue());
                }
                if (cellType.equals(CellType.STRING)) {
                    json.put(header, cell.getRichStringCellValue().toString());
                }
            }
        }
        return json;
    }
}