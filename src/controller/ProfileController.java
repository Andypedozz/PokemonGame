package controller;

import java.util.Arrays;
import java.util.List;
import interfaces.Model;
import interfaces.View;
import interfaces.menu.Account;
import interfaces.menu.ProfileObserver;

public class ProfileController implements ProfileObserver {
	private Model model;
	private View view;
	private Controller father;

	public ProfileController(Model model, View view, Controller father) {
		this.model = model;
		this.view = view;
		this.father = father;
	}

	@Override
	public void initProfile() {
		this.view.profileScreen();
	}
	
	@Override
	public List<String> getAccountInfo(int select) {
		Account account = this.model.getFileManager().getUsedDataArray(select);
		List<String> info = Arrays.asList(account.getUsername(),
										  String.valueOf(account.getMatches()),
										  String.valueOf(account.getWins()),
										  String.valueOf(account.getLosses()));
		return info;
	}

	@Override
	public void back() {
		this.father.initMainMenu();
	}
}
