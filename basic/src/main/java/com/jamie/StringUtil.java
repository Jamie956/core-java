package com.jamie;

import cn.hutool.http.HtmlUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

public class StringUtil {
    public static void main(String[] args) {
        String replace = cleanLineBeak("   \tname\r\n                              ");
        System.out.println(replace);
    }

    public static String cleanLineBeak(String str) {
        return str.replaceAll("[\n\t\r]","").trim();
    }

    /**
     * 截取文章前n个字(截取部分包含关键字)
     */
    private String subContent(String content, String[] keywords) {
        if (StringUtils.isBlank(content)) {
            return "";
        }
        //去除html标签
        String cleanContent = HtmlUtil.cleanHtmlTag(content);
        //去除换行符
        cleanContent = cleanContent.replaceAll("[\n\t\r]", "").trim();

        int length = cleanContent.length();
        //截取长度
        int subLen = 200;

        //正文长度小于subLen，不截取
        if (length < subLen) {
            return cleanContent;
        }

        if (keywords.length == 0) {
            return cleanContent.substring(0, subLen) + "...";
        }

        //第一个关键字出现位置
        int start = Integer.MAX_VALUE;
        for (String keyword : keywords) {
            int index = cleanContent.indexOf(keyword);
            if (index > -1) {
                start = Math.min(index, start);
            }
        }

        //没有匹配关键字
        if (start == Integer.MAX_VALUE) {
            return cleanContent.substring(0, subLen) + "...";
        }

        //第一个关键出现位置到末尾（长度小于subLen）
        if ((length - start) <= subLen) {
            return cleanContent.substring(start, length);
        }

        //第一个关键字出现位置开始，长度subLen
        return cleanContent.substring(start).substring(0, subLen) + "...";
    }

    /**
     * &nbsp;    &lt;    &gt;    &amp;   &quot;  &apos;
     * 转义
     */
    @Test
    public void htmlConvert() {
        //&lt; -> <
        String unescapeHtml4 = StringEscapeUtils.unescapeHtml4("&lt;");
        System.out.println(unescapeHtml4);
        //< -> &lt;
        String escapeHtml4 = StringEscapeUtils.escapeHtml4("<");
        System.out.println(escapeHtml4);
    }

    /**
     * 驼峰、下划线字符串转换
     */
    @Test
    public void guavaConvert() {
        // 驼峰转下划线, userName -> user_name
        Converter<String, String> caseFormat = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
        System.out.println(caseFormat.convert("userName"));

        // 驼峰转连接符, userName -> user-name
        caseFormat = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN);
        System.out.println(caseFormat.convert("userName"));

        // 驼峰转首字符大写驼峰, userName -> UserName
        caseFormat = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
        System.out.println(caseFormat.convert("userName"));

        // 驼峰转大写下划线, userName -> USER_NAME
        caseFormat = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        System.out.println(caseFormat.convert("userName"));
    }

}
