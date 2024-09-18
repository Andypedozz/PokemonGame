package interfaces;

public interface BattleUpdateListener {
    void endTurn();
    void endBattle(Team winningTeam);
    void switchFaintedPokemon(boolean teamAction);
}
