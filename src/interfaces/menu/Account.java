package interfaces.menu;

public interface Account {
	String getUsername();
	
	String getPassword();
	
	int getId();
	
	int getMatches();
	
	void setMatches(int matches);
	
	int getWins();
	
	void setWins(int wins);
	
	int getLosses();
	
	void setLosses(int losses);
	
    boolean isLogged();

    void setLogged(boolean logged);
}
