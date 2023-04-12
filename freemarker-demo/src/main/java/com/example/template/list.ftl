<#--集合长度-->
${users?size}
<#--空判断和对象集合-->
<#if users??>
    <#list users as user >
        ${user}
    </#list>
<#else>
    ${user!"变量为空则给一个默认值"}
</#if>