package interfaces;

import interfaces.battle.Match;
import view.MoveButton;
import view.PokemonButton;

public interface BattleObserver {
	
	void storeMove(MoveButton button, boolean teamAction);
	void storeSwitchAction(PokemonButton button, boolean teamAction);
	void deleteSelectedActionForTeam1();
	void deleteSelectedActionForTeam2();
	void setSwitchViewForTeam1();
	void setSwitchViewForTeam2();
	void setHealthBarForTeam1(Pokemon pokemon);
	void setIconForTeam1(Pokemon pokemon);
	void setHealthBarForTeam2(Pokemon pokemon);
	void setIconForTeam2(Pokemon pokemon);
	void setTargetsForTeam1(Pokemon pokemon);
	void setTargetsForTeam2(Pokemon pokemon);
	void setAttackersForTeam1(Pokemon pokemon);
	void setAttackersForTeam2(Pokemon pokemon);
	void setViewForTeam1(Pokemon pokemon1, Pokemon pokemon2);
	void setViewForTeam2(Pokemon pokemon1, Pokemon pokemon2);
	void updateView();
	void startBattle(Match match);
	void resetButtons(boolean teamAction);
	void battle();
    void endTurn();
    void endBattle(Team winningTeam);
    void switchFaintedPokemon(boolean teamAction);
}
