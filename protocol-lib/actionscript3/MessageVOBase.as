package
{
	
	import flash.utils.ByteArray;
	import flash.utils.Endian;
	
	public class MessageVOBase extends MessageBase
	{
		
		public function MessageVOBase()
		{
			super();
		}
		
		protected function readUnsignedShort():int
		{
			return _byteArray.readUnsignedShort();
		}
		
		protected function readDouble():Number
		{
			return _byteArray.readDouble();
		}
		
		protected function readInt():int
		{
			return _byteArray.readInt();
		}
		
		protected function readShort():int
		{
			return _byteArray.readShort();
		}
		
		
		protected function readFloat():Number
		{
			return _byteArray.readFloat();
		}
		
		protected function readByte():int
		{
			return _byteArray.readByte();
		}
		
		/**
		 * 读取8字节的int64类型,返回16进制表示的字符串
		 * @return String
		 */
		protected function readLong():String
		{
			var guid:String = "";
			for (var i:int = 0; i < 8; i++)
			{
				var read:String = _byteArray.readUnsignedByte().toString(16);
				if (read.length == 1)
				{
					read = "0" + read;
				}
				guid = read + guid;
			}
			return guid;
		}
		
		/**
		 * 读取指定编码格式的定长字符串，默认UTF-8编码格式
		 * @param len 字节长度
		 * @param charSet 编码格式
		 * @return String
		 */
		protected function readString(len:int, charSet:String = "UTF-8"):String
		{
			try
			{
				return _byteArray.readMultiByte(len, charSet);
			} catch (e:Error)
			{
				return "null";
			}
			return "null";
		}
		
		/**
		 * 读取指定编码格式的变长字符串，默认UTF-8编码格式
		 * @param charSet 编码格式
		 * @return String
		 */
		protected function readLongString(charSet:String = "UTF-8"):String
		{
			try
			{
				var len:uint = _byteArray.readShort();
				return _byteArray.readMultiByte(len, charSet);
			} catch (e:Error)
			{
				return "null";
			}
			return "null";
		}
		
		override public function toString():String
		{
			return super.toString();
		}
		
		protected function writeUnsignedShort(value:int):void
		{
			_byteArray.writeByte(value);
		}
		
		protected function writeDouble(value:Number):void
		{
			_byteArray.writeDouble(value);
		}
		
		protected function writeInt(value:int):void
		{
			_byteArray.writeInt(value);
		}
		
		protected function writeShort(value:int):void
		{
			_byteArray.writeShort(value);
		}
		
		
		protected function writeFloat(value:Number):void
		{
			_byteArray.writeFloat(value);
		}
		
		protected function writeByte(value:int):void
		{
			_byteArray.writeByte(value);
		}
		
		protected function writeBytes(value:ByteArray):void
		{
			_byteArray.writeBytes(value);
		}
		
		/**
		 * 使用指定编码格式写入定长字符串
		 * @param str 要写入的字符串
		 * @param len 字符串字节长度
		 * @param charSet 字符串，默认为UTF-8
		 */
		protected function writeString(str:String, len:int, charSet:String = "UTF-8"):void
		{
			var bytes:ByteArray = new ByteArray();
			bytes.endian = Endian.LITTLE_ENDIAN;
			bytes.writeMultiByte(str, charSet);
			var blank:int = len - bytes.length;
			if (blank < 0) //字符串超出了字节长度
			{
				_byteArray.length += len;
			} else
			{
				_byteArray.writeBytes(bytes);
				_byteArray.length += blank;
				_byteArray.position += blank;
			}
		}
		
		/**
		 * 写入一个8字节的int64的id
		 * @param longId 字符串表示的16进制长id,长度固定为16
		 */
		protected function writeLong(longId:String):void
		{
			longId = parseStringToLong(longId);
			if (longId.length != 16)
			{
				_byteArray.writeDouble(0);
				return ;
			}
			for (var j:int = 0; j < 8; j++)
			{
				var bits:String = longId.substr(longId.length - 2 - j * 2, 2);
				var temp:int = parseInt(bits, 16);
				_byteArray.writeByte(temp);
			}
		}
		
		/**
		 * 使用指定编码格式写入变长字符串
		 * @param str 要写入的字符串
		 * @param charSet 字符格式，默认为UTF-8
		 */
		protected function writeLongString(str:String, charSet:String = "UTF-8"):void
		{
			if (str != null)
			{
				var bytes:ByteArray = new ByteArray();
				bytes.endian = Endian.LITTLE_ENDIAN;
				bytes.writeMultiByte(str, charSet);
				_byteArray.writeShort(bytes.length);
				_byteArray.writeMultiByte(str, charSet);
				bytes.clear();
				bytes = null;
			}
		}
		
		/**
		 * 转换int为long型
		 * @param value
		 * @return
		 *
		 */
		private function parseStringToLong(value:String):String
		{
			var str:String = "";
			var s:String = value;
			var num:int = 16 - value.length;
			
			for (var i:int = 0; i < num; i++)
			{
				str += "0";
			}
			return str + s;
		}
	}
}