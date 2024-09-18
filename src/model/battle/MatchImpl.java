package model.battle;

import java.util.ArrayList;
import interfaces.Pokemon;
import interfaces.Team;
import interfaces.battle.Action;
import interfaces.battle.Match;
import interfaces.battle.Move;

public class MatchImpl implements Match{
	
	private Team team1;
	private Team team2;
	private Team winner;
	private Pokemon pokemonInBattle;
	private Pokemon pokemonInBattle2;
	private Action action1;
	private Action action2;
	private ArrayList<Action> actionsList;
	private boolean over;
	private BattleManager battleManager;
	
	public MatchImpl(Team team1, Team team2) {
		
		this.team1 = team1;
		this.team2 = team2;
		this.pokemonInBattle = this.team1.getList().get(0);
		this.pokemonInBattle2 = this.team2.getList().get(0);
		this.battleManager = new BattleManager(this);
		this.actionsList = new ArrayList<>();
	}
	
	@Override
    public void createAttackAction(Pokemon pokemon, Move usedMove) {
    	
    	if(pokemon.equals(this.pokemonInBattle)) {
    		
    		this.action1 = new AttackAction(pokemon, pokemonInBattle2, usedMove, 0);
    	}
    	else {
    		
    		this.action2 = new AttackAction(pokemon, pokemonInBattle, usedMove, 0);
    	}
    }
    
	@Override
    public void createSwitchAction(Pokemon pokemon, Pokemon pokemonToSwitch) {
    	
    	if(pokemon.equals(this.pokemonInBattle)) {
    		this.action1 = new SwitchAction(pokemon, pokemonToSwitch, 1);
    	}
    	else {
    		this.action2 = new SwitchAction(pokemon, pokemonToSwitch, 1);
    	}	
    }
    
	@Override
    public void addAction(Action action) {
    	
    	this.actionsList.add(action);
    }
    
	@Override
    public ArrayList<Action> getActionList(){
    	
    	return this.actionsList;
    }
    
	public Action getAction(Pokemon pokemon) {
    	
    	if(pokemon.equals(this.pokemonInBattle)) {
    		return this.action1;
    	}
    	else {
    		return this.action2;
    	}
    }
    
    @Override
    public void setPokemonInBattle(Pokemon pokemon) {
    	
    	this.pokemonInBattle = pokemon;
    }
    
    @Override
    public void setPokemonInBattle2(Pokemon pokemon) {
    	
    	this.pokemonInBattle2 = pokemon;
    }
    
    @Override
    public Pokemon getPokemonInBattle() {
    	
    	return this.pokemonInBattle;
    }
    
    @Override
    public Pokemon getPokemonInBattle2() {
    	
    	return this.pokemonInBattle2;
    }
    
    @Override
    public Team getTeam1() {
    	
    	return this.team1;
    }
    
    @Override
    public Team getTeam2() {
    	
    	return this.team2;
    }
    
    @Override
    public void battle() {
    	
    	this.battleManager.startActions(this.actionsList);
    	resetBattle(this.actionsList);
    }
    
    @Override
    public BattleManager getBattleManager() {
    	
    	return this.battleManager;
    }
    
    @Override
    public void resetBattle(ArrayList<Action> actionsList) {
    	
    	for(Action action : actionsList) {
    		
    		action.getAttacker().setIsProtect(false);
    	}
    	this.actionsList.removeAll(this.actionsList);
    }
    
    @Override
    public boolean isOver() {
    	return this.over;
    }
    
    @Override
    public void setWinner(Team team) {
    	this.winner = team;
    	this.over = true;
    }
    
    @Override
    public Team getWinner() {
    	return this.winner;
    }
}
