package protocol.vo
{
	import flash.utils.ByteArray;
	

	public class ${messageContentVO.type} extends MessageVOCustom
	{
		<#list messageContentVO.children as child>
		/**${child.comment}*/
		<#switch child.contentType>
		<#case typeDef.ATTRIBUTE>
		public var ${child.name}:${child.type};
		<#break>
		<#case typeDef.OBJECT>
		public var ${child.name}:${child.type} = new ${child.type}();
		<#break>
		<#case typeDef.LIST>
		public var ${child.name}:Vector.<${child.type}> = new Vector.<${child.type}>();
		<#break>
		</#switch>
		</#list>
		
		override public function toString():String{
			var str:String = "";
			<#include "/ClientClass/toString.ftl">
			return str;
		}
		
		override public function get byteArray():ByteArray{
		    <#assign var_tab_count = 3/>
			<#assign var_children = messageContentVO.children/>
			<#include "/ClientMessage/WriteByteArray.ftl">
			return this._byteArray;
		}
		
		override public function set byteArray(value:ByteArray):void
		{
			this._byteArray = value;
			<#assign var_tab_count = 3/>
			<#assign var_children = messageContentVO.children/>
			<#include "/ClientMessage/ReadByteArray.ftl">
			
		}
	}
}