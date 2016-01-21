package utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {

	public static File getOutPutFile(String fileName, boolean createIfNotExist) throws IOException {
		File file = new File(fileName);
		if(!file.exists()) {
			List<File> tList = new ArrayList<File>();
			while(!file.exists()) {
				tList.add(file);
				file = file.getParentFile();
			}
			for(int i = tList.size() - 1; i >= 0; i--) {
				if(i > 0) {
					tList.get(i).mkdir();
				}
				if(createIfNotExist && i == 0) {
					tList.get(i).createNewFile();
				}
			}
			file = tList.get(0);
		}
		return file;
	}
}
