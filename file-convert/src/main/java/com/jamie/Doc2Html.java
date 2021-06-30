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
public class Doc2Html {

    @Test
    public void htmlFilesUnescape() {
        String filePath = "C:\\Users\\tgwzz\\Downloads\\ir\\laws\\605aea72c4bf9cb3841dae0a.html";
        try (FileInputStream in = new FileInputStream(new File(filePath)); ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
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
     * 2007版本word转换成html
     *
     * @throws IOException
     */
    public static void Word2007ToHtml(String filepath, String fileName, String htmlName) throws IOException {
        final String file = filepath + fileName;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) 加载word文档生成 XWPFDocument对象
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
                File imageFolderFile = new File(filepath);
                XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                options.setIgnoreStylesIfUnused(false);
                options.setFragment(true);

                //输出到文件
//                OutputStream out = new FileOutputStream(new File(filepath + htmlName));
//                XHTMLConverter.getInstance().convert(document, out, options);

                //输出到数组流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                XHTMLConverter.getInstance().convert(document, baos, options);
                String content = baos.toString();

                content = StringEscapeUtils.unescapeHtml4(content);
                System.out.println(content);
                baos.close();
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Word2007ToHtml("C:\\Users\\tgwzz\\Downloads\\ir\\laws\\", "605aea3ec8c76d4d318d9d0b.docx", "605aea3ec8c76d4d318d9d0b.html");
    }

}