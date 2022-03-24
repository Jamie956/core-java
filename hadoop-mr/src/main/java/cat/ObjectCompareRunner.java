package cat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;

public class ObjectCompareRunner implements Tool {
    private Configuration conf = null;

    /*
    WritableComparable 对象重写排序方法

1 222
2 722
1 33
3 232
3 33
2 522
2 122

    输出
1,222
1,33
2,722
2,522
2,122
3,232
3,33
     */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/orderinfo", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new ObjectCompareRunner(), args);
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

        job.setJarByClass(ObjectCompareRunner.class);
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);

        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

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
