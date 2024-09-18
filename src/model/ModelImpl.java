package model;

import model.menu.AccountManager;
import interfaces.Model;
import interfaces.Pokedex;
import interfaces.battle.Match;
import interfaces.menu.Lobby;
import interfaces.menu.LoginManager;
import model.menu.LobbyImpl;
import model.menu.LoginManagerImpl;

public class ModelImpl implements Model{
	private AccountManager fileManager;
	private LoginManager loginManager;
	private Lobby lobby;
	private Pokedex pokedex;
	private Match match;
	private boolean saveEnabled;
		
	public AccountManager getFileManager() {
		if(this.fileManager == null) {
			this.fileManager = AccountManager.getInstance();
		}
		return this.fileManager;
	}	
	
	public LoginManager getLoginManager() {
		if(this.loginManager == null) {
			this.loginManager = LoginManagerImpl.getInstance();
		}
		return this.loginManager;	
	}

	@Override
	public Lobby getLobby() {
		
		if(this.lobby == null) {
			this.lobby = new LobbyImpl();
		}
		return this.lobby;
	}
	
	@Override
	public Lobby getLobby(int teamSize) {
		if(this.lobby == null) {
			this.lobby = new LobbyImpl(teamSize);
		}
		return this.lobby;
	}	
	
	@Override
	public void setMatch(Match match) {
		
		this.match = match;
	}

	@Override
	public boolean isSaveEnabled() {
		return this.saveEnabled;
	}
	
	@Override
	public void setSave(boolean b) {
		this.saveEnabled = b;
	}

	@Override
	public Match getMatch() {
		return this.match;
	}

	@Override
	public void exitLobby() {
		this.lobby = null;
	}
}
