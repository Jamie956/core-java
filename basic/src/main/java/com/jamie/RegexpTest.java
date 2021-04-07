package com.jamie;

import cn.hutool.core.util.ReUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest {
    /**
     * \w               一个  数字或字母
     * \w*              0或以上个   数字或字母
     * \w+              1或以上个   数字或字母
     * abc\w*           abc     连接  0或以上个   数字字母
     * \d               匹配一个数字字符。等价于[0-9]
     * \s               匹配一个任何空白字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v]。
     * ?                贪婪模式则尽可能多的匹配所搜索的字符串
     * \s*              0或以上个   空格
     * \s+              1或以上个   空格
     * \s?              0或1个空格
     * \D               匹配一个非数字字符。等价于[^0-9]。
     * \W               匹配任何非单词字符。等价于“[^A-Za-z0-9_]”。
     * {n}              n是一个非负整数。匹配确定的n次
     * {n,m}            m和n均为非负整数，其中n<=m。最少匹配n次且最多匹配m次。
     * [a-zA-Z0-9]      一个a-z的字母或A-Z的字母或0-9的数字，[]字符范围。匹配指定范围内的任意字符。
     * w{1}             一个字母或数字
     * \w{1,2}          1-2个字母或数字
     * \w{1,3}          1-3个字母或数字
     * \w{1,7}          1-7个字母或数字
     * \w{0,7}          0-7个字母或数字
     * [xyz]            字符集合。匹配所包含的任意一个字符
     * [^xyz]           负值字符集合。匹配未包含的任意字符。
     * [kj]ava          k 或 j 连接ava
     * [^kj]ava         除了 k 或 j 连接ava
     * .                匹配除“\n”之外的任何单个字符
     * .[o][b].*        第1个任意，第2个是o，第3个是b，后面0或多个任意
     * .ob.*            同上
     * .*               0个或多个 任意
     * \w+\s\'.+        一个或多个字母或数字 + 一个空格 + 一个' + 一个或多个任意
     * \w+\sa.+         一个或多个字母或数字 + 一个空格 + 一个字母a + 一个或多个任意
     * \D+\sa.+         1个或多个(排除数字) + 一个空格 + 一个字母a + 一个或多个任意
     * \D+\s.+          1个或多个(排除数字) + 一个空格 + 一个或多个任意
     * \w+\s?\'?\d{0,3}\'?          一个或多个字母或数字 + 0或1个空格 + 0或1个' + 0-3个数字 + 0或1个'
     * \w+(\s\')?\d{0,3}\'?         同上
     * \w+(\s\'(\d*)\')?
     * .+(\\d)$        数字结尾
     *
     */
    @Test
    public void test01() {
        String[] patterns = {"\\w", "\\w*", "\\w+", "abc\\w*", "\\w+\\s\\d+", "\\w+\\s*\\d+", "\\w+\\s+\\d+", "\\w+\\s?\\d+", "\\D+", "\\W", "[a-zA-Z0-9]", "\\w{1}", "\\w{1,2}", "\\w{1,3}", "\\w{1,7}", "\\w{0,7}", "[kj]ava", "[^kj]ava", ".[o][b].*", ".ob.*", ".*", "\\w+", "\\w+\\s\\'.+", "\\w+\\sa.+", "\\D+\\sa.+", "\\D+\\s.+", "\\w+\\s?\\'?\\d{0,3}\\'?", "\\w+(\\s\\')?\\d{0,3}\\'?", "\\w+(\\s\\'(\\d*)\\')?", "M.+", "^M.+", ".+(\\d)$", "\\d{4}-\\d{2}-\\d{2}"};
        String[] words = {"a", "1", "abc", "abcde987321", "abcdefg", "defghij", "", "abc", "abc 123", "abc123", "abc  123", "a", "rt", "C", "+", "RtS", "8", "abcdefg", "a+\\", "kava", "sava", "lava", "tava", "java", "Robert", "book", "cat", "job", "Todor", "+ob 123? @ p", "Boooob", "jjobss", "abc135gf", "string '123'", "int a = 5247;", "int A = 5247;", "int b = 5247;", "1int a = 5247;", "string '", "Marko is a good boy.", "Our Marko, is a good boy!", "Nobody is as good as our Marko is!", "2.345,56", "-52.678.110", "235", "128m", "2020-08-03"};

        for (String pattern : patterns) {
            System.out.println(">>>>>>>>>>>>>> pattern: " + pattern + " <<<<<<<<<<<<<<");
            for (String word : words) {
                if (word.matches(pattern)) {
                    System.out.println(String.format("MATCHES!          word: %s, pattern: %s", word, pattern));
                } else {
                    System.out.println(String.format("NOT MATCHES!      word: %s, pattern: %s", word, pattern));
                }
            }
        }
    }

    @Test
    public void asdasd() {
        String content = "<p>asdasdas</p>\n" + "<p>sdfsd</p>\n" + "<p>bbb</p>\n" + "<p>cccc</p>";

        //删除第一个<p>
        String a = ReUtil.delFirst("<p>", content);
        //替换第一个<p>
        String b = content.replaceFirst("<p>", "<p>1:  " );
        //删除全部<p></p>
        String c = content.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
    }

    /**
     * 从text中找出匹配正则表达式的字符
     * 找出文本的全部中文                         [\\u4E00-\\u9FA5]+
     * 找出文本的全部数字                        \d+
     * 找出指定开头x和结尾y的文本                 x.+.y
     * 取PIN=为开头的内容                          (?<=PIN=).\S*
     * 取以=开头 以&结尾 取得的中间的内容            (?<==).*?(?=(&|$))
     * 取"key" : "开头，",结尾的中间的内容            (?<="key" : ").*?(?=(",|$))
     * 取某个字符串|开头的内容                      (\|.*)
     */
    @Test
    public void findMatch() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("asa1jbjb3");
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    /**
     * 正则表达式解析sql
     */
    @Test
    public void sqlRegParse() throws IOException {
        //匹配整个ddl，将ddl分为表名，列sql部分，表注释
        String ddlReg = "\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$";
        //匹配列sql部分，分别解析每一列的列名 类型 和列注释
        String colReg = "\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)";

        Pattern ddlPattern = Pattern.compile(ddlReg, Pattern.CASE_INSENSITIVE);
        Pattern colPattern = Pattern.compile(colReg, Pattern.CASE_INSENSITIVE);
        String sql = FileUtils.readFileToString(new File("src/main/resources/myddl"), "UTF-8");

        Matcher matcher = ddlPattern.matcher(sql);
        if (matcher.find()) {
            String tableName = matcher.group("tableName");
            String tableComment = matcher.group("tableComment");

            System.out.println(tableName + "\t\t" + tableComment);
            System.out.println("--------------------");

            String columnsSql = matcher.group("columnsSQL");

            if (columnsSql != null && columnsSql.length() > 0) {
                Matcher colMatcher = colPattern.matcher(columnsSql);
                while (colMatcher.find()) {
                    String fieldName = colMatcher.group("fieldName");
                    String fieldType = colMatcher.group("fieldType");
                    String fieldComment = colMatcher.group("fieldComment");
                    if (!"key".equalsIgnoreCase(fieldType)) {
                        System.out.println(fieldName + "\t\t" + fieldType + "\t\t" + fieldComment);
                    }
                }
            }
        }
    }

    @Test
    public void replaceHtml() {
        String str = "<p>去除所有html标签,<img/><My-Tag class=\\\"abc\\\" value=\\\"test\\\">自定     义标签\n也\r可以\t去除哦</My-Tag></p>";
        str = str.replaceAll("<[^>]+>","").replaceAll("[\r\t\n]", "").replaceAll(" ", "");
        System.out.println(str);
    }

    @Test
    public void replaceDate() {
        String str = "CompanyNotice_2021-03-05.txt";
        str = str.replaceAll("_\\d{4}-\\d{2}-\\d{2}", "");
        System.out.println(str);
    }
}
