package com.jamie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jamie.utils.IDGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    private static Properties p;

    static {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src/main/resources/test.properties"));
            p = new Properties();
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json 递归生成树结构
     */
    public static void jsonLoop(Object object, long parentId) {
        if (object instanceof JSONObject) {
            JSONObject json = (JSONObject) object;
            long id = IDGenerator.nextId();
            json.put("snow_id", id);
            json.put("parent_snow_id", parentId);

            for (String key : json.keySet()) {
                Object value = json.get(key);
                if (value instanceof JSONObject || value instanceof JSONArray) {
                    jsonLoop(value, id);
                } else if (value instanceof String) {
                    json.put(key, value.toString() + "!");
                }
            }
        }
        if (object instanceof JSONArray) {
            JSONArray arr = (JSONArray) object;
            for (Object e : arr) {
                jsonLoop(e, parentId);
            }
        }
    }

    public static Job initJob(Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue) throws IOException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        return job;
    }

    public static Job initJob(Configuration conf, Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue) throws IOException {

        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        return job;
    }

    public static Job initJob(Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue, String inPath) throws IOException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/" + inPath));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        return job;
    }

    public static Job initJob(Configuration conf, Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<? extends Reducer> reduceClass, Class<?> mapperKey, Class<?> mapperValue, Class<?> reduceKey, Class<?> reduceValue, String inPath) throws IOException {
        Job job = Job.getInstance(conf);
        job.setJarByClass(driverClass);

        job.setMapperClass(mapperClass);
        job.setReducerClass(reduceClass);

        job.setMapOutputKeyClass(mapperKey);
        job.setMapOutputValueClass(mapperValue);

        job.setOutputKeyClass(reduceKey);
        job.setOutputValueClass(reduceValue);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/" + inPath));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        return job;
    }

    public static Job initJob(Class<?> driverClass, Class<? extends Mapper> mapperClass, Class<?> key, Class<?> value, String inPath) throws IOException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(driverClass);
        job.setMapperClass(mapperClass);

        job.setOutputKeyClass(key);
        job.setOutputValueClass(value);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/" + inPath));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        // 不进行reduce
        job.setNumReduceTasks(0);
        return job;
    }
}
