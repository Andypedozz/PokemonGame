package model;

import java.util.List;

import interfaces.Pokemon;
import interfaces.Stats;
import interfaces.battle.MoveSet;


public class PokemonImpl implements Pokemon{
	
	private final String name;
	private final List<Type> type;
	private Stats stats;
	private MoveSet moveSet;
	private String iconString;
	private boolean isFainted;
	private boolean isProtect;
	
	public PokemonImpl(String name, List<Type> type, Stats stats, MoveSet moveSet, String iconString) {
		
		this.name = name;
		this.type = type;
		this.stats = stats;
		this.moveSet = moveSet;	
		this.iconString = iconString;
		this.isFainted = false;
		this.isProtect = false;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Type> getType() {
		return this.type;
	}

	@Override
	public Stats getStats() {
		return this.stats;
	}

	@Override
	public MoveSet getMoveSet() {
		return this.moveSet;
	}

	@Override
	public String getIconString() {
		return this.iconString;
	}


	@Override
	public PokemonImpl duplicate() {
		Stats newStats = this.stats.duplicate();
	    MoveSet newMoveSet = this.moveSet.duplicate();
	    return new PokemonImpl(this.name, this.type, newStats, newMoveSet, this.iconString);
	}


	@Override
	public boolean isFainted() {
		return this.isFainted;
	}

	@Override
	public void setIsFainted(boolean isFainted) {
		this.isFainted = isFainted;
	}


	@Override
	public void setIsProtect(boolean isProtect) {
		this.isProtect = isProtect;
	}


	@Override
	public boolean getIsProtect() {
		return this.isProtect;
	}
}
