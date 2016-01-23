<#assign var_tab_count = var_tab_count/>
<#switch var_attributeDefType>
<#case var_typeDef.GP_DOUBLE>
${""?left_pad(var_tab_count,"	")}this.writeDouble(${var_attributeName});
<#break>
<#case var_typeDef.GP_INT32>
${""?left_pad(var_tab_count,"	")}this.writeInt(${var_attributeName});
<#break>
<#case var_typeDef.GP_INT16>
${""?left_pad(var_tab_count,"	")}this.writeShort(${var_attributeName});
<#break>
<#case var_typeDef.GP_STRING>
<#if var_attributeLength == "0">
${""?left_pad(var_tab_count,"	")}this.writeLongString(${var_attributeName});
<#else>
${""?left_pad(var_tab_count,"	")}this.writeString(${var_attributeName}, ${var_attributeLength});
</#if>
<#break>
<#case var_typeDef.GP_FLOAT>
${""?left_pad(var_tab_count,"	")}this.writeFloat(${var_attributeName});
<#break>
<#case var_typeDef.GP_INT8>
${""?left_pad(var_tab_count,"	")}this.writeByte(${var_attributeName});
<#break>
<#case var_typeDef.GP_INT64>
${""?left_pad(var_tab_count,"	")}this.writeLong(${var_attributeName});
<#break>
</#switch>