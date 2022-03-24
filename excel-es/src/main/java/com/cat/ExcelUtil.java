package com.cat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class ExcelUtil {
    private static final String EXCEL2007 = "xlsx";
    private static final String EXCEL2003 = "xls";

    /**
     * 将Excel 的一行数据转成json
     *
     * @param headers Excel 首行 Header
     * @param row     数据行
     * @return Json 格式的行数据
     */
    public static JSONObject excelRowToJson(Row headers, Row row) {
        JSONObject json = new JSONObject();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            String header = headers.getCell(i).getStringCellValue();
            if (cell != null) {
                CellType cellType = cell.getCellType();
                if (cellType.equals(CellType.NUMERIC)) {
                    json.put(header, cell.getNumericCellValue());
                } else if (cellType.equals(CellType.STRING)) {
                    String value = cell.getRichStringCellValue().toString();
                    if (StringUtils.isNotBlank(value)) {
                        Object o = checkAndConvertStrToJson(value);
                        json.put(header, o);
                    }
                } else if (cellType.equals(CellType.BLANK)) {
                    //空cell 不做处理
                } else {
                    throw new RuntimeException(String.format("不支持解析cellType：[%s]", cellType));
                }
            }
        }
        return json;
    }

    /**
     * 检查上传 Excel 的扩展名，根据扩展名实例化不同的 Poi Workbook
     * 扩展名为 xlsx 时使用 XSSFWorkbook
     * 扩展名为 xls 时使用 HSSFWorkbook
     *
     * @param file 上传文件
     * @return Poi Workbook
     */
    public static Workbook checkAndCreateWorkbook(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileExt = "";
        if (StringUtils.isNotBlank(fileName)) {
            fileExt = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        InputStream inputStream = file.getInputStream();
        Workbook workbook;
        if (EXCEL2007.equals(fileExt)) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (EXCEL2003.equals(fileExt)) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new RuntimeException(String.format("不支持扩展名 [%s]", fileExt));
        }
        return workbook;
    }

    /**
     * 字符串转换成 json object 或者json array 或者字符串
     */
    public static Object checkAndConvertStrToJson(String jsonStr) {
        Object object;
        try {
            object = JSON.parse(jsonStr);
        } catch (Exception e) {
            return jsonStr;
        }
        return object;
    }
}