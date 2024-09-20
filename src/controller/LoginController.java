package controller;

import java.io.FileNotFoundException;

import interfaces.View;
import interfaces.menu.LoginObserver;
import interfaces.Model;

public class LoginController implements LoginObserver{
	private Controller father;
	private Model model;
	private View view;
	
	public LoginController(Model model, View view, Controller father) {
		this.father = father;
		this.model = model;
		this.view = view;
	}
	
	@Override
	public void initLogin() {
		this.view.loginMenu();
	}
	
	@Override
	public void register(String username, String password, String name) {
		if(!username.isBlank() && !password.isBlank() && !name.isBlank()) {
			try {
				boolean result = this.model.getLoginManager().register(username, password);
				if(!result) {
					this.view.getLoginPanel().accountAlreadyRegistered();
				}else {
					this.view.getLoginPanel().signedUpSuccessfully();
				}
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}else {
			this.view.getLoginPanel().signUpFailed();
		}
	}

	@Override
	public void login(int select, String username, String password) {
		int logged = this.model.getLoginManager().login(username, password, select);
		switch(logged) {
		case 0:
			if(this.model.getLoginManager().ready())
				this.father.initMainMenu();
			else
				this.view.getLoginPanel().obscurePanel(select);
			break;
		case 1:
			this.view.getLoginPanel().accountNotFound();
			break;
		case 3:
			this.view.getLoginPanel().alreadyInUse();
			break;
		}
	}

	@Override
	public void disconnect(int select) {
		this.model.getLoginManager().disconnect(select);
	}
	
	@Override
	public void firstMenu() {
		if(this.model.getFileManager().getUsedDataArray(0) != null) {
			this.model.getLoginManager().disconnect(0);			
		}
		if(this.model.getFileManager().getUsedDataArray(1) != null) {
			this.model.getLoginManager().disconnect(1);			
		}
		this.father.initFirstMenu();
	}
}
