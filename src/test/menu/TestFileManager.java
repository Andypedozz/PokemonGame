package test.menu;

import model.menu.AccountManager;

public class TestFileManager {
	
	AccountManager accountManager;
	
	private void init() {
		accountManager = AccountManager.getInstance();
		accountManager.resetData();
	}
	
	public void testReadFromFile() {
		
	}
	
}
