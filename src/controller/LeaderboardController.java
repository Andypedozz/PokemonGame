
package controller;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import interfaces.View;
import interfaces.menu.Account;
import interfaces.menu.LeaderboardObserver;
import interfaces.Model;

public class LeaderboardController implements LeaderboardObserver {
	private Model model;
	private View view;
	private Controller father;

	public LeaderboardController(Model model, View view, Controller father) {
		this.model = model;
		this.view = view;
		this.father = father;
	}

	@Override
	public void initLeaderboard() {
		this.view.leaderboard();
	}

	@Override
	public List<Account> getAccounts() {
		List<Account> accounts = new LinkedList<>();
		this.model.getFileManager().getDataMap().forEach((s, a) -> {
			accounts.add(model.getFileManager().getDataMap().get(s));
		});
		
		accounts.sort(new Comparator<Account>() {

			@Override
			public int compare(Account o1, Account o2) {
				return o2.getWins() - o1.getWins();
			}

		});
		return accounts;
	}

	public void back() {
		this.father.initMainMenu();
	}
}
