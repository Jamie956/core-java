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
        System.out.println(a);
    }

    public String evaluate(String str)  {
        str = "1,2,3,4";
         String[] split = str.split(",");
        Map<String, Double>  map =new HashMap<>();
        map.put("sepal_length", Double.parseDouble(split[0]));
        map.put("sepal_width",Double.parseDouble(split[1]));
        map.put("petal_length", Double.parseDouble(split[2]));
        map.put("petal_width", Double.parseDouble(split[3]));

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
        }
        return null;
    }

}
