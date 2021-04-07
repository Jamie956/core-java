package com.jamie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSUtilsNew {
    private static final String HDFS_PATH = "hdfs://hadoop212:8020";
    private static final String HDFS_USER = "hdfs";
    public static FileSystem FILE_SYSTEM;
    public static Configuration CONF;

    static {
        try {
            CONF = new Configuration();
            FILE_SYSTEM = FileSystem.get(new URI(HDFS_PATH), CONF, HDFS_USER);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


//
//    /**
//     * 创建目录,支持递归创建
//     */
//    @Test
//    public void mkDir() throws Exception {
//        fileSystem.mkdirs(new Path("/1215"));
//    }
//
//    /**
//     * 创建具有指定权限的目录
//     */
//    @Test
//    public void mkDirWithPermission() throws Exception {
//        fileSystem.mkdirs(new Path("/1216"), new FsPermission(FsAction.READ_WRITE, FsAction.READ, FsAction.READ));
//    }
//
//    /**
//     * 创建文件,并写入内容
//     */
//    @Test
//    public void create() throws Exception {
//        // 如果文件存在，默认会覆盖, 可以通过第二个参数进行控制。第三个参数可以控制使用缓冲区的大小
//        FSDataOutputStream out = fileSystem.create(new Path("/a.txt"), true, 4096);
//        out.write("hello hadoop!".getBytes());
//        out.write("hello spark!".getBytes());
//        out.write("hello flink!".getBytes());
//
//        out.flush();
//        out.close();
//    }
//
//    /**
//     * 判断文件是否存在
//     */
//    @Test
//    public void exist() throws Exception {
//        boolean exists = fileSystem.exists(new Path("/a.txt"));
//        System.out.println(exists);
//    }
//
//    /**
//     * 查看文件内容
//     */
//    @Test
//    public void readToString() throws Exception {
//        FSDataInputStream inputStream = fileSystem.open(new Path("/origin_data/ccr_qc/company_list/000000_0"));
//        String context = inputStreamToString(inputStream, "utf-8");
//        System.out.println(context);
//    }
//
//    /**
//     * 文件重命名
//     */
//    @Test
//    public void rename() throws Exception {
//        Path oldPath = new Path("/a.txt");
//        Path newPath = new Path("/b.txt");
//        boolean result = fileSystem.rename(oldPath, newPath);
//        System.out.println(result);
//    }
//
//    /**
//     * 删除文件
//     */
//    @Test
//    public void delete() throws Exception {
//        /*
//         *  第二个参数代表是否递归删除
//         *    +  如果path是一个目录且递归删除为true, 则删除该目录及其中所有文件;
//         *    +  如果path是一个目录但递归删除为false,则会则抛出异常。
//         */
//        boolean result = fileSystem.delete(new Path("/b.txt"), true);
//        System.out.println(result);
//    }
//
//    /**
//     * 上传文件到HDFS
//     */
//    @Test
//    public void copyFromLocalFile() throws Exception {
//        Path src = new Path("src/main/resources/hello.txt");
//        Path dst = new Path("/wordcount/hello.txt");
//        fileSystem.copyFromLocalFile(src, dst);
//    }
//
//    /**
//     * 上传文件到HDFS
//     */
//    @Test
//    public void copyFromLocalBigFile() throws Exception {
//        File file = new File("D:\\kafka.tgz");
//        final float fileSize = file.length();
//        InputStream in = new BufferedInputStream(new FileInputStream(file));
//
//        FSDataOutputStream out = fileSystem.create(new Path("/kafka5.tgz"), new Progressable() {
//            long fileCount = 0;
//            @Override
//            public void progress() {
//                fileCount++;
//                // progress方法每上传大约64KB的数据后就会被调用一次
//                System.out.println("文件上传总进度：" + (fileCount * 64 * 1024 / fileSize) * 100 + " %");
//            }
//        });
//        IOUtils.copyBytes(in, out, 4096);
//    }
//
//    /**
//     * 从HDFS上下载文件
//     */
//    @Test
//    public void copyToLocalFile() throws Exception {
//        Path src = new Path("/hi.txt");
//        Path dst = new Path("src/main/resources/download-hi.txt");
//        /*
//         * 第一个参数控制下载完成后是否删除源文件,默认是true,即删除;
//         * 最后一个参数表示是否将RawLocalFileSystem用作本地文件系统;
//         * RawLocalFileSystem默认为false,通常情况下可以不设置,
//         * 但如果你在执行时候抛出NullPointerException异常,则代表你的文件系统与程序可能存在不兼容的情况(window下常见),
//         * 此时可以将RawLocalFileSystem设置为true
//         */
//        fileSystem.copyToLocalFile(false, src, dst, true);
//    }
//

//
//    /**
//     * 递归查看指定目录下所有文件的信息
//     */
//    @Test
//    public void listFilesRecursive() throws Exception {
//        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/origin_data"), true);
//        while (files.hasNext()) {
//            System.out.println(files.next());
//        }
//    }
//
//    /**
//     * 查看文件块信息
//     */
//    @Test
//    public void getFileBlockLocations() throws Exception {
//        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/hi.txt"));
//        BlockLocation[] blocks = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
//        for (BlockLocation block : blocks) {
//            System.out.println(block);
//        }
//    }
//
//    /**
//     * 把输入流转换为指定编码的字符
//     *
//     * @param inputStream 输入流
//     * @param encode      指定编码类型
//     */
//    private static String inputStreamToString(InputStream inputStream, String encode) {
//        try {
//            if (encode == null || ("".equals(encode))) {
//                encode = "utf-8";
//            }
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encode));
//            StringBuilder builder = new StringBuilder();
//            String str = "";
//            while ((str = reader.readLine()) != null) {
//                builder.append(str).append("\n");
//            }
//            return builder.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // 流上传
//    @Test
//    public void putFileToHDFS() throws IOException {
//        FileInputStream fis = new FileInputStream(new File("src/main/resources/hi.txt"));
//        FSDataOutputStream fos = fileSystem.create(new Path("/hi.txt"));
//
//        IOUtils.copyBytes(fis, fos, configuration);
//        IOUtils.closeStream(fos);
//        IOUtils.closeStream(fis);
//    }
//
//    // 从HDFS下载到本地
//    @Test
//    public void getFileFromHDFS() throws IOException, InterruptedException, URISyntaxException {
//        FSDataInputStream fis = fileSystem.open(new Path("/hi.txt"));
//        FileOutputStream fos = new FileOutputStream(new File("src/main/resources/download-hi.txt"));
//
//        IOUtils.copyBytes(fis, fos, configuration);
//        IOUtils.closeStream(fos);
//        IOUtils.closeStream(fis);
//    }
//
//    // 分块下载，下载第一块
//    @Test
//    public void readFileSeek1() throws IOException, InterruptedException, URISyntaxException {
//        FSDataInputStream fis = fileSystem.open(new Path("/hadoop-2.7.2.tar.gz"));
//        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part1"));
//
//        byte[] buf = new byte[1024];
//        for (int i = 0; i < 1024 * 128; i++) {
//            fis.read(buf);
//            fos.write(buf);
//        }
//
//        IOUtils.closeStream(fos);
//        IOUtils.closeStream(fis);
//    }
//
//    // 分块下载，下载第二块
//    @Test
//    public void readFileSeek2() throws IOException, InterruptedException, URISyntaxException {
//        FSDataInputStream fis = fileSystem.open(new Path("/hadoop-2.7.2.tar.gz"));
//        // 设置指定读取的起点
//        fis.seek(1024 * 1024 * 128);
//        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part2"));
//
//        IOUtils.copyBytes(fis, fos, configuration);
//        IOUtils.closeStream(fos);
//        IOUtils.closeStream(fis);
//    }
}
