package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import interfaces.Pokemon;
import interfaces.View;
import interfaces.menu.TeamObserver;
import model.PokedexImpl;
import model.battle.MatchImpl;
import view.LobbyPokemonButton;
import interfaces.Team;
import interfaces.Model;
import interfaces.battle.Match;

public class TeamController implements TeamObserver{
	private Controller father;
	private Model model;
	private View view;
	
	public TeamController(Model model, View view, Controller father) {
		this.father = father;
		this.model = model;
		this.view = view;
	}
	
	// metodo per avviare una schermata di scelta team
	@Override
	public void initTeam() {
		this.model.getLobby();
		this.view.teamMenu();
		loadButtons();
	}
	
	@Override
	public void selectPokemon(String name) {
		this.model.getLobby().selectPokemon(name);
	}

	// MIGLIORABILE
	@Override
	public void addPokemon(int select) {
		int added = this.model.getLobby().addPokemon(select);
		if(added >= 0) {
			this.view.getTeamPanel().addPokemon(select);
			this.deselect();
		}else if(added == -1) {
			this.view.getTeamPanel().pokemonNotSelected();
			this.deselect();
		}
		else if(added == -2){
			this.view.getTeamPanel().teamFull();
			this.deselect();
		}else {
			this.view.getTeamPanel().alreadyPresent();
			this.deselect();
		}
	}

	@Override
	public void deselect() {
		this.model.getLobby().deselect();
		this.view.getTeamPanel().resetSlots();
	}
	
	@Override
	public void infoScreen() {
		if(this.model.getLobby().getSelected() != null) {
			Pokemon pokemon = this.model.getLobby().getSelected();
			this.view.infoScreen(pokemon);
			this.deselect();
		}else
			this.view.getTeamPanel().pokemonNotSelected();
	}


	@Override
	public void removePokemon(int select) {
		int index = this.model.getLobby().removePokemon(select);
		if(index > -1) {
			this.view.getTeamPanel().removePokemon(select,index);
			this.deselect();
		}else
			this.view.getTeamPanel().pokemonNotSelected();
	}

	@Override
	public void backFromTeam() {
		this.model.exitLobby();
		if(this.model.isSaveEnabled())
			this.father.initMainMenu();
		else
			this.view.firstMenu();
	}

	@Override
	public void selectFromTeam(int select, int index) {
		boolean selected = this.model.getLobby().selectFromTeam(select, index);
		if(selected)
			this.view.getTeamPanel().selectFromTeam(select, index);
	}

	@Override
	public void play() {

		boolean ready = this.model.getLobby().checkReady();
		if(ready) {
			Team team1 = this.model.getLobby().getTeam(0);
			Team team2 = this.model.getLobby().getTeam(1);
			Match match = new MatchImpl(team1, team2);
			this.father.initBattle(match);
		}		
		else
			this.view.getTeamPanel().notReady();
	}

	@Override
	public int getPokedexSize() {
		return this.model.getLobby().getPokedex().getList().size();
	}

	private void loadButtons() {
		List<Pokemon> pokemonList = PokedexImpl.getInstance().getList();
		
		LobbyPokemonButton buttons[] = this.view.getTeamPanel().getPokeButtons();
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setPokemon(pokemonList.get(i));
			try {
				BufferedImage image = ImageIO.read(new File(pokemonList.get(i).getIconString()));
				buttons[i].setIcon(new ImageIcon(image));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
