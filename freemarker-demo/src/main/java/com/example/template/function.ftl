<#assign cname=r"特殊字符完成输出(http:\www.baidu.com)">
${cname}

<#assign cname2='<img class="k">hi.asd//http:\\www.baidu.com<img>'>
${cname2}


算数运算：
<#assign number1 = 10>
<#assign number2 = 5>
"+" : ${number1 + number2}
"－" : ${number1 - number2}
"*" : ${number1 * number2}
"/" : ${number1 / number2}
"%" : ${number1 % number2}

比较运算符：
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
    "*" : ${number1 * number2}
<#else>
    "/" : ${number1 / number2}
</#if>

内建函数：
<#assign data = "abcd1234">
<#assign data2 = "HiWorld">
第一个字母大写：${data?cap_first}
第一个字母小写：${data2?uncap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}
<#assign floatData = 12.34>
数值取整数：${floatData?int}


Map集合：
<#assign mapData={"name":"程序员", "salary":15000}>
name：${mapData["name"]}

通过Key遍历Map：
<#list mapData?keys as key>
    Key: ${key} - Value: ${mapData[key]}
</#list>

通过Value遍历Map：
<#list mapData?values as value>
    Value: ${value}
</#list>

List集合：
<#assign listData=["ITDragon", "blog", "is", "cool"]>
<#list listData as value>
    ${value}
</#list>

include指令：
引入其他文件：<#include "otherFreeMarker.ftl" />


命名空间：
<#import "otherFreeMarker.ftl" as otherFtl>
${otherFtl.otherName}
<@otherFtl.addMethod a=10 b=20 />
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值"/>
${otherFtl.otherName}
<#assign otherName="修改otherFreeMarker.ftl中的otherName变量值" in otherFtl />
${otherFtl.otherName}


<#assign x = ["red", 16, "blue", "cyan"]>
数组是否包含blue，包含返回yes，否则no
"blue": ${x?seq_contains("blue")?string("yes", "no")}

<#if x?seq_contains("blue")>
    has blueblueblue
</#if>


<#assign cat="mmm">
宏，类似函数
<#macro mo>
    ${cat}  111
</#macro>
使用宏
<@mo />

<#macro moArgs a b c>
    ${a+b+c}
</#macro>
<@moArgs a=1 b=2 c=3 />

