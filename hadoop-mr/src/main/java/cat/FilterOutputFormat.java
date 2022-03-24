package cat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FilterOutputFormat extends FileOutputFormat<Text, NullWritable> {
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) {
        return new FRecordWriter(job);
    }

    static class FRecordWriter extends RecordWriter<Text, NullWritable> {
        FSDataOutputStream atOut;
        FSDataOutputStream otOut;

        //构建输出流
        public FRecordWriter(TaskAttemptContext job) {
            String atLog = "bigdata/src/main/resources/out/atguigu.log";
            String otLog = "bigdata/src/main/resources/out/other.log";
            try {
                FileSystem fs = FileSystem.get(job.getConfiguration());
                //创建文件输出流
                atOut = fs.create(new Path(atLog));
                otOut = fs.create(new Path(otLog));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void write(Text key, NullWritable value) throws IOException {
            //根据条件，写出到不同的流
            if (key.toString().contains("atguigu")) {
                atOut.write(key.toString().getBytes());
            } else {
                otOut.write(key.toString().getBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext context) {
            IOUtils.closeStream(atOut);
            IOUtils.closeStream(otOut);
        }
    }

}
