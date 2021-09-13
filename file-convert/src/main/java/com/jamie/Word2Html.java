package com.jamie;


import org.apache.commons.text.StringEscapeUtils;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.*;

/**
 * word 转换成html
 */
public class Word2Html {

    /**
     * html 文件转义，输出字符串
     */
    public static void htmlFileUnescape2String(String htmlFile) {
        try (FileInputStream in = new FileInputStream(new File(htmlFile)); ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            while (true) {
                int len = in.read(buf);
                if (len != -1) {
                    bao.write(buf, 0, len);
                } else {
                    break;
                }
            }
            String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(bao.toString());
            System.out.println(unescapeHtml4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * html 文件转义，输出html文件
     */
    public static void htmlFileUnescape2File(String srcPath, String destPath) {
        try (FileInputStream in = new FileInputStream(new File(srcPath)); FileWriter out = new FileWriter(new File(destPath))) {
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int len = in.read(buf);
                if (len != -1) {
                    sb.append(new String(buf, 0, len));
                } else {
                    break;
                }
            }
            String unescapeHtml4 = StringEscapeUtils.unescapeHtml4(sb.toString());
            out.write(unescapeHtml4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void htmlFileUnescape2FileTest() {
        htmlFileUnescape2File("D:\\11d.html", "D:\\11d_cp.html");
    }

    /**
     * 2007word 转换成 html字符串
     */
    public static void word20072HtmlString(String filepath, String fileName) throws IOException {
        File wordFile = new File(filepath + fileName);
        if (!wordFile.exists()) {
            System.out.println("Sorry File does not Exists!");
            return;
        }
        if (wordFile.getName().endsWith(".docx") || wordFile.getName().endsWith(".DOCX")) {
            // 1) 加载word文档生成 XWPFDocument对象
            InputStream in = new FileInputStream(wordFile);
            XWPFDocument document = new XWPFDocument(in);
            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            File imageFolderFile = new File(filepath);
            XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
            options.setExtractor(new FileImageExtractor(imageFolderFile));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            //输出到数组流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XHTMLConverter.getInstance().convert(document, baos, options);
            String content = StringEscapeUtils.unescapeHtml4(baos.toString());
            System.out.println(content);
            baos.close();
        } else {
            System.out.println("Enter only MS Office 2007+ files");
        }
    }

    /**
     * 2007word 转换成 html file
     */
    public static void word20072HtmlFile(String filepath, String fileName, String htmlName) throws IOException {
        File wordFile = new File(filepath + fileName);
        if (!wordFile.exists()) {
            System.out.println("Sorry File does not Exists!");
            return;
        }
        if (wordFile.getName().endsWith(".docx") || wordFile.getName().endsWith(".DOCX")) {
            // 1) 加载word文档生成 XWPFDocument对象
            InputStream in = new FileInputStream(wordFile);
            XWPFDocument document = new XWPFDocument(in);
            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            File imageFolderFile = new File(filepath);
            XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
            options.setExtractor(new FileImageExtractor(imageFolderFile));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
            //输出到文件
            OutputStream out = new FileOutputStream(new File(filepath + htmlName));
            XHTMLConverter.getInstance().convert(document, out, options);
        } else {
            System.out.println("Enter only MS Office 2007+ files");
        }
    }


    @Test
    public void word20072HtmlFileTest() throws IOException {
        word20072HtmlFile("D:\\", "11.docx", "11d.html");
    }

}