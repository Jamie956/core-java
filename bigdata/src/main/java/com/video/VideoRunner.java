package com.video;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class VideoRunner implements Tool {
    private Configuration conf = null;

    public static void main(String[] args) {
        args = new String[]{"/Users/jamie/project/java-core/bigdata/src/main/resources/vedio",
                "/Users/jamie/project/java-core/bigdata/src/main/resources/output"};

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

        initJobInputPath(job);
        initJobOutputPath(job);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    private void initJobInputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        Path inputPath = new Path(conf.get("inputPath"));
        if (fs.exists(inputPath)) {
            FileInputFormat.setInputPaths(job, inputPath);
        } else {
            throw new RuntimeException("HDFS 目录不存在" + conf.get("inputPath"));
        }
    }

    private void initJobOutputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        FileSystem fs = FileSystem.get(conf);
        Path outputPath = new Path(conf.get("outputPath"));
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
        }
        FileOutputFormat.setOutputPath(job, outputPath);
    }

    @Override
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }
}
