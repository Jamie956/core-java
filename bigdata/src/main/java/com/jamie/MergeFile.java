package com.jamie;

import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @Author: Zjm
 * @Date: 2021/4/7 10:29
 */
public class MergeFile {

    /**
     * 将hdfs 文件夹CompanyNotice下的全部文件合并，合并成一个文件CompanyNotice.txt输出到本地
     * @throws Exception
     */
    @Test
    public void mergeFiles() throws Exception {
        FileSystem fs = HDFSUtilsNew.FILE_SYSTEM;
        FileStatus[] inputFiles = fs.listStatus(new Path("/origin_data/ccr_qc/2021-04-06/CompanyNotice"));

        FileSystem localFs = FileSystem.getLocal(HDFSUtilsNew.CONF);
        FSDataOutputStream out = localFs.create(new Path("src/main/resources/CompanyNotice.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        FSDataInputStream in = null;
        BufferedReader reader = null;
        for (FileStatus fileStatus : inputFiles) {
            in = fs.open(fileStatus.getPath());

            reader = new BufferedReader(new InputStreamReader(in));

            char[] buf = new char[1024];
            int len = -1;
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }
            writer.flush();
        }
//        fs.close();
        in.close();
        reader.close();
        out.close();
        writer.close();
    }
}
