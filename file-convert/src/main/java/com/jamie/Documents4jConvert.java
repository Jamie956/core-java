package com.jamie;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.*;

/**
 * documents4j
 */
public class Documents4jConvert {

    public static void main(String[] args) {
        docxToPdf("D:\\aa.docx", "D:\\bb.pdf");
    }

    public static void docxToPdf(String wordFilePath, String pdfFilePath) {
        try (InputStream inputStream = new FileInputStream(new File(wordFilePath));
             OutputStream outputStream = new FileOutputStream(new File(pdfFilePath));) {

            IConverter converter = LocalConverter.builder().build();
            boolean ret = converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();

            if (ret) {
                System.out.println("转换成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}