<#list var_children as child>
	<#switch child.contentType>
	<#case typeDef.ATTRIBUTE>
	<#--引入写属性的模板-->
	<#assign var_attributeDefType = child.defType/>
	<#assign var_attributeNameTitle = 'this'/>
	<#assign var_attributeName = var_attributeNameTitle + '.' + child.name/>
	<#assign var_attributeLength = child.length/>
	<#assign var_typeDef = typeDef/>
	<#include "/ClientMessage/WriteAttribute.ftl">
	<#break>
	<#case typeDef.OBJECT>
	<#--引入写对象的模板,这里就是自身-->
	<#assign var_objectName = child.name/>
	${""?left_pad(var_tab_count,"	")}this.writeBytes(this.${var_objectName}.byteArray);
	<#break>
	<#case typeDef.LIST>
	<#if child.hasChildren>
	<#--引入写对象集合的模板-->
	<#assign var_list = child/>
	<#assign var_listName = "this." + child.name/>
	<#assign var_listCurrentName = child.name/>
	<#assign var_listType = child.type/>
	<#include "/ClientMessage/WriteObjectList.ftl">
	<#else>
	<#--引入写普通集合的模板-->
	<#assign var_listName = "this." + child.name/>
	<#assign var_listCurrentName = child.name/>
	<#assign var_listDefType = child.defType/>
	<#assign var_listType = child.type/>
	<#assign var_typeDef = typeDef/>
	<#include "/ClientMessage/WriteList.ftl">
	<#break>
	</#if>
	</#switch>
</#list>