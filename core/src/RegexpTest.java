import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest {
    @Test
    public void match() {
        //\w               一个  数字/字母
        match("\\w", "1", "a", "aa", "12", "!", "!@#");

        //\w*              0或以上个   数字/字母
        match("\\w*", "1", "a", "aa", "12", "!", "!@#");

        //\w+              1或以上个   数字/字母
        match("\\w+", "1", "a", "aa", "12", "!", "!@#");

        //\w*[\u4E00-\u9FA5]+       匹配中文
        match("\\w*[\\u4E00-\\u9FA5]+", "1", "a", "aa", "!@#", "aa啊的");

        //abc\w*           abc     连接  0或以上个   数字/字母
        match("abc\\w*", "1", "abc", "abc12", "12");

        //\d               匹配一个数字字符     等价于[0-9]
        match("\\d", "1", "a", "12");

        //\s               匹配一个任何空白字符，包括空格、制表符、换页符等    等价于[ \f\n\r\t\v]
        match("\\s", " ", "\t", "\r");

        //\s*              >=0个   空格
        match("\\s*", "", " ", "  ");

        //\s+              >=1个   空格
        match("\\s+", "", " ", "  ");

        //\s?              0或1个     空格
        match("\\s?", "", " ", "  ");

        //\D               匹配一个非数字字符    等价于[^0-9]
        match("\\D", "12", "ab", "1", "a", "!", "!@");

        //\W               匹配任何非字母非数字    等价于[^A-Za-z0-9_]
        match("\\W", "12", "ab", "1", "a", "!", "!@");

        //\w{n}              n是一个非负整数     匹配确定的n次
        match("\\w{1}", "12", "ab", "1", "a", "!", "!@");

        //\w{n,m}            m和n均为非负整数，其中n<=m  最少匹配n次且最多匹配m次
        match("\\w{1,2}", "12", "ab", "1", "a", "!", "!@");

        //[a-zA-Z0-9]      匹配一个a-z的字母或A-Z的字母或0-9的数字，[]字符范围
        match("[a-zA-Z0-9]", "12", "ab", "1", "a", "!", "!@");

        //[xyz]            匹配一个字符，这个字符是x or y or z
        match("[xyz]", "12", "ab", "1", "a", "x", "xy");

        //[^xyz]           匹配一个字符，这个字符不是x or y or z
        match("[^xyz]", "12", "ab", "1", "a", "x", "xy");

        //[kj]ava          k 或 j 连接ava
        match("[kj]ava", "java", "kava", "mava", "1java");

        //[^kj]ava         除了 k 或 j 连接ava
        match("[^kj]ava", "mava1", "kava", "mava", "1java");

        //.                匹配除\n之外的任何单个字符
        match(".", "12", "ab", "1", "a", "\n");

        //.[o][b].*        第1位任意单个字符，第2位为o，第3位为b，第4位及后面是>=0个任意字符
        match(".[o][b].*", "1obj", "obj", "1obj", "1ob");

        //.ob.*            同上
        match(".ob.*", "1obj", "obj", "1obj", "1ob");

        //.*               >=0个 任意字符
        match(".*", "1obj", "obj", "1obj", "1ob");

        //\w+\s\'.+        >=1 个字母/数字 + 一个空格 + 一个' + >=1个任意字符
        match("\\w+\\s\\'.+", "1a '1", "1a'1", "'1");

        //\w+\s?\'?\d{0,3}\'?          >=1字母/数字 + 0或1个空格 + 0或1个' + 0-3个数字 + 0或1个'
        match("\\w+\\s?\\'?\\d{0,3}\\'?", "a1 '1'");

        //\w+(\s\')?\d{0,3}\'?         同上
        match("\\w+(\\s\\')?\\d{0,3}\\'?", "a1 '1'");

        //\w+(\s\'(\d*)\')?
        match("\\w+(\\s\\')?\\d{0,3}\\'?", "a111'", "a111");

        //.+(\d)$        匹配数字结尾
        match(".+(\\d)$", "a1", "aa1", "11a", "1a1");

    }

    public void match(String pattern, String... words) {
        System.out.println(String.format("-------------- pattern is [%s] --------------", pattern));
        for (String word : words) {
            if (word.matches(pattern)) {
                System.out.println(String.format("[%s] match [%s]", word, pattern));
            } else {
                System.out.println(String.format("[%s] not match [%s]", word, pattern));
            }
        }
    }

    @Test
    public void replace() {
        String content = "<p>asdasdas</p><p>sdf45156321sd</p><p>bbb</p><p>cccc</p>";

        //替换html标签
        removeReplace(content, "<[^>]*>");

        //替换数字
        removeReplace(content, "\\d");

        //去除日期
        removeReplace("CompanyNotice_2021-03-05.txt", "_\\d{4}-\\d{2}-\\d{2}");

        String html = "<html>\n" +
                "<!--This is a head-->\n" +
                "<head>A Head</head>\n" +
                "<!--This is    a div -->\n" +
                "<div>A Div</div>\n" +
                "<!--This is     a span-->\n" +
                "<!--span in     a div-->\n" +
                "<div>a div</div>\n" +
                "<span>A span</span>\n" +
                "</html>";
        //去除注释
        removeReplace(html, "<!--.*?-->");
    }

    public String removeReplace(String content, String regex) {
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("要处理的文本 [%s]", content));
        System.out.println(String.format("处理的正则 [%s]", regex));
        String result = content.replaceAll(regex, "");
        System.out.println(String.format("替换后的结果 [%s]", result));
        System.out.println();
        return result;
    }

    @Test
    public void findMatch() {
        //d+        找数字
        matchTest("\\d+", "asa9jbjb1", "asa91jbjb232321");

        //找出文本的全部中文
        matchTest("[\\u4E00-\\u9FA5]+", "asdasd阿松大啊实打实大苏打", "asa91jbjb232321");

        //找出指定开头x和结尾y的文本                 x.+.y
        matchTest("x.+.y", "x1a23y111x23y2");

        //取 PIN= 为开头的内容                          (?<=PIN=).\S*
        matchTest("(?<=PIN=).\\S*", "PIN=123");

        //取以=开头 以&结尾 取得的中间的内容            (?<==).*?(?=(&|$))
        matchTest("(?<==).*?(?=(&|$))", "=asdasdnk&11", "=asdasdnk#");

        //取"key" : "开头，",结尾的中间的内容            (?<="key" : ").*?(?=(",|$))

        //取某个字符串|开头的内容                      (\|.*)
    }

    public void matchTest(String pattern, String... items) {
        for (String item : items) {
            System.out.println(String.format("-------------- 文本：[%s] --------------", item));
            final Matcher matcher = Pattern.compile(pattern).matcher(item);
            while (matcher.find()) {
                System.out.println(String.format("匹配起始位置 [%s], 匹配结束位置 [%s], 匹配的字符串 [%s]", matcher.start(), matcher.end(), matcher.group()));
            }
        }
        System.out.println();
    }

    /**
     * 截取字符串的开头到第n个中文
     */
    @Test
    public void chineseMatch() {
        String str = "as!@#@a9阿zsd松大jbjb的1123";
//        String str = "阿松大asdasd啊实打实大苏打";
//        String str = "阿松大啊实打实大苏打sadads";
//        String str = "asdasd阿松大啊实打实大苏打";
        final Matcher matcher = Pattern.compile("[\\u4E00-\\u9FA5]+").matcher(str);
        int n = 2;
        int len = 0;
        int cur = -1;
        while (matcher.find()) {
            int curLen = matcher.end() - matcher.start();
            if (len + curLen > n) {
                cur = matcher.start() + n - len;
                break;
            }
            len += curLen;
        }
        System.out.println(str.substring(0, cur));
    }

//    /**
//     * 正则表达式解析sql
//     */
//    @Test
//    public void sqlRegParse() throws IOException {
//        //匹配整个ddl，将ddl分为表名，列sql部分，表注释
//        String ddlReg = "\\s*create\\s+table\\s+(?<tableName>\\S+)[^\\(]*\\((?<columnsSQL>[\\s\\S]+)\\)[^\\)]+?(comment\\s*(=|on\\s+table)\\s*'(?<tableComment>.*?)'\\s*;?)?$";
//        //匹配列sql部分，分别解析每一列的列名 类型 和列注释
//        String colReg = "\\s*(?<fieldName>\\S+)\\s+(?<fieldType>\\w+)\\s*(?:\\([\\s\\d,]+\\))?((?!comment).)*(comment\\s*'(?<fieldComment>.*?)')?\\s*(,|$)";
//
//        Pattern ddlPattern = Pattern.compile(ddlReg, Pattern.CASE_INSENSITIVE);
//        Pattern colPattern = Pattern.compile(colReg, Pattern.CASE_INSENSITIVE);
//        String sql = FileUtils.readFileToString(new File("src/main/resources/myddl"), "UTF-8");
//
//        Matcher matcher = ddlPattern.matcher(sql);
//        if (matcher.find()) {
//            String tableName = matcher.group("tableName");
//            String tableComment = matcher.group("tableComment");
//
//            System.out.println(tableName + "\t\t" + tableComment);
//            System.out.println("--------------------");
//
//            String columnsSql = matcher.group("columnsSQL");
//
//            if (columnsSql != null && columnsSql.length() > 0) {
//                Matcher colMatcher = colPattern.matcher(columnsSql);
//                while (colMatcher.find()) {
//                    String fieldName = colMatcher.group("fieldName");
//                    String fieldType = colMatcher.group("fieldType");
//                    String fieldComment = colMatcher.group("fieldComment");
//                    if (!"key".equalsIgnoreCase(fieldType)) {
//                        System.out.println(fieldName + "\t\t" + fieldType + "\t\t" + fieldComment);
//                    }
//                }
//            }
//        }
//    }

//    /**
//     * 移除html标签的指定属性, 能够移除不带双引号的属性
//     */
//    @Test
//    public void matchAppendReplace() {
//        String text = "sdfsdfsdf<p align=\"center\" width=100 style=width:100px;height:152px;> fdsfdsfsd <a class=\"center\" width=100 style=width:100px;height:152px; width=100> saaa </a> sdfasd";
////        String text = "aaaaa<tr powered-by=xiumi.us opera-tn-ra-comp=_$.pages:0.layers:0.comps:27.col1:0.classicTable1:0>bbbbbbbbbbbb";
//        final Matcher matcher = Pattern.compile("(?i)<[^>]*?>").matcher(text);
//
//        String[] rmAttrs = {"style", "class"};
//
//        StringBuffer resultStringBuffer = new StringBuffer();
//        while (matcher.find()) {
//            //截取到一个html标签
//            String rawText = matcher.group();
//            String[] attrs = StringUtils.split(rawText, " ");
//
//            StringBuilder attrStringBuilder = new StringBuilder();
//
//            for (String attr : attrs) {
//                boolean noneMatch = Stream.of(rmAttrs).noneMatch(e -> attr.startsWith(e) || attr.contains("$"));
//                if (noneMatch) {
//                    attrStringBuilder.append(attr).append(" ");
//                }
//            }
//
//            if (attrStringBuilder.indexOf(">") == -1) {
//                attrStringBuilder.append(">");
//            }
//            String cleanText = attrStringBuilder.toString();
//            System.out.println(String.format("html标签 [%s] 替换为 [%s]", rawText, cleanText));
//            matcher.appendReplacement(resultStringBuffer, cleanText);
//        }
//        matcher.appendTail(resultStringBuffer);
//
//        System.out.println(String.format("result [%s]", resultStringBuffer.toString()));
//    }
}