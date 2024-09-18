package interfaces;

import view.BattlePanel;
import view.LeaderboardPanel;
import view.LoginPanel;
import view.TeamPanel;

public interface View {
	void firstMenu();
	
	void loginMenu();
	
	void profileScreen();
	
	void mainMenu();
	
	void leaderboard();
	
	void teamMenu();
	
	void infoScreen(Pokemon pokemon);
	
	void battleMenu();
	
	BattlePanel getBattlePanel();
	
	LoginPanel getLoginPanel();
	
	TeamPanel getTeamPanel();
	
	LeaderboardPanel getLeaderboardPanel();
}
