package interfaces.battle;

import java.util.ArrayList;

import interfaces.Pokemon;
import interfaces.Team;
import model.battle.BattleManager;

public interface Match {
	
	void createAttackAction(Pokemon pokemon, Move usedMove);
	void createSwitchAction(Pokemon pokemon, Pokemon pokemonToSwitch);
	void addAction(Action action);
    ArrayList<Action> getActionList();
    Action getAction(Pokemon pokemon);
    void setPokemonInBattle(Pokemon pokemon);
    void setPokemonInBattle2(Pokemon pokemon);
    Pokemon getPokemonInBattle();
    Pokemon getPokemonInBattle2();
    Team getTeam1();
    Team getTeam2();
    void battle();
    BattleManager getBattleManager();
    void resetBattle(ArrayList<Action> actionsList);
	boolean isOver();
	void setWinner(Team winner);
	Team getWinner();
}
