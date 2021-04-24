package com.jamie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class NoReduceLogRunner  implements Tool {
    private Configuration conf = null;

    /**
     * 去除日志中字段长度小于等于11的日志
     */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/log", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new NoReduceLogRunner(), args);
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

        job.setJarByClass(NoReduceLogRunner.class);
        job.setMapperClass(LogMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 不进行reduce
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

    static  class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split(" ");
            if (fields.length < 11) {
                context.getCounter("map", "false").increment(1);
                return;
            }
            context.getCounter("map", "true").increment(1);
            k.set(line);
            context.write(k, NullWritable.get());
        }

    }


}
