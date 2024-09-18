package interfaces;

import interfaces.battle.Match;
import interfaces.menu.LeaderboardObserver;
import interfaces.menu.LoginObserver;
import interfaces.menu.MenuObserver;
import interfaces.menu.ProfileObserver;
import interfaces.menu.TeamObserver;

public interface ViewObserver {
	void initLogin();
	void initTeam();
	void initLeaderboard();
	void initMainMenu();
	void initBattle(Match match);
	void quickPlay();
	void disconnect(int select);
	void initProfile();
	LoginObserver getLoginObserver();
	TeamObserver getTeamObserver();
	BattleObserver getBattleObserver();
	MenuObserver getMenuObserver();
	LeaderboardObserver getLeadObserver();
	ProfileObserver getProfileObserver();
	void initFirstMenu();
}
