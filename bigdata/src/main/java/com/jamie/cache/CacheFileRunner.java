package com.jamie.cache;

import com.jamie.HDFSUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CacheFileRunner implements Tool {
    private Configuration conf = null;

    /*
    缓存文件
order
1001	1
1002	2
1003	3
1004	1
1005	2
1006	3

product
1	小米
2	华为
3	格力

预期
1001	1	小米
1002	2	华为
1003	3	格力
1004	1	小米
1005	2	华为
1006	3	格力
     */
//    @Test
//    public void cacheFile() throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
//        String cacheFilePath = "src/main/resources/product";
//        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
//        Job job = initJob(Driver.class, DistributedCacheMapper.class, Text.class, NullWritable.class, "/order");
//        // 添加缓存文件
//        job.addCacheFile(new URI(cacheFilePath));
//        boolean result = job.waitForCompletion(true);
//        System.exit(result ? 0 : 1);
//    }

    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/order", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new CacheFileRunner(), args);
            if (code == 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        conf = this.getConf();
        conf.set("inputPath", args[0]);
        conf.set("outputPath", args[1]);

        Job job = Job.getInstance(conf);

        job.setJarByClass(CacheFileRunner.class);
        job.setMapperClass(DistributedCacheMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 不进行reduce
        job.setNumReduceTasks(0);
        // 添加缓存文件
        String cacheFilePath = "bigdata/src/main/resources/product";
        job.addCacheFile(new URI(cacheFilePath));
        HDFSUtils.initJobInputPath(job);
        HDFSUtils.initJobOutputPath(job);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;

    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }


    static class DistributedCacheMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        HashMap<String, String> cacheMap = new HashMap<>();
        Text k = new Text();

        /**
         * 加载缓存文件
         */
        @Override
        protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException {
            // 加载cache 文件product
            URI[] cacheFiles = context.getCacheFiles();
            String path = cacheFiles[0].getPath();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            String line;
            while (StringUtils.isNotEmpty(line = reader.readLine())) {
                String[] fields = line.split("\t");

                String productId = fields[0];
                String productName = fields[1];
                cacheMap.put(productId, productName);
            }

            // 关闭资源
            IOUtils.closeStream(reader);
        }

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            //读取order
            String line = value.toString();
            String productId = line.split("\t")[1];
            String productName = cacheMap.get(productId);
            k.set(line + "\t" + productName);

            context.write(k, NullWritable.get());
        }
    }


}
