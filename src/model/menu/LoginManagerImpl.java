package model.menu;

import java.io.FileNotFoundException;

import interfaces.menu.Account;
import interfaces.menu.LoginManager;

public class LoginManagerImpl implements LoginManager {
    private static LoginManager INSTANCE;
    private AccountManager fileManager;
    private boolean logged[];

    private LoginManagerImpl() {
        this.fileManager = AccountManager.getInstance();
        this.logged = new boolean[2];
    }

    public static LoginManager getInstance() {
        if(INSTANCE == null)
            INSTANCE = new LoginManagerImpl();
        return INSTANCE;
    }

    /**
     * 
     * @param username
     * @param password
     * @param select
     * @return 0 = login con successo
     * 		   1 = account non trovato
     * 		   2 = Profilo occupato
     * 		   3 = Account già in uso
     */
    public int login(String username, String password, int select) {
    	int exit = 0;
    	String hashedPassword = Hashing.hashString(password);
    	String storedHashedPassword = this.fileManager.getCredMap().get(username);
    	
    	if(this.fileManager.getCredMap().containsKey(username) && storedHashedPassword.equals(hashedPassword)) {
    		Account toLogin = this.fileManager.getDataMap().get(username);
    		if(!toLogin.isLogged()) {
    			if(this.fileManager.getUsedDataArray(select) == null) {
    				this.fileManager.setUsedDataArray(select, toLogin);
    				toLogin.setLogged(true);
    				this.logged[select] = true;
    				exit = 0;
    			}else{
    				System.out.println("Profilo occupato!");
    				exit = 2;
    			}
    		}else{
    			System.out.println("Account già in uso!");
    			exit = 3;
    		}
    	}else {
    		System.out.println("Account non trovato!");
    		exit = 1;
    	}
    	
    	return exit;
    }

    public boolean register(String username, String password) throws FileNotFoundException {
        // casistiche di successo: username non è già presente
        boolean result = false;

        // se non c'è
        if(!this.fileManager.getCredMap().containsKey(username)) {
            String hashedPassword = Hashing.hashString(password);
            int id = this.fileManager.getLastId() + 1;
            Account toRegister = new AccountImpl(username, hashedPassword,id);
            this.fileManager.getCredMap().put(username, hashedPassword);
            this.fileManager.getDataMap().put(username, toRegister);
            this.fileManager.writeNewFile(toRegister);
            System.out.println("Registrato con successo");
            result = true;
        }else{
            System.out.println("Username già utilizzato!");
        }
        return result;
    }
    
    public boolean registerNoWrite(String username, String password) {
        // casistiche di successo: username non è già presente
        boolean result = false;

        // se non c'è
        if(!this.fileManager.getCredMap().containsKey(username)) {
            String hashedPassword = Hashing.hashString(password);
            Account toRegister = new AccountImpl(username, hashedPassword);
            this.fileManager.getCredMap().put(username, hashedPassword);
            this.fileManager.getDataMap().put(username, toRegister);
            System.out.println("Registrato con successo");
            result = true;
        }else{
            System.out.println("Username già utilizzato!");
        }
        return result;
    }

    public boolean disconnect(int select) {
        boolean result = true;

        if(this.fileManager.getUsedDataArray(select) != null) {
			this.fileManager.getUsedDataArray(select).setLogged(false);
			System.out.println("Profilo "+select+" ("+this.fileManager.getUsedDataArray(select).getUsername()+") disconnesso");
			this.fileManager.setUsedDataArray(select, null);
			this.logged[select] = false;
		}else{
			System.out.println("Profilo "+select+" non loggato!");
			result = false;
		}
        return result;
    }

    public boolean ready() {
        return this.logged[0] && this.logged[1];
    }
}
