package com.jamie;

import com.jamie.cache.DistributedCacheMapper;

import com.jamie.flowsum.Counter;
import com.jamie.flowsum.FlowCountMapper;
import com.jamie.flowsum.FlowCountReducer;
import com.jamie.flowsum.MyPartitioner;
import com.jamie.inputformat.SequenceFileMapper;
import com.jamie.inputformat.SequenceFileReducer;
import com.jamie.inputformat.WholeFileInputformat;
import com.jamie.kv.KVTextMapper;
import com.jamie.kv.KVTextReducer;
import com.jamie.log.LogMapper;
import com.jamie.order.*;
import com.jamie.outputformat.FilterMapper;
import com.jamie.outputformat.FilterOutputFormat;
import com.jamie.outputformat.FilterReducer;
import com.jamie.table.Table;
import com.jamie.table.TableMapper;
import com.jamie.table.TableReducer;
import com.jamie.topn.OrderCleanUpMapper;
import com.jamie.topn.OrderCleanUpReducer;
import com.jamie.wordcount.WordcountCombiner;
import com.jamie.wordcount.WordcountMapper;
import com.jamie.wordcount.WordcountReducer;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static com.jamie.Utils.initJob;

public class Driver {
    public static final String OUTPUT_PATH = "src/main/resources/out";



    /*
    指定切片数量
    number of splits:3

    输入文件一共9行
    每个切片分3行
    需要3个切片
     */
    @Test
    public void splitNumline() throws Exception {
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Job job = initJob(Driver.class, WordcountMapper.class, WordcountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, "/words");
        // 设置每个切片InputSplit中划分三条记录
        NLineInputFormat.setNumLinesPerSplit(job, 3);
        // 使用NLineInputFormat处理记录数
        job.setInputFormatClass(NLineInputFormat.class);
        job.waitForCompletion(true);
    }

    /**
     * 去除日志中字段长度小于等于11的日志
     */
    @Test
    public void noReduceLog() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Job job = initJob(Driver.class, LogMapper.class, Text.class, NullWritable.class, "/log");
        job.waitForCompletion(true);
    }

    /*
配置指定 kv 分隔符
     */
    @Test
    public void kvSeperator() throws Exception {
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Configuration conf = new Configuration();
        //配置指定 kv 分隔符
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");
        Job job = initJob(conf, Driver.class, KVTextMapper.class, KVTextReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, "/top10");
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.waitForCompletion(true);
    }

    /**
     * 输出 SequenceFile文件
     */
    @Test
    public void sequenceFile() throws IOException, ClassNotFoundException, InterruptedException {
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Job job = initJob(Driver.class, SequenceFileMapper.class, SequenceFileReducer.class, Text.class, BytesWritable.class, Text.class, BytesWritable.class, "/words");
        job.setInputFormatClass(WholeFileInputformat.class);
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        job.waitForCompletion(true);
    }

    /*
    缓存文件
order
1001	1
1002	2
1003	3
1004	1
1005	2
1006	3

product
1	小米
2	华为
3	格力

预期
1001	1	小米
1002	2	华为
1003	3	格力
1004	1	小米
1005	2	华为
1006	3	格力
     */
    @Test
    public void cacheFile() throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        String cacheFilePath = "src/main/resources/product";
        FileUtils.deleteDirectory(new File(OUTPUT_PATH));
        Job job = initJob(Driver.class, DistributedCacheMapper.class, Text.class, NullWritable.class, "/order");
        // 添加缓存文件
        job.addCacheFile(new URI(cacheFilePath));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

}
