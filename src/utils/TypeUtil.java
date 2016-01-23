package utils;

import data.ProgramLanguageType;
import data.TypeDef;

/*TypeDef.java
 * yanghongbin
 */

public class TypeUtil {
	public static String parseDefTypeToRealType(String programLanguageType, String defType) {
		String str = "";
		// 判断语言找到对应方法
		if (programLanguageType.equals(ProgramLanguageType.ACTIONSCRIPT3)) {
			str = GetActionScript3RealType(defType);
		}
		if (str == "") {
			try {
				throw new Exception("ERROR_PROPRETY_TYPE: " + defType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	public static String GetActionScript3RealType(String defType) {
		String str = "";
		String def = defType;
		TypeDef td = new TypeDef();
		if (def.equals(td.GP_INT32)) {
			str = "int";
		} else if (def.equals(td.GP_INT16)) {
			str = "int";
		} else if (def.equals(td.GP_STRING)) {
			str = "String";
		} else if (def.equals(td.GP_FLOAT)) {
			str = "Number";
		} else if (def.equals(td.GP_INT8)) {
			str = "int";
		} else if (def.equals(td.GP_INT64)) {
			str = "String";
		} else if (def.equals(td.GP_DOUBLE)) {
			str = "Number";
		}
		if (str == "") {
			try {
				throw new Exception("ERROR_PROPRETY_TYPE: " + defType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}
