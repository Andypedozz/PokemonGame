package test.battle;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import interfaces.Pokedex;
import interfaces.Pokemon;
import interfaces.Team;
import interfaces.battle.Match;
import interfaces.battle.Move;
import model.PokedexImpl;
import model.TeamImpl;
import model.battle.MatchImpl;

public class TestBattleManager {

	Match match = null;
	Team teams[] = new TeamImpl[2];
	
	private void attack(Pokemon attacker, Move move) {
		match.createAttackAction(attacker, move);
		match.addAction(match.getAction(attacker));
	}
	
	private void switchPokemon(Pokemon inBattle, Pokemon toSend) {
		match.createSwitchAction(inBattle, toSend);
		match.addAction(match.getAction(inBattle));
	}
	
	private void initTeams(List<String> names1, List<String> names2) {
		Pokedex pokedex = PokedexImpl.getInstance();
		
		System.out.println("TEAM 1");
		for(int i = 0; i < 3; i++) {
			System.out.println(names1.get(i));
		}
		System.out.println();
		
		System.out.println("TEAM 2");
		for(int i = 0; i < 3; i++) {
			System.out.println(names2.get(i));
		}
		System.out.println();
		
		teams[0] = new TeamImpl();
		teams[1] = new TeamImpl();
		for(int i = 0; i < 3; i++) {
			teams[0].add(pokedex.getPokemon(names1.get(i)).duplicate());
			teams[1].add(pokedex.getPokemon(names2.get(i)).duplicate());
		}
		match = new MatchImpl(teams[0], teams[1]);
	}
	
	@Test
	public void testAttackBoostX2() {
		System.out.println("********** TEST ATTACK BOOST X2 **********");
		List<String> names1 = Arrays.asList("Lucario","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Blaziken","Swampert","Salamence");
		initTeams(names1, names2);
		
		int baseAttack = match.getPokemonInBattle().getStats().getAttack();
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseAttack * 2,match.getPokemonInBattle().getStats().getActualAttack());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseAttack * 3,match.getPokemonInBattle().getStats().getActualAttack());
		match.resetBattle(match.getActionList());
	}
	
	@Test
	public void testAttackBoost() {
		System.out.println("********** TEST ATTACK BOOST **********");
		List<String> names1 = Arrays.asList("Salamence","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Blaziken","Swampert","Lucario");
		initTeams(names1, names2);
		
		int baseAttack = match.getPokemonInBattle().getStats().getAttack();
		System.out.println(match.getPokemonInBattle().getStats().getActualAttack());
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		System.out.println(match.getPokemonInBattle().getStats().getActualAttack());
		assertEquals(baseAttack + (baseAttack / 2), match.getPokemonInBattle().getStats().getActualAttack());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		System.out.println(match.getPokemonInBattle().getStats().getActualAttack());
	}
	
	@Test
	public void testReduceSpDefense() {
		System.out.println("********** TEST REDUCE SPECIAL DEFENSE **********");
		List<String> names1 = Arrays.asList("Staraptor","Infernape","Lucario");
		List<String> names2 = Arrays.asList("Staraptor","Swampert","Salamence");
		initTeams(names1, names2);
		
		int baseSpDefense = match.getPokemonInBattle().getStats().getSpDefense();
		System.out.println(match.getPokemonInBattle().getStats().getActualSpDefense());
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove2());
		match.getBattleManager().startActions(match.getActionList());
		System.out.println(match.getPokemonInBattle().getStats().getActualSpDefense());
		assertEquals(baseSpDefense - (baseSpDefense / 2), match.getPokemonInBattle().getStats().getActualSpDefense());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove2());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(match.getPokemonInBattle().getStats().getActualSpDefense(), 1);
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove2());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(match.getPokemonInBattle().getStats().getActualSpDefense(), 1);
		match.resetBattle(match.getActionList());
	}
	
	@Test
	public void testReduceDefense() {
		System.out.println("********** TEST REDUCE DEFENSE **********");
		List<String> names1 = Arrays.asList("Lucario","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Blaziken","Swampert","Salamence");
		initTeams(names1, names2);
		
		
	}
	
	@Test
	public void testBoostAvoidance() {
		System.out.println("********** TEST BOOST AVOIDANCE **********");
		List<String> names1 = Arrays.asList("Staraptor","Infernape","Lucario");
		List<String> names2 = Arrays.asList("Staraptor","Swampert","Salamence");
		initTeams(names1, names2);
		
		int baseAvoidance = match.getPokemonInBattle().getStats().getAvoidance();
		for(int i = 0; i < 10; i++) {
			attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
			attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
			match.getBattleManager().startActions(match.getActionList());
			assertEquals(baseAvoidance + 15, match.getPokemonInBattle().getStats().getAvoidance());
			baseAvoidance = match.getPokemonInBattle().getStats().getAvoidance();
			match.resetBattle(match.getActionList());
		}
	}
	
	@Test
	public void testRecover() {
		System.out.println("********** TEST RECOVER **********");
		List<String> names1 = Arrays.asList("Blissey","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Blissey","Swampert","Salamence");
		initTeams(names1, names2);
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove2());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove2());
		match.getBattleManager().startActions(match.getActionList());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove3());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove3());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(match.getPokemonInBattle().getStats().getHp(), match.getPokemonInBattle().getStats().getActualHp());
		match.resetBattle(match.getActionList());
	}
	
	@Test
	public void testSpeedBoost() {
    	System.out.println("********** TEST SPEED BOOST **********");
		List<String> names1 = Arrays.asList("Infernape","Zoroark","Staraptor");
		List<String> names2 = Arrays.asList("Blissey","Swampert","Salamence");
		initTeams(names1, names2);
		
		int baseSpeed = match.getPokemonInBattle().getStats().getSpeed();
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove4());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove3());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseSpeed + (baseSpeed / 2), match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove4());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove3());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseSpeed * 2, match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove4());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove3());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals((baseSpeed * 2) + (baseSpeed / 2), match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
	}
	
	@Test
	public void testSpeedBoostX2() {
    	System.out.println("********** TEST SPEED BOOST X2 **********");
		List<String> names1 = Arrays.asList("Zoroark","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Lucario","Swampert","Salamence");
		initTeams(names1, names2);
		
		int baseSpeed = match.getPokemonInBattle().getStats().getSpeed();
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseSpeed * 2, match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseSpeed * 3, match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(baseSpeed * 3, match.getPokemonInBattle().getStats().getActualSpeed());
		match.resetBattle(match.getActionList());
	}
	
	@Test
	public void testKnockout() {
		System.out.println("********** TEST KNOCKOUT **********");
		List<String> names1 = Arrays.asList("Lucario","Infernape","Staraptor");
		List<String> names2 = Arrays.asList("Blaziken","Swampert","Salamence");
		initTeams(names1, names2);
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		match.resetBattle(match.getActionList());
		
		attack(match.getPokemonInBattle(), match.getPokemonInBattle().getMoveSet().getMove1());
		attack(match.getPokemonInBattle2(), match.getPokemonInBattle2().getMoveSet().getMove1());
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(true, match.getPokemonInBattle().isFainted());
		match.resetBattle(match.getActionList());
		
		switchPokemon(match.getPokemonInBattle(), match.getTeam1().getList().get(1));
		switchPokemon(match.getPokemonInBattle2(), match.getTeam2().getList().get(1));
		match.getBattleManager().startActions(match.getActionList());
		assertEquals(match.getPokemonInBattle().getName(), "Infernape");
		assertEquals(match.getPokemonInBattle2().getName(), "Swampert");
		match.resetBattle(match.getActionList());
	}
	
}
