package controller;

import interfaces.BattleObserver;
import interfaces.Model;
import interfaces.View;
import interfaces.ViewObserver;
import interfaces.battle.Match;
import interfaces.menu.LeaderboardObserver;
import interfaces.menu.LoginObserver;
import interfaces.menu.MenuObserver;
import interfaces.menu.ProfileObserver;
import interfaces.menu.TeamObserver;
import model.ModelImpl;
import model.PokedexImpl;
import view.ViewImpl;

public class Controller implements ViewObserver {
	// MODELLO E VISTA
	private Model model;
	private View view;
	
	// SOTTO-CONTROLLER
	private LoginController loginController;
	private TeamController teamController;
	private BattleController battleController;
	private MenuController menuController;
	private LeaderboardController leadController;
	private ProfileController profileController;
	
	// controller start
	public void start() {
		this.model = new ModelImpl();
		
		try {
			this.model.getFileManager().openFileDirectory(".\\accountdata\\accounts");
			this.model.getFileManager().readAllFiles();
			PokedexImpl.getInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.view = new ViewImpl(this);
		this.view.firstMenu();
	}
	
	@Override
	public void initLogin() {
		this.model.setSave(false);
		this.loginController = new LoginController(model,view,this);
		this.loginController.initLogin();
	}

	@Override
	public void initTeam() {
		this.teamController = new TeamController(model,view,this);
		this.teamController.initTeam();
	}

	@Override
	public void initBattle(Match match) {
		this.battleController = new BattleController(view, model,this);
		this.battleController.startBattle(match);
	}

	@Override
	public void disconnect(int select) {
		this.loginController.disconnect(select);
	}

	@Override
	public LoginObserver getLoginObserver() {
		return this.loginController;
	}

	@Override
	public TeamObserver getTeamObserver() {
		return this.teamController;
	}

	@Override
	public BattleObserver getBattleObserver() {
		return this.battleController;
	}

	@Override
	public void initLeaderboard() {
		this.leadController = new LeaderboardController(model,view,this);
		this.leadController.initLeaderboard();
	}

	@Override
	public void initMainMenu() {
		this.model.setSave(true);
		this.menuController = new MenuController(model,view,this);
		this.menuController.initMenu();
	}
	
	@Override
	public MenuObserver getMenuObserver() {
		return this.menuController;
	}

	@Override
	public LeaderboardObserver getLeadObserver() {
		return this.leadController;
	}

	@Override
	public void quickPlay() {
		this.model.setSave(false);
		this.initTeam();
	}

	@Override
	public void initProfile() {
		this.profileController = new ProfileController(model,view,this);
		this.profileController.initProfile();
	}

	@Override
	public ProfileObserver getProfileObserver() {
		return this.profileController;
	}

	@Override
	public void initFirstMenu() {
		this.loginController = null;
		this.view.firstMenu();
	}

}
