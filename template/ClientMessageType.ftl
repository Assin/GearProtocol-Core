package protocol
{

	public class RemoteMessageType{

<#list messages as message>
        /**
		 * ${message.comment}
		 */		
		public static const ${message.type}:int = ${message.id?string.computer};
        </#list>
	}
}