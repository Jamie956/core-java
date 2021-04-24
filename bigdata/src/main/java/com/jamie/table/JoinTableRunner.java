package com.jamie.table;

import com.jamie.HDFSUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.IOException;
import java.util.ArrayList;

public class JoinTableRunner implements Tool {
    private Configuration conf = null;


    /*
reduce 合并两个表

1001	1
1002	2
1003	3
1004	1
1005	2
1006	3

1	小米
2	华为
3	格力

输出
1004	1	小米
1001	1	小米
1005	2	华为
1002	2	华为
1006	3	格力
1003	3	格力
     */
    public static void main(String[] args) {
        args = new String[]{"bigdata/src/main/resources/table", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new JoinTableRunner(), args);
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

        job.setJarByClass(JoinTableRunner.class);
        job.setMapperClass(TableMapper.class);
        job.setReducerClass(TableReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Table.class);

        job.setOutputKeyClass(Table.class);
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

    static class TableMapper extends Mapper<LongWritable, Text, Text, Table> {
        String fileName;
        Table table = new Table();
        Text k = new Text();

        @Override
        protected void setup(Mapper<LongWritable, Text, Text, Table>.Context context) {
            // 获取文件的名称
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            fileName = inputSplit.getPath().getName();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] fields = value.toString().split("\t");

            //用关联的id 作为key，使得相同key 聚合在一起
            if (fileName.startsWith("order")) {
                String orderId = fields[0];
                String productId = fields[1];

                table.setOrderId(orderId);
                table.setProductId(productId);
                table.setProductName("");
                table.setFlag("order");

                //productId 作为key，聚合
                k.set(productId);
            } else if (fileName.startsWith("product")) {
                String productId = fields[0];
                String productName = fields[1];

                table.setOrderId("");
                table.setProductId(productId);
                table.setProductName(productName);
                table.setFlag("product");

                //productId 作为key，聚合
                k.set(productId);
            }

            context.write(k, table);
        }
    }

    static class TableReducer extends Reducer<Text, Table, Table, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<Table> values, Context context) throws IOException, InterruptedException {
            ArrayList<Table> orders = new ArrayList<>();
            Table product = new Table();

            for (Table value : values) {
                String flag = value.getFlag();
                String orderId = value.getOrderId();
                String productId = value.getProductId();
                String productName = value.getProductName();

                if ("order".equals(flag)) {
                    Table order = new Table();
                    order.setOrderId(orderId);
                    order.setProductId(productId);

                    orders.add(order);

                } else if ("product".equals(flag)) {
                    product.setProductId(productId);
                    product.setProductName(productName);
                }
            }

            for (Table order : orders) {
                order.setProductName(product.getProductName());
                context.write(order, NullWritable.get());
            }
        }
    }


}
