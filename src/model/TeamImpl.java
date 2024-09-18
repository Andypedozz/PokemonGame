package model;

import java.util.LinkedList;
import java.util.List;
import interfaces.Pokemon;
import interfaces.Team;

public class TeamImpl implements Team{
	
	private List<Pokemon> team;
	private int teamSize;
	
	public TeamImpl() {
		this.team = new LinkedList<Pokemon>();
		this.teamSize = 3;
	}
	
	public TeamImpl(int teamSize) {
		this.team = new LinkedList<Pokemon>();
		this.teamSize = teamSize;
	}
	
	public TeamImpl(List<Pokemon> team) {
		this.team = team;
	}
	
	@Override
	public void add(Pokemon pkmn) {
		this.team.add(pkmn);
	}
	
	@Override
	public void remove(int index) {
		this.team.remove(index);
	}
	
	@Override
	public void remove(Pokemon pkmn) {
		this.team.remove(pkmn);
	}
	
	@Override
	public List<Pokemon> getList(){
		return this.team;
	}
	
	@Override
	public int getTeamMaxSize() {
		return teamSize;
	}
}
