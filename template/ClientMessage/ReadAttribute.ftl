<#assign var_tab_count = var_tab_count/>
<#switch var_attributeDefType>
<#case var_typeDef.GP_DOUBLE>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readDouble();
<#break>
<#case var_typeDef.GP_INT32>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readInt();
<#break>
<#case var_typeDef.GP_INT16>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readShort();
<#break>
<#case var_typeDef.GP_STRING>
<#if var_attributeLength == "0">
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readLongString();		
<#else>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readString(${var_attributeLength});		
</#if>
<#break>
<#case var_typeDef.GP_FLOAT>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readFloat();
<#break>
<#case var_typeDef.GP_INT8>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readByte();
<#break>
<#case var_typeDef.GP_INT64>
${""?left_pad(var_tab_count,"	")}${var_attributeName} = this.readLong();
<#break>
</#switch>
