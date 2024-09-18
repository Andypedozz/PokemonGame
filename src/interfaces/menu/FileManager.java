package interfaces.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface FileManager<K,V> {
	File getOpenDirectory();
	
	void closeFileDirectory();
	
	void openFileDirectory(String filepath) throws FileNotFoundException;
	
	Map<K,V> getDataMap();
	
	List<File> getAllFiles();
	
	V getUsedDataArray(int select);
	
	void setUsedDataArray(int select, V data);
	
	V getUsedDataList(int select);
	
	void setUsedDataList(int index, V data);
	
	boolean readAllFiles();
	
	boolean readFile(String filename);
	
	boolean writeNewFile(V data);
	
	boolean updateFile(V data);
	
	void resetData();
}
