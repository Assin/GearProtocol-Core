package protocol
{
	import protocol.custom.*;
	import flash.utils.Dictionary;

	public class RemoteMessageRegister{

		private var messageClassDict:Dictionary = new Dictionary();

		private static var _instance:RemoteMessageRegister;

		public static function getInstance():RemoteMessageRegister{
			if(_instance == null){
				_instance = new RemoteMessageRegister();
			}
			return _instance;
		}
	
		/**
		 * init
		 */	
		public function init():void{
<#list messages as message>
			//${message.comment}
			this.addMessage(RemoteMessageType.${message.type}, ${message.name});
</#list>		
		}

		/**
		 * add to map
		 */	
		public function addMessage(messageID:int,messageClass:Class):void{
			messageClassDict[messageID] = messageClass;
		}
		/**
		 * get message class by messageID
		 */	
		public function getMessageClass(messageID:int):Class{
			return messageClassDict[messageID];
		}
	}
}