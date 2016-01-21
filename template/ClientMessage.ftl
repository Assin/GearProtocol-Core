package protocol.custom
{
	import flash.utils.ByteArray;

	/**
	 * file name: ${messageVO.name}.as
	 * comment: ${messageVO.comment}
	 * author: yanghongbin
	 */
	public class ${messageVO.name} extends <#if messageVO.messageTypeID == 1>MessageEncodeCustom<#else>MessageDecodeCustom</#if>
	{
		<#--要输出的基本属性-->
		<#list messageVO.children as child>
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

		override public function get byteArray():ByteArray
		{
		<#--如果是一个返回消息，那么不能获取byteArray-->
		<#if messageVO.messageTypeID == 1>

			_byteArray.length = 4;
			_byteArray.position = 4;
			

			<#assign var_tab_count = 3/>
			<#assign var_children = messageVO.children/>
			<#assign var_attributeNameTitle = 'this'/>
			<#assign var_listNameTitle = 'this'/>
			<#assign var_listName = 'this'/>
			<#include "/ClientMessage/WriteByteArray.ftl">

			this.packageByteArray();
			return this._byteArray;
		<#else>
			throw new Error("${messageVO.name}.byteArray(getter)");
			return null;
		</#if>
		}

		override public function set byteArray(value:ByteArray):void
		{
		<#--如果是一个发送消息，那么不能注入byteArray-->
		<#if messageVO.messageTypeID == 1>
			throw new Error("${messageVO.name}.byteArray(setter)");
		<#else>

			if(value.length < 4) return;
			this._byteArray = value;

			this.packageMessageInfo();
			

			<#assign var_tab_count = 3/>
			<#assign var_children = messageVO.children/>
			<#assign var_attributeNameTitle = 'this'/>
			<#assign var_listNameTitle = 'this'/>
			<#assign var_listName = ''/>
			<#include "/ClientMessage/ReadByteArray.ftl">
			
		</#if>
		}
		
		public function ${messageVO.name}()
		{
			this._messageID = ${messageVO.id?string.computer};
			this._messageType = "${messageVO.type}";
		}
				
		
		override public function toString():String {
			var str:String = "";
			<#list messageVO.children as child>
			<#switch child.contentType>
			<#case typeDef.ATTRIBUTE>
			<#case typeDef.OBJECT>
			   str += "${child.name} = " + ${child.name}.toString() + "\n";
			<#break>
			<#case typeDef.LIST>
			   for (var ${child.name}Index:int = 0; ${child.name}Index < ${child.name}.length; ${child.name}Index++)
			   {
				  str += "${child.name}["+${child.name}Index+"] = " + ${child.name}[${child.name}Index].toString() + ", ";
			   }
			   str += "\n";
			<#break>
			</#switch>
			</#list>
			return super.toString() + str;
		}

		public function clear():void {
			_byteArray.position = 0;
			_byteArray.length = 0;
			_byteArray.clear();
		}
		
	}
}