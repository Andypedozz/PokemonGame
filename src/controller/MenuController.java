package controller;

import interfaces.View;
import interfaces.menu.MenuObserver;
import interfaces.Model;

public class MenuController implements MenuObserver{
	private Model model;
	private View view;
	private Controller father;
	
	public MenuController(Model model, View view, Controller father) {
		this.model = model;
		this.view = view;
		this.father = father;
	}
	
	@Override
	public void initMenu() {
		this.view.mainMenu();
	}
	
	@Override
	public void leaderboardScreen() {
		this.father.initLeaderboard();
	}

	@Override
	public void teamScreen() {
		this.father.initTeam();
	}

	@Override
	public void back() {
		this.model.getLoginManager().disconnect(1);
		this.model.getLoginManager().disconnect(0);
		this.father.initLogin();
	}

	@Override
	public void profile() {
		this.father.initProfile();
	}
}
