package
{
	
	import flash.utils.ByteArray;
	
	/**
	 * @name 消息类基类
	 * @explain
	 * @author yanghongbin
	 * @create 2011 Oct 31, 2011 2:37:12 PM
	 */
	public class MessageBase implements IMessageBase
	{
		/**
		 * 消息号
		 */
		protected var _messageID:int;
		/**
		 * 消息主体的长度,未压缩
		 */
		protected var _messageLength:int;
		/**
		 * 消息体字节数组
		 */
		protected var _byteArray:ByteArray = new ByteArray();
		
		/**
		 * 消息号对应的常量字符串
		 */
		protected var _messageType:String;
		
		/**
		 * 消息号
		 */
		public function get messageID():int
		{
			return _messageID;
		}
		
		public function get byteArray():ByteArray
		{
			return _byteArray;
		}
		
		public function set byteArray(value:ByteArray):void
		{
			_byteArray = value;
		}
		
		public function toString():String
		{
			return "MessageID = " + _messageID + 
					", MessageType = " + _messageType + 
					", MessageBodyLength = " + _messageLength + "\n";
		}
		
		public function MessageBase()
		{
		}
	}
}