package model.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import interfaces.menu.Account;

public class AccountManager extends AbstractFileManager<String, Account>{
	private static AccountManager INSTANCE;
	private Map<String,String> credMap;
	private int lastId;
	
	private AccountManager() {
		super.fixedUsedData = new Account[2];
		this.credMap = new HashMap<>();
	}
	
	public static AccountManager getInstance() {
		if(INSTANCE == null)
			INSTANCE = new AccountManager();
		return INSTANCE;
	}
	
	public Map<String,String> getCredMap() {
		return this.credMap;
	}
	
	@Override
	public boolean readAllFiles() {
		List<File> fileList = null;
		boolean read = true;
		try {
			fileList = this.getAllFiles();
		}catch(NullPointerException e) {
			read = false;
		}
		
		if(read) {
			int lastId = -1, accountsLoaded = 0;
			
            System.out.println("Lettura da file...");
			if(fileList != null) {
				try {
					for(File f : fileList) {
						if(!f.getName().equals("toExport")) {							
							Scanner scan = new Scanner(f);
							lastId = Integer.valueOf(f.getName());
							String username = scan.nextLine();
							String hashedPassword = scan.nextLine();
							int matches = Integer.parseInt(scan.nextLine());
							int wins = Integer.parseInt(scan.nextLine());
							int losses = Integer.parseInt(scan.nextLine());
							Account account = new AccountImpl(username,hashedPassword,lastId,matches,wins,losses);
							this.getCredMap().put(username, hashedPassword);
							this.getDataMap().put(username,account);
							accountsLoaded++;
							scan.close();
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
			}
			System.out.println(accountsLoaded+" account caricati correttamente!");
            this.lastId = lastId;
        }
        return read;
	}

	@Override
	public boolean writeNewFile(Account account) {
		boolean result = false;
    	if(account != null) {
            File credFile = this.getOpenDirectory();
    		String filename = String.valueOf(account.getId());
    		File toWrite = new File(credFile,filename);
    		result = this.writeFile(toWrite, account);
    	}
    	return result;
	}

	@Override
	public boolean updateFile(Account account) {
    	boolean result = false;
    	if(account != null) {    		
    		File credFile = this.getOpenDirectory();
    		String filename = String.valueOf(account.getId());
    		File toUpdate = new File(credFile,filename);
    		result = this.writeFile(toUpdate, account);
    	}
    	return result;
	}
	
	private boolean writeFile(File file, Account account) {
    	boolean result = false;
    	
		try {
			if(!file.exists())
				this.lastId = account.getId();
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(account.getUsername()+"\n"+
					account.getPassword()+"\n"+
					account.getMatches()+"\n"+
					account.getWins()+"\n"+
					account.getLosses());
			myWriter.close();
            result = true;
		} catch (IOException e) {
            System.out.println(e);
			result = false;
		}
		return result;
    }
	
	public int getLastId() {
		return this.lastId;
	}

	@Override
	public void resetData() {
		super.resetData();
		this.credMap.clear();
		for(int i = 0; i < this.fixedUsedData.length; i++) {
			this.fixedUsedData[i] = null;
		}
		this.lastId = -1;
	}

	@Override
	public boolean readFile(String filename) {
		// TODO Auto-generated method stub
		return false;
	}

}
