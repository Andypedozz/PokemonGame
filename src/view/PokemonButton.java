package view;

import javax.swing.JButton;

import interfaces.Pokemon;

public class PokemonButton extends JButton{
	
	private Pokemon pAttacker;
	private Pokemon pokemonToSwitch;
	
	public void setAttacker(Pokemon pokemon) {
		
		this.pAttacker = pokemon;
	}
	
	public Pokemon getAttacker() {
		
		return this.pAttacker;
	}
	
	public void setPokemonToSwitch(Pokemon pokemon) {
		
		this.setText(pokemon.getName());
		this.pokemonToSwitch = pokemon;
	}
	
	public Pokemon getPokemonToSwitch() {
		
		return this.pokemonToSwitch;
	}
	
}
