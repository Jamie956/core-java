package com.jamie.topn;

import com.jamie.HDFSUtils;
import com.jamie.flowsum.Counter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;
import java.util.TreeMap;

public class MrCleanUpSortRunner implements Tool {
    private Configuration conf = null;

    /*
输出前3，map 排序，reduce 排序

map cleanup, reduce cleanup
java 1
html 2
php 1
spring 6
python 4
ruby 3

预期
spring	6...
python	4...
ruby	3...

     */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/top10", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new MrCleanUpSortRunner(), args);
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

        job.setJarByClass(MrCleanUpSortRunner.class);
        job.setMapperClass(OrderCleanUpMapper.class);
        job.setReducerClass(OrderCleanUpReducer.class);

        job.setMapOutputKeyClass(Counter.class);
        job.setMapOutputValueClass(Text.class);

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


    static class OrderCleanUpMapper extends Mapper<LongWritable, Text, Counter, Text> {
        // 排序容器
        TreeMap<Counter, String> flowMap = new TreeMap<>();
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) {
            String[] fields = value.toString().split(" ");

            String name = fields[0];
            int count = Integer.parseInt(fields[1]);
            Counter bean = new Counter(count);

            //由对象 compareTo 实现排序
            flowMap.put(bean, name);

            //控制 TreeMap 的大小，超过10条就删除掉最小的一条数据
            if (flowMap.size() > 3) {
                flowMap.remove(flowMap.lastKey());
            }
        }

        //cleanup 最后执行写出
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            for (Counter bean : flowMap.keySet()) {
                String name = flowMap.get(bean);
                v.set(name);
                context.write(bean, v);
            }
        }
    }


    static class OrderCleanUpReducer extends Reducer<Counter, Text, Text, Counter> {
        // 排序容器
        TreeMap<Counter, Text> flowMap = new TreeMap<>();
        Text k = new Text();

        @Override
        protected void reduce(Counter key, Iterable<Text> values, Context context) {
            Text name = values.iterator().next();
            Counter bean = new Counter(key.getCount());
            flowMap.put(bean, new Text(name));

            //对象排序，取前3名
            if (flowMap.size() > 3) {
                flowMap.remove(flowMap.lastKey());
            }
        }

        //最后 写出排序后的map
        @Override
        protected void cleanup(Reducer<Counter, Text, Text, Counter>.Context context) throws IOException, InterruptedException {
            for (Counter bean : flowMap.keySet()) {
                k.set(flowMap.get(bean));
                context.write(k, bean);
            }
        }
    }
}
