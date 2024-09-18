package model.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import interfaces.menu.FileManager;

public abstract class AbstractFileManager<K,V> implements FileManager<K,V>{

    private Map<K,V> dataList;
    protected V fixedUsedData[];
    private LinkedList<V> dynamicUsedData;
    private File directory;
	
    public AbstractFileManager() {
        this.dataList = new HashMap<>();
        this.dynamicUsedData = new LinkedList<V>();
    }
	
    public File getOpenDirectory() {
		return this.directory;
	}

	public void closeFileDirectory() {
		this.directory = null;
	}

	public void openFileDirectory(String filepath) throws FileNotFoundException {
		File file = new File(filepath);
		if(!file.exists())
			throw new FileNotFoundException("Directory non trovata!");
		this.directory = new File(filepath);
	}

	public Map<K, V> getDataMap() {
		return this.dataList;
	}

	public List<File> getAllFiles() {
		List<File> fileList = Arrays.asList(this.directory.listFiles());
        return fileList;
	}

	public V getUsedDataArray(int select) {
		V toReturn = null;
    	try {
    		toReturn = this.fixedUsedData[select];
    	}catch(Exception e) {
    		System.out.println("Errore: indice fuori range! (Indici ammessi 0/1)");
    	}
    	return toReturn;
	}
	
	public void setUsedDataArray(int select, V data) {
    	try {
    		this.fixedUsedData[select] = data;
    	}catch(Exception e) {
    		System.out.println("Errore: indice fuori range! (Indici ammessi 0/1)");
    	}  
	}
	
	public V getUsedDataList(int index) {
		return this.dynamicUsedData.get(index);
	}
	
	public void setUsedDataList(int index, V data) {
		this.dynamicUsedData.set(index, data);
	}

	public abstract boolean readAllFiles();
	
	public abstract boolean readFile(String filename);

	public abstract boolean writeNewFile(V data);

	public abstract boolean updateFile(V data);
	
	
	public void resetData() {
		this.dataList.clear();
		this.dynamicUsedData.clear();
		this.closeFileDirectory();
	}

}
