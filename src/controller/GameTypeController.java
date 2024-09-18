package controller;

import interfaces.Model;
import interfaces.View;
import interfaces.menu.GameTypeObserver;

public class GameTypeController implements GameTypeObserver{

	private Model model;
	private View view;
	private Controller father;
	
	@Override
	public void singleBattle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doubleBattle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tripleBattle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initMenu() {
		this.view.gameTypePanel();
	}

}
