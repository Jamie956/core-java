package cat;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.sql.Date;


public class CheckDate extends UDF{
    public int evaluate(String date) {
        try {
            Date.valueOf(date);
            return 0;
        } catch (IllegalArgumentException e) {
            return 1;
        }
    }
}
