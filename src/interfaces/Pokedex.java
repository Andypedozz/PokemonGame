package interfaces;

import java.util.List;

public interface Pokedex {
	List<Pokemon> getList();
	Pokemon getPokemon(String name);
}
