${""?left_pad(var_tab_count,"	")}this.writeShort(${var_listName}.length);
${""?left_pad(var_tab_count,"	")}for each(var ${var_listCurrentName}Item:${var_listType} in ${var_listName}){
<#assign var_tab_count = var_tab_count + 1/>
<#--引入写数据的模板-->
<#assign var_listName = var_listCurrentName + 'Item'/>
${""?left_pad(var_tab_count,"	")}this.writeBytes(${var_listName}.byteArray);
<#assign var_tab_count = var_tab_count - 1/>
${""?left_pad(var_tab_count,"	")}}
