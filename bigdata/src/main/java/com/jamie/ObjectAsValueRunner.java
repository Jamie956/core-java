package com.jamie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ObjectAsValueRunner implements Tool {
    private Configuration conf = null;

    /*
计算同一个手机号码的总流量

136	1
136	1
137	2
137	3
138	2
139	1
140	2

预期
136	2...
137	5...
138	2...
139	1...
140	2...
 */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/phone", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new ObjectAsValueRunner(), args);
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

        job.setJarByClass(ObjectAsValueRunner.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Counter.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Counter.class);

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

    static class FlowCountMapper extends Mapper<LongWritable, Text, Text, Counter> {
        Text k = new Text();
        Counter v = new Counter();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");

            String phone = fields[0];
            int count = Integer.parseInt(fields[1]);

            k.set(phone);
            v.setCount(count);
            //写出 key 电话号码，value 对象，将电话号码一样的对象聚集在一起
            context.write(k, v);
        }
    }

    static class FlowCountReducer extends Reducer<Text, Counter, Text, Counter> {
        Counter v = new Counter();

        @Override
        protected void reduce(Text key, Iterable<Counter> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (Counter value : values) {
                sum += value.getCount();
            }
            v.setCount(sum);

            //写出 key 电话号码，value 对象
            context.write(key, v);
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Counter implements WritableComparable<Counter> {
        private int count;

        // 序列化方法
        @Override
        public void write(DataOutput out) throws IOException {
            out.writeInt(count);
        }

        // 反序列化方法
        @Override
        public void readFields(DataInput in) throws IOException {
            // 必须要求和序列化方法顺序一致
            count = in.readInt();
        }

        @Override
        public int compareTo(Counter bean) {
            if (this.count > bean.getCount()) {
                return -1;
            } else if (this.count < bean.getCount()) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return count + "...";
        }
    }
}
