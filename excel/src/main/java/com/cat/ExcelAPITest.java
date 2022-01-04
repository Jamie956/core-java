package com.cat;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelAPITest {
    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream(new File("D:/test.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook();

        //创建sheet
        Sheet sheet = workbook.createSheet("sheetName");
        //创建行
        Row row = sheet.createRow(0);
        //创建列
        Cell cell = row.createCell(1);
        //设置cell样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cell.setCellStyle(cellStyle);
        //设置cell内容
        cell.setCellValue("aa123");

        //设置字体
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontName("Verdana");
//        font.setColor(HSSFFont.COLOR_RED);
        //自定义颜色
        java.awt.Color rgb = translate("#ff9900");
        XSSFColor xssfColor = new XSSFColor(rgb, new DefaultIndexedColorMap());
        font.setColor(xssfColor);
        cellStyle.setFont(font);

        //设置背景颜色
//        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        cellStyle.setFillForegroundColor(xssfColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //边框样式
        cellStyle.setBorderBottom(BorderStyle.THIN);

        //设置列宽
        sheet.setColumnWidth(1, 10000);

        workbook.write(out);
        workbook.close();
        out.close();
    }

    public static java.awt.Color translate(String rgbStr) {
        if (rgbStr.startsWith("rgb")){
            Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher m = c.matcher(rgbStr);

            if (m.matches()) {
                return new java.awt.Color(Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)));
            }
        } else if(rgbStr.startsWith("#")) {
            return Color.decode(rgbStr);
        }
        return null;
    }
}
