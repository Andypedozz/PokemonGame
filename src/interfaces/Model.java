package interfaces;

import interfaces.battle.Match;
import interfaces.menu.Lobby;
import interfaces.menu.LoginManager;
import model.menu.AccountManager;

public interface Model {
	AccountManager getFileManager();
	LoginManager getLoginManager();
	Lobby getLobby();
	Lobby getLobby(int teamSize);
	void setMatch(Match match);
	Match getMatch();
	void setSave(boolean b);
	void exitLobby();
	boolean isSaveEnabled();
}
