package interfaces.menu;

import java.io.FileNotFoundException;

public interface LoginManager {
	int login(String username, String password, int select);

    boolean register(String username, String password) throws FileNotFoundException;
    
    boolean registerNoWrite(String username, String password);

    boolean disconnect(int select);

    boolean ready();
}
