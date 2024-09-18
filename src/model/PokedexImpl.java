package model;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import interfaces.Pokemon;
import model.menu.PokemonLoader;
import interfaces.Pokedex;

public class PokedexImpl implements Pokedex {
	
	public static Pokedex INSTANCE = null;
	private List<Pokemon> list;
	
	public static Pokedex getInstance() {
		if(INSTANCE == null)
			INSTANCE = new PokedexImpl();
		return INSTANCE;
	}
	
	private PokedexImpl() {
		this.list = new LinkedList<Pokemon>();
		PokemonLoader pokemonLoader = new PokemonLoader();
		try {
			pokemonLoader.openFileDirectory(".\\resources\\pokedex");
			pokemonLoader.readAllFiles();
			pokemonLoader.getDataMap().forEach((name, pokemon) -> {
				this.list.add(pokemon);
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public List<Pokemon> getList() {
		return this.list;
	}

	
	public Pokemon getPokemon(String name) {
		for(Pokemon p : this.list) {
			if(p.getName().equals(name))
				return p;
		}
		return null;
	}
}
