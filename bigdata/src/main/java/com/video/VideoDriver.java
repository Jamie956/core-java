package com.video;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class VideoDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String inputPath = "src/main/resources/input";
        String outputPath = "src/main/resources/output";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(VideoDriver.class);
        job.setMapperClass(VideoMapper.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.waitForCompletion(true);
    }
}
