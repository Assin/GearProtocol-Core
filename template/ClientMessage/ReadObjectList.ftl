<#assign var_tempItem = 'temp'+var_listCurrentName+'Item'/>
${""?left_pad(var_tab_count,"	")}var ${var_listCurrentName}Length:int = this.readUnsignedShort();
${""?left_pad(var_tab_count,"	")}for(var ${var_listCurrentName}Index:int = 0; ${var_listCurrentName}Index < ${var_listCurrentName}Length; ${var_listCurrentName}Index++){
<#assign var_tab_count = var_tab_count + 1/>
${""?left_pad(var_tab_count,"	")}var ${var_tempItem}:${var_listType} = new ${var_listType}();
${""?left_pad(var_tab_count,"	")}${var_tempItem}.byteArray = this._byteArray;
${""?left_pad(var_tab_count,"	")}this.${var_listCurrentName}.push(${var_tempItem});
<#assign var_tab_count = var_tab_count - 1/>
${""?left_pad(var_tab_count,"	")}}
