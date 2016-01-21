package
{
	
	
	/**
	 * @name
	 * @explain
	 * @author yanghongbin
	 * @create Nov 14, 2012 2:17:59 PM
	 */
	public class MessageEncodeCustom extends MessageEncodeBase
	{
		public function MessageEncodeCustom()
		{
			super();
		}
		
		override protected function packageByteArray():void
		{
			//计算消息长度，未加密  减去包头4的长度
			_messageLength = _byteArray.length - 4;
			super.packageByteArray();
			//写入封包头标志
			_byteArray.position = 0;
			_byteArray.writeShort(_messageID);
			_byteArray.writeShort(_messageLength);
			//归位到原位
			_byteArray.position = 0;
		}
		
	}
}