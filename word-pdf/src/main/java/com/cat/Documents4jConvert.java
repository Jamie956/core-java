package com.cat;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.junit.Test;

import java.io.*;

/**
 * documents4j
 */
public class Documents4jConvert {

    @Test
    public void docxToPdfTest() {
        word2Pdf("D:\\aa.docx", "D:\\bb.pdf");
    }

    /**
     * 使用 documents4j
     */
    public static void word2Pdf(String wordFilePath, String pdfFilePath) {
        try (InputStream inputStream = new FileInputStream(new File(wordFilePath));
             OutputStream outputStream = new FileOutputStream(new File(pdfFilePath));) {
            IConverter converter = LocalConverter.builder().build();
            converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}