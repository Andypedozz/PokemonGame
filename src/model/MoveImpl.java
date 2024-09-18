package model;

import interfaces.battle.Move;

public class MoveImpl implements Move{
	
	private final String name;
	private final Type type;
	private final MovesEffects moveEffect;
	private final int pp;
	private final int power;
	private final int precision;
	private final String description;
	private final MoveType moveType;
	private final int priority;
	private int actualPp;
	
	public MoveImpl(String name, Type type, int pp, int power, int precision, String description, MoveType moveTarget, MovesEffects moveEffect, int priority) {
		
		this.name = name;
		this.type = type;
		this.power = power;
		this.pp = pp;
		this.actualPp = pp;
		this.precision = precision;
		this.description = description;	
		this.moveType = moveTarget;
		this.moveEffect = moveEffect;
		this.priority = priority;
	}
	
	@Override
	public void setActualPp(int actualPp) {
		this.actualPp = actualPp;
	}

	@Override
	public int getActualPp() {
		return this.actualPp;
	}
	
	@Override
	public MovesEffects getMoveEffect() {
		
		return this.moveEffect;
	}
	@Override
	public int getPp() {
		return this.pp;
	}

	@Override
	public int getPrecision() {
		return this.precision;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getPower() {
		return this.power;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
	@Override
	public Type getType() {
		return this.type;
	}
	@Override
	public MoveType getMoveType() {
		return this.moveType;
	}
	@Override
	public MoveImpl duplicateMove() {
		return new MoveImpl(this.name, this.type, this.pp, this.power, this.precision, this.description, this.moveType, this.moveEffect, this.priority);
	}

	@Override
	public int getPriority() {
		return this.priority;
	}
}
