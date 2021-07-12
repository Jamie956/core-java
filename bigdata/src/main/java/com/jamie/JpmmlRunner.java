package com.jamie;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.MRJobConfig;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JpmmlRunner implements Tool {
    private Configuration conf = null;

    public static void main(String[] args) {
//        args = new String[]{"bigdata/src/main/resources/pmmldata", "bigdata/src/main/resources/out"};

        try {
            int code = ToolRunner.run(new JpmmlRunner(), args);
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
//        conf.setBoolean(MRJobConfig.MAPREDUCE_JOB_USER_CLASSPATH_FIRST, true);

        Job job = Job.getInstance(conf);

        job.setJarByClass(JpmmlRunner.class);
        job.setMapperClass(JpmmlMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 不进行reduce
        job.setNumReduceTasks(0);

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

    static  class JpmmlMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            String line = value.toString();

            Map<String, Double>  map=new HashMap<>(4);
            String[] split = line.split(",");

            map.put("sepal_length", Double.parseDouble(split[0]));
            map.put("sepal_width",Double.parseDouble(split[1]));
            map.put("petal_length", Double.parseDouble(split[2]));
            map.put("petal_width", Double.parseDouble(split[3]));

            String ret = predictLrHeart(map);

            k.set(ret);
            context.write(k, NullWritable.get());
        }

    }

    public static String predictLrHeart(Map<String, Double> irismap) {
        ClassPathResource resource = new ClassPathResource("/lightgbm.pmml");

        try (InputStream is = resource.getInputStream()) {

            PMML pmml = org.jpmml.model.PMMLUtil.unmarshal(is);

            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();

            ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            Evaluator evaluator = (Evaluator) modelEvaluator;

            List<InputField> inputFields = evaluator.getInputFields();
            // 过模型的原始特征，从画像中获取数据，作为模型输入
            Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
            for (InputField inputField : inputFields) {
                FieldName inputFieldName = inputField.getName();
                Object rawValue = irismap.get(inputFieldName.getValue());
                FieldValue inputFieldValue = inputField.prepare(rawValue);
                arguments.put(inputFieldName, inputFieldValue);
            }

            Map<FieldName, ?> results = evaluator.evaluate(arguments);
            List<TargetField> targetFields = evaluator.getTargetFields();
            //对于分类问题等有多个输出。

            Map<Object, Object> ret = new HashMap<>();
            for (TargetField targetField : targetFields) {
                FieldName targetFieldName = targetField.getName();
                Object targetFieldValue = results.get(targetFieldName);
                ret.put(targetFieldName.getValue(), targetFieldValue);
            }

            return ret.toString();
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return "";
    }

}
