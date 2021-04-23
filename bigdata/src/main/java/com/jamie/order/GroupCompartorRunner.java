package com.jamie.order;

import com.jamie.Driver;
import com.jamie.HDFSUtils;
import com.jamie.wordcount.WordCountRunner;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.junit.Test;
import sun.util.resources.cldr.uk.CurrencyNames_uk;

import java.io.File;
import java.io.IOException;

import static com.jamie.Utils.initJob;

public class GroupCompartorRunner implements Tool {
    private Configuration conf = null;

    /*
计算每一个订单中最贵的商品

执行全部map -> 执行order 对象 compareTo -> 执行分组的 compare

1 222
2 722
1 33
3 232
3 33
2 522
2 122

输出
1	222
2	722
3	232
 */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/orderinfo", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new GroupCompartorRunner(), args);
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

        job.setJarByClass(GroupCompartorRunner.class);
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(MyGroupingComparator.class);

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


    static class OrderMapper extends Mapper<LongWritable, Text, Order, NullWritable> {
        Order k = new Order();

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Order, NullWritable>.Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split(" ");
            int orderId = Integer.parseInt(fields[0]);
            int price = Integer.parseInt(fields[1]);
            k.setOrderId(orderId);
            k.setPrice(price);
            context.write(k, NullWritable.get());
        }
    }

    static class OrderReducer extends Reducer<Order, NullWritable, Order, NullWritable> {
        @Override
        protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            context.write(key, NullWritable.get());
        }
    }


}
