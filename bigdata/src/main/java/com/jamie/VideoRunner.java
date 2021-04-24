package com.jamie;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class VideoRunner implements Tool {
    private Configuration conf = null;

    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/video",
                "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new VideoRunner(), args);
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

        job.setJarByClass(VideoRunner.class);
        job.setMapperClass(VideoMapper.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(0);

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

    static class VideoMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String lineEtl = lineETL(line);

            if (StringUtils.isBlank(lineEtl)) {
                return;
            }
            v.set(lineEtl);
            context.write(NullWritable.get(), v);
        }
    }

    public static String lineETL(String org) {
        String[] items = org.split("\t");
        if (items.length < 9) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        //去除空格 a & b -> a&b
        items[3] = items[3].replace(" ", "");
        for (int i = 0; i < items.length; i++) {
            if (i < 9) {
                //前面9个以\t分隔
                if (i == items.length - 1) {
                    sb.append(items[i]);
                } else {
                    sb.append(items[i]).append("\t");
                }
            } else {
                //处理相关id，以&连接
                if (i == items.length - 1) {
                    sb.append(items[i]);
                } else {
                    sb.append(items[i]).append("&");
                }
            }
        }

        return sb.toString();
    }
}
