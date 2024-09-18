package view;

import javax.swing.JButton;

import interfaces.Pokemon;

public class LobbyPokemonButton extends JButton {
	private Pokemon pokemon;
	private int index;
	
	public LobbyPokemonButton(int index) {
		super();
		this.index = index;
	}
	
	public LobbyPokemonButton(Pokemon pokemon, int index) {
		super();
		this.index = index;
		this.pokemon = pokemon;
	}
	
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	
	public Pokemon getPokemon() {
		return this.pokemon;
	}
	
	public int getIndex() {
		return this.index;
	}

}
