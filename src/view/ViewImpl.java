package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import interfaces.Pokemon;
import interfaces.View;
import interfaces.ViewObserver;

public class ViewImpl extends JFrame implements View {
	private ViewObserver observer;
	private FirstMenu firstMenu;
	private LoginPanel loginPanel;
	private TeamPanel teamPanel;
	private InfoPanel infoPanel;
	private MainMenu mmPanel;
	private LeaderboardPanel leadPanel;
	private BattlePanel battlePanel;
	private ProfileScreen profilePanel;
	
	public ViewImpl(ViewObserver observer) {
		this.observer = observer;
		this.setSize(900,700);
		this.setMinimumSize(new Dimension(900,700));
		this.setTitle("Pokemon Battle Mania");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void firstMenu() {
		if(this.teamPanel != null) {
			this.remove(teamPanel);
			this.teamPanel = null;
		}
		if(this.loginPanel != null) {
			this.remove(loginPanel);
			this.loginPanel = null;
		}
		this.firstMenu = new FirstMenu(observer);
		this.add(firstMenu);
		this.revalidate();
	}

	// metodo per inserire il panel di login
	public void loginMenu() {
		if(this.firstMenu != null) {
			this.remove(firstMenu);
			this.firstMenu = null;
		}
		if(this.mmPanel != null) {
			this.remove(mmPanel);
			this.mmPanel = null;
		}
		this.loginPanel = new LoginPanel(observer.getLoginObserver(),this);
		this.add(loginPanel);
		this.setResizable(true);
		this.revalidate();
	}
	
	public void profileScreen() {
		if(this.mmPanel != null) {
			this.remove(mmPanel);
			this.mmPanel = null;
		}
		this.profilePanel = new ProfileScreen(observer.getProfileObserver());
		this.add(profilePanel);
		this.validate();
	}
	
	public void mainMenu() {
		if(this.loginPanel != null) {
			this.remove(loginPanel);
			this.loginPanel = null;
		}
		if(this.teamPanel != null) {
			this.remove(teamPanel);
			this.teamPanel = null;
		}
		if(this.leadPanel != null) {
			this.remove(leadPanel);
			this.leadPanel = null;
		}
		if(this.profilePanel != null) {
			this.remove(profilePanel);
			this.profilePanel = null;
		}
		this.mmPanel = new MainMenu(this.observer.getMenuObserver());
		this.add(mmPanel);
		this.revalidate();
	}
	
	public void leaderboard() {
		if(this.mmPanel != null) {
			this.remove(mmPanel);
			this.mmPanel = null;
		}
		if(this.teamPanel != null) {
			this.remove(teamPanel);
			this.teamPanel = null;
		}
		this.leadPanel = new LeaderboardPanel(this.observer.getLeadObserver());
		this.add(leadPanel);
		this.revalidate();
	}
	
	// metodo per inserire il panel di scelta team
	public void teamMenu() {
		if(this.firstMenu != null) {
			this.remove(firstMenu);
			this.firstMenu = null;
		}
		if(this.loginPanel != null) {
			this.remove(loginPanel);
			this.loginPanel = null;
		}
		if(this.teamPanel == null) {
			this.teamPanel = new TeamPanel(observer.getTeamObserver());
		}
		if(this.mmPanel != null) {
			this.remove(mmPanel);
			this.mmPanel = null;
		}
		if(this.infoPanel != null) {
			this.remove(infoPanel);
			this.infoPanel = null;
		}
		if(this.battlePanel != null) {
			this.remove(battlePanel);
			this.battlePanel = null;
		}
		this.add(teamPanel);
		this.setResizable(true);
		this.validate();
	}
	
	public void infoScreen(Pokemon pokemon) {
		this.remove(this.teamPanel);
		this.infoPanel = new InfoPanel(observer);
		this.infoPanel.loadInfo(pokemon);
		this.add(infoPanel);
		this.validate();
	}
	
	public void battleMenu() {
	    if (this.teamPanel != null) {
	        this.remove(teamPanel);
	        this.teamPanel = null; 
	    }
	    if (this.battlePanel == null) {  
	        this.battlePanel = new BattlePanel(); 
	    }
	    this.add(battlePanel);
		this.setLocationRelativeTo(null);
		this.validate();
	}
	
	public BattlePanel getBattlePanel() {	
		return this.battlePanel;
	}
	
	public LoginPanel getLoginPanel() {
		return this.loginPanel;
	}
	
	public TeamPanel getTeamPanel() {
		return this.teamPanel;
	}
	
	public LeaderboardPanel getLeaderboardPanel() {
		return this.leadPanel;
	}

}
