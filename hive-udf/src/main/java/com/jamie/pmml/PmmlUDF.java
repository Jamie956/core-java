package com.jamie.pmml;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zjm
 * @Date: 2021/7/8 16:03
 */
public class PmmlUDF extends UDF{
    public static void main(String[] args) {
        String a = new PmmlUDF().evaluate("1,2,3,4");
    }

    public String evaluate(String str)  {

         String[] split = str.split(",");
        Map<String, Double>  map =new HashMap<>();
//            map.put("sepal_length", 7.0);
//            map.put("sepal_width", 3.2);
//            map.put("petal_length", 4.7);
//            map.put("petal_width", 1.4);

        map.put("sepal_length", Double.parseDouble(split[0]));
        map.put("sepal_width",Double.parseDouble(split[1]));
        map.put("petal_length", Double.parseDouble(split[2]));
        map.put("petal_width", Double.parseDouble(split[3]));

        System.out.println("map:"+map.toString());



        ClassPathResource resource = new ClassPathResource("/lightgbm.pmml");

        try (InputStream is = resource.getInputStream()) {

            System.out.println("is: "+is);

//            OutputStream out = new ByteArrayOutputStream();
//            byte[] buf = new byte[1024];
//
//            while (true) {
//                int len = is.read(buf);
//                if (len != -1) {
//                    out.write(buf, 0, len);
//                } else {
//                    break;
//                }
//            }
//            System.out.println("outstring:"+out.toString());

            PMML pmml = org.jpmml.model.PMMLUtil.unmarshal(is);

            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();

            ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            Evaluator evaluator = (Evaluator) modelEvaluator;

            List<InputField> inputFields = evaluator.getInputFields();
            // 过模型的原始特征，从画像中获取数据，作为模型输入
            Map<FieldName, FieldValue> arguments = new LinkedHashMap<>();
            for (InputField inputField : inputFields) {
                FieldName inputFieldName = inputField.getName();
                Object rawValue = map.get(inputFieldName.getValue());
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
            System.out.println(e.getMessage());
        }
        return null;
    }

}
