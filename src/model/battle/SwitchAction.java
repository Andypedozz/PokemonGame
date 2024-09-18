package model.battle;

import interfaces.Pokemon;

public class SwitchAction extends ActionImpl{
	
	private Pokemon pokemonToSwitch;
	
	public SwitchAction(Pokemon pAttacker, Pokemon pokemonToSwitch, int id) {
		super(pAttacker, id);
		this.pokemonToSwitch = pokemonToSwitch;
	}
	
	public Pokemon getPokemonToSwitch() {
		
		return this.pokemonToSwitch;
	}
}
