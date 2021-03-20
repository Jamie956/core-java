package com.video;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class VideoMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String lineEtl = VideoETL.lineETL(line);

        if (StringUtils.isBlank(lineEtl)) {
            return;
        }
        v.set(lineEtl);
        context.write(NullWritable.get(), v);
    }
}
