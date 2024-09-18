package interfaces;

import java.util.List;

public interface Team {
	
	List<Pokemon> getList();

	void add(Pokemon pkmn);

	void remove(int index);

	void remove(Pokemon pkmn);

	int getTeamMaxSize();
}
