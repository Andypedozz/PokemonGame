package interfaces.menu;

import interfaces.Pokedex;
import interfaces.Pokemon;
import interfaces.Team;

public interface Lobby {
	int addPokemon(int select);
	
	int removePokemon(int select);

	Pokemon getSelected();

	void deselect();

	void selectPokemon(String name);
	
	Pokedex getPokedex();

	boolean selectFromTeam(int select, int index);
	
	boolean checkReady();
	
	Team getTeam(int select);
}
