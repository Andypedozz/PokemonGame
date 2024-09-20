package interfaces.menu;

public interface LoginObserver {
	void initLogin();
	void register(String username, String password, String name);
	void login(int select, String username, String password);
	void disconnect(int select);
	void firstMenu();
}
