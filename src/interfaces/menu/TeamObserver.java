package interfaces.menu;

public interface TeamObserver {
	void initTeam();
	void backFromTeam();
	void selectPokemon(String name);
	void addPokemon(int select);
	void deselect();
	void infoScreen();
	void removePokemon(int select);
	void selectFromTeam(int select, int index);
	int getPokedexSize();
	void play();
}
