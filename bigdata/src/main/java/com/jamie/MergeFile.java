package com.jamie;

import org.apache.hadoop.fs.*;
import org.junit.Test;

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
        FileSystem localFs = FileSystem.getLocal(HDFSUtilsNew.CONF);
        FSDataOutputStream out = localFs.create(new Path("src/main/resources/CompanyNotice.txt"));

        FileStatus[] inputFiles = fs.listStatus(new Path("/origin_data/ccr_qc/2021-04-06/CompanyNotice"));
        for (FileStatus fileStatus : inputFiles) {
            FSDataInputStream in = fs.open(fileStatus.getPath());

            byte[] buffer = new byte[256];

            int bytesRead = -1;

            while ((bytesRead = in.read(buffer)) > 0) {
                //合并文件
                out.write(buffer, 0, bytesRead);
            }
        }
        fs.close();
    }
}
