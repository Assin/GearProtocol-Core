package
{
	
	
	/**
	 * @name
	 * @explain
	 * @author yanghongbin
	 * @create Nov 14, 2012 6:55:15 PM
	 */
	public class MessageDecodeCustom extends MessageDecodeBase
	{
		
		public function MessageDecodeCustom()
		{
			super();
		}
		
		override protected function packageMessageInfo():void
		{
			super.packageMessageInfo();
			//读取消息ID
			_messageID = readShort();
			_messageLength = readShort(); 
		}
		
		override public function toString():String
		{
			_messageLength = _byteArray.length - 4;
			return super.toString();
		}
	}
}