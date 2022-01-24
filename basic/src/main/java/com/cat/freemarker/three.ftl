判断map是否为空
<#if kindsMap3?size gt 0>
    map size 大于0
<#else>
    map size 不大于0
</#if>

<#list kindsMap?keys as mKey>
    ${mKey}
    <#assign item = kindsMap[mKey]>
    <#list item as itemValue>
        ${itemValue}
    </#list>
</#list>