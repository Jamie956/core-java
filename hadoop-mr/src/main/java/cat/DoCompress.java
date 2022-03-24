package cat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DoCompress {
    @Test
    public void decompress() throws IOException {
        String fileName = "src/main/resources/words.deflate";
        // 压缩方式
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        CompressionCodec codec = factory.getCodec(new Path(fileName));
        if (codec == null) {
            return;
        }

        // 输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        CompressionInputStream cis = codec.createInputStream(fis);

        // 输出流
        FileOutputStream fos = new FileOutputStream(new File(fileName + ".decode"));

        // 流的对拷
        IOUtils.copyBytes(cis, fos, 1024 * 1024, false);

        // 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(cis);
        IOUtils.closeStream(fis);
    }

    @Test
    public void compress() throws ClassNotFoundException, IOException {
        //被压缩文件
        String fileName = "src/main/resources/words";
		/*
		压缩方式
		org.apache.hadoop.io.compress.DefaultCodec
		org.apache.hadoop.io.compress.BZip2Codec
		org.apache.hadoop.io.compress.GzipCodec
		 */
        String method = "org.apache.hadoop.io.compress.DefaultCodec";

        // 输入流
        FileInputStream fis = new FileInputStream(new File(fileName));
        // 输出流
        Class<?> classCodec = Class.forName(method);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(classCodec, new Configuration());
        FileOutputStream fos = new FileOutputStream(new File(fileName + codec.getDefaultExtension()));
        CompressionOutputStream cos = codec.createOutputStream(fos);

        // 流的对拷
        IOUtils.copyBytes(fis, cos, 1024 * 1024, false);

        // 关闭资源
        IOUtils.closeStream(cos);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
}
