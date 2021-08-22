package com.jamie;

import cn.hutool.http.HtmlUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

public class ConvertTest {



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
        Converter<String, String> converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
        // 输出: user_name
        System.out.println(converter.convert("userName"));
        System.out.println(converter.convert("user_name"));

        // 驼峰转连接符, userName -> user-name
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN);
        // 输出: user-name
        System.out.println(converter.convert("userName"));

        // 驼峰转首字符大写驼峰, userName -> UserName
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
        // 输出: UserName
        System.out.println(converter.convert("userName"));

        // 驼峰转大写下划线, userName -> USER_NAME
        converter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        // 输出: USER_NAME
        System.out.println(converter.convert("userName"));
    }

    @Test
    public void rmExtend() {
        String fileName = "asasasa.txt.exe";
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println(fileName);
    }

    @Test
    public void htmlUtilTest() {
        //清除指定HTML标签和被标签包围的内容
        String content = "<div style=\"padding:10px 0;text-align:center;\"><img src=./W020201019582462534023.jpg style></div><div class=TRS_Editor><style type=text/css>\n" + "\n" + ".TRS_Editor P{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor DIV{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor TD{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor TH{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor SPAN{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor FONT{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor UL{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor LI{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor A{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}</style><div class=TRS_Editor><style type=text/css>\n" + "\n" + ".TRS_Editor P{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor DIV{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor TD{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor TH{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor SPAN{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor FONT{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor UL{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor LI{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}.TRS_Editor A{margin-top:20px;margin-bottom:20px;line-height:1.5;font-family:宋体;font-size:10.5pt;}</style><p align=justify>";
        content = HtmlUtil.removeHtmlTag(content, "img", "style");
        System.out.println(content);

        //清除指定HTML标签，不包括内容
        String s = "<p></p><p></p><p></p><p><br></p><p>附件：1.<ahref=\"http://scjgj.shanxi.gov.cn/attached/file/2019/11/29/20191129184421_671.docx\"target=\"_blank\">本次检验项目</a></p><p>2.<ahref=\"http://scjgj.shanxi.gov.cn/attached/file/2019/11/29/20191129184455_438.xlsx\"target=\"_blank\">食品监督抽检产品合格信息</a></p><p>3.<ahref=\"http://scjgj.shanxi.gov.cn/attached/file/2019/11/29/20191129185247_713.xls\"target=\"_blank\">食品监督抽检不合格产品信息</a></p>";
        s = HtmlUtil.unwrapHtmlTag(s, "p");
        System.out.println(s);
    }

}
