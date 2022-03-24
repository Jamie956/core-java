package cat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class HDFSUtils {
//    private static final String HDFS_PATH = "hdfs://hadoop212:8020";
//    private static final String HDFS_USER = "hdfs";

    public static FileSystem FS;
    public static Configuration CONF;

    static {
        try {
            CONF = new Configuration();
            //本地
            FS = FileSystem.getLocal(CONF);
            //连服务器
//            FS = FileSystem.get(new URI(HDFS_PATH), CONF, HDFS_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        listFiles("D:\\ccr_qc_data\\del\\");
    }

    /**
     * 设置MR 输入路径，路径不存在就抛异常
     * @param job
     * @throws IOException
     */
    public static void initJobInputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        Path inputPath = new Path(conf.get("inputPath"));
        if (fs.exists(inputPath)) {
            FileInputFormat.setInputPaths(job, inputPath);
        } else {
            throw new RuntimeException("HDFS 目录不存在" + conf.get("inputPath"));
        }
    }

    /**
     * 设置MR 输出路径，路径存在就删除路径
     * @param job
     * @throws IOException
     */
    public static void initJobOutputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        Path outputPath = new Path(conf.get("outputPath"));
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);
    }

    /**
     * 从HDFS 下载文件
     * @param srcPath /origin_data/ccr_qc/2021-04-06/CompanyNotice
     * @param destPath src/main/resources/CompanyNotice
     * @throws Exception
     */
    public static void download(String srcPath, String destPath) throws Exception {
        /*
         * delSrc 下载完成后是否删除源文件,默认是true,即删除;
         * RawLocalFileSystem 是否用作本地文件系统,如果你在执行时候抛出NullPointerException异常,则代表你的文件系统与程序可能存在不兼容的情况(window下常见),此时可以将RawLocalFileSystem设置为true
         */
        FS.copyToLocalFile(false, new Path(srcPath), new Path(destPath), true);
    }

    /**
     * 将文件夹下的全部文件合并成一个文件输出
     * @param inPath 输入路径
     * @param outPath 输出路径
     * @throws Exception .
     */
    public static void mergeFiles(String inPath, String outPath) throws Exception {
        FileSystem localFs = FileSystem.getLocal(CONF);

        try (FSDataOutputStream fsDataOutputStream = localFs.create(new Path(outPath));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fsDataOutputStream))
        ) {
            FileStatus[] inputFiles = FS.listStatus(new Path(inPath));
            for (FileStatus file : inputFiles) {
                FSDataInputStream fsDataInputStream = FS.open(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream));

                char[] buf = new char[1024];
                while (true) {
                    int len = bufferedReader.read(buf);
                    if (len != -1) {
                        bufferedWriter.write(buf, 0, len);
                    } else {
                        break;
                    }
                }
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (FS != null) {
                FS.close();
            }
        }
    }

    /**
     * 创建目录,支持递归创建
     */
//    @Test
    public void mkDir() throws Exception {
        FS.mkdirs(new Path("/1215"));
    }

    /**
     * 创建具有指定权限的目录
     */
//    @Test
    public void mkDirWithPermission() throws Exception {
        FS.mkdirs(new Path("/1216"), new FsPermission(FsAction.READ_WRITE, FsAction.READ, FsAction.READ));
    }

    /**
     * 创建文件,并写入内容
     */
//    @Test
    public void create() throws Exception {
        FSDataOutputStream out = FS.create(new Path("/a.txt"), true, 1024);
        out.write("hello hadoop!".getBytes());
        out.write("hello spark!".getBytes());
        out.write("hello flink!".getBytes());

        out.flush();
        out.close();
    }

    /**
     * 判断文件是否存在
     */
//    @Test
    public void exist() throws Exception {
        boolean exists = FS.exists(new Path("/a.txt"));
        System.out.println(exists);
    }

    /**
     * 查看文件内容
     */
//    @Test
    public void readToString() throws Exception {
        FSDataInputStream fsDataInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            fsDataInputStream = FS.open(new Path("/a.txt"));
            inputStreamReader = new InputStreamReader(fsDataInputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();

            char[] buf = new char[1024];
            while (bufferedReader.read(buf) != -1) {
                sb.append(buf);
            }
            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(bufferedReader);
            IOUtils.closeStream(inputStreamReader);
            IOUtils.closeStream(fsDataInputStream);
        }

        FS.close();
    }

    /**
     * 文件重命名
     */
//    @Test
    public void rename() throws Exception {
        Path oldPath = new Path("/a.txt");
        Path newPath = new Path("/b.txt");
        boolean result = FS.rename(oldPath, newPath);
        System.out.println(result);
    }

    /**
     * 删除文件
     */
//    @Test
    public void delete() throws Exception {
        boolean result = FS.delete(new Path("/b.txt"), true);
        System.out.println(result);
    }

    /**
     * 上传文件到HDFS
     */
//    @Test
    public void copyFromLocalFile() throws Exception {
        Path src = new Path("/b.txt");
        Path dst = new Path("/sub/b.txt");
        FS.copyFromLocalFile(src, dst);
    }

    /**
     * 上传文件到HDFS?
     */
//    @Test
    public void copyFromLocalBigFile() throws Exception {
        File file = new File("/a.txt");
        final float fileSize = file.length();
        InputStream in = new BufferedInputStream(new FileInputStream(file));

        FSDataOutputStream out = FS.create(new Path("/b.txt"), new Progressable() {
            long fileCount = 0;

            @Override
            public void progress() {
                fileCount++;
                // progress方法每上传大约64KB的数据后就会被调用一次
                System.out.println("文件上传总进度：" + (fileCount * 64 * 1024 / fileSize) * 100 + " %");
            }
        });
        IOUtils.copyBytes(in, out, 1024);
    }

    /**
     * 递归查看指定目录下所有文件的信息
     */
//    @Test
    public static void listFiles(String path) {
        try {
            RemoteIterator<LocatedFileStatus> files = FS.listFiles(new Path(path), true);
            while (files.hasNext()) {
                System.out.println(files.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看文件块信息
     */
//    @Test
    public void getFileBlockLocations() throws Exception {
        FileStatus fileStatus = FS.getFileStatus(new Path("/a.txt"));
        BlockLocation[] blocks = FS.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        for (BlockLocation block : blocks) {
            System.out.println(block);
        }
    }

    /**
     * 流上传
     */
//    @Test
    public void putFileToHDFS() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("/a.txt"));
        FSDataOutputStream fsDataOutputStream = FS.create(new Path("/hi.txt"));

        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, CONF);
        IOUtils.closeStream(fsDataOutputStream);
        IOUtils.closeStream(fileInputStream);
    }

    /**
     * 从HDFS下载到本地
     */
//    @Test
    public void getFileFromHDFS() throws IOException, InterruptedException, URISyntaxException {
        FSDataInputStream fsDataInputStream = FS.open(new Path("/hi.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/hi2.txt"));

        IOUtils.copyBytes(fsDataInputStream, fileOutputStream, CONF);
        IOUtils.closeStream(fileOutputStream);
        IOUtils.closeStream(fsDataInputStream);
    }

    /**
     * 分块下载，下载第一块
     */
//    @Test
    public void readFileSeek1() throws IOException, InterruptedException, URISyntaxException {
        FSDataInputStream fis = FS.open(new Path("/hadoop-2.7.2.tar.gz"));
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part1"));

        byte[] buf = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {
            fis.read(buf);
            fos.write(buf);
        }

        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }

    /**
     * 分块下载，下载第二块
     */
//    @Test
    public void readFileSeek2() throws IOException, InterruptedException, URISyntaxException {
        FSDataInputStream fis = FS.open(new Path("/hadoop-2.7.2.tar.gz"));
        // 设置指定读取的起点
        fis.seek(1024 * 1024 * 128);
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-2.7.2.tar.gz.part2"));

        IOUtils.copyBytes(fis, fos, CONF);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
}
