package interfaces.menu;

import java.util.List;

public interface LeaderboardObserver {
	void initLeaderboard();

	void back();

	List<Account> getAccounts();
}
