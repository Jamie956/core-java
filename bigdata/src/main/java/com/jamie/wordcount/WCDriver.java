package com.jamie.wordcount;

import com.jamie.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.jamie.Utils.initJob;

/**
 * @Author: Zjm
 * @Date: 2021/4/7 18:51
 */
public class WCDriver {
    public static final String OUTPUT_PATH = "src/main/resources/out";


    /*
    词频统计
    a b c
    v b
    a d a

    预期
    a	3
    b	2
    c	1
    d	1
    v	1
     */
    @Test
    public void wordCount() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Job job = initJob(Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, "/words");
        job.waitForCompletion(true);
    }


    static class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        Text k = new Text();
        IntWritable v = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] words = line.split(" ");
            for (String word : words) {
                k.set(word);
                context.write(k, v);
            }
        }
    }

    static class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        IntWritable v = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            v.set(sum);
            context.write(key, v);
        }
    }
}

