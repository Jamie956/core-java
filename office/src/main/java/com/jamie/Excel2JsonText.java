package com.jamie;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Iterator;

/**
 * 将excel 转成json 文件
 * @author ZJM
 * @date 2021/8/6 18:08
 */
public class Excel2JsonText {

    /**
     * 读取一个excel文件的内容
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        excel2json("C:\\Users\\tgwzz\\Downloads\\heimao2.xls", "C:\\Users\\tgwzz\\Downloads\\heimao_output.txt");
    }
    
    //通过对单元格遍历的形式来获取信息 ，这里要判断单元格的类型才可以取出值
    public static void readTable() throws Exception {
        InputStream ips = new FileInputStream("C:\\Users\\tgwzz\\Downloads\\heimao2.xls");
        HSSFWorkbook wb = new HSSFWorkbook(ips);
        HSSFSheet sheet = wb.getSheetAt(0);
        for (Iterator ite = sheet.rowIterator(); ite.hasNext(); ) {
            HSSFRow row = (HSSFRow) ite.next();
            row.getCell(0);
            for (Iterator itet = row.cellIterator(); itet.hasNext(); ) {
                HSSFCell cell = (HSSFCell) itet.next();
                String cellData = cell.getRichStringCellValue().toString();
                System.out.println(cellData);
            }
        }
    }

    /**
     * 读取excel, 把每行的第一、二列转成一个一行json, 写到文件
     */
    public static void excel2json(String srcPath, String destPath) throws Exception {
        try (InputStream ips = new FileInputStream(srcPath);
             HSSFWorkbook wb = new HSSFWorkbook(ips);
             FileWriter fw = new FileWriter(destPath);
             BufferedWriter bw = new BufferedWriter(fw)) {

            HSSFSheet sheet = wb.getSheetAt(0);
            for (Iterator<Row> ite = sheet.rowIterator(); ite.hasNext();) {
                HSSFRow row = (HSSFRow) ite.next();
                if (row.getCell(0) != null && row.getCell(1) != null) {
                    String id = row.getCell(0).getRichStringCellValue().toString();
                    String content = row.getCell(1).getRichStringCellValue().toString();
                    content = content.replaceAll("\n|\r", "<br>");
                    JSONObject line = new JSONObject();
                    line.put("id", id);
                    line.put("content", content);
                    bw.write(line + "\r\n");
                } else {
                    System.out.println(1);
                }
            }
            bw.flush();
        }
    }

}
