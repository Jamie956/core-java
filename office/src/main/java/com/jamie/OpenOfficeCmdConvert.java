package com.jamie;

import java.io.*;

/**
 * LibreOffice
 */
public class OpenOfficeCmdConvert {

    //需要安装 LibreOffice
    private String sofficeDir = "D:\\software\\LibreOffice\\program";

    public void word2pdf(String inPath, String outPath) throws Exception {
        if (!new File(inPath).exists()) {
            throw new FileNotFoundException();
        }
        String command = String.format("%s\\soffice --convert-to pdf:writer_pdf_Export %s --outdir %s", sofficeDir, inPath, outPath);
        String output = this.executeCommand(command);
        System.out.println("command=" + command);
        System.out.println("output=" + output);
    }

    protected String executeCommand(String command) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();
        Process p;
        p = Runtime.getRuntime().exec(command);
        p.waitFor();
        try (InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream(), "UTF-8");
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        p.destroy();
        return output.toString();
    }

    public static void main(String[] args) throws Exception {
        new OpenOfficeCmdConvert().word2pdf("D:\\aa.docx", "D:\\temp");
    }
}
