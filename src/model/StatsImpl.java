package model;

import interfaces.Stats;

public class StatsImpl implements Stats{
	
	//Attributi di base del pokemon
	private final int hp;
	private final int attack;
	private final int defense;
	private final int spAttack;
	private final int spDefense;
	private final int speed;
	
	//Attributi del pokemon nel corso della battaglia
	private int actualHp;
	private int actualAttack;
	private int actualDefense;
	private int actualSpAttack;
	private int actualSpDefense;
	private int actualSpeed;
	private int precision;
	private int avoidance;
	
	public StatsImpl(int hp, int attack, int defense, int spAttack, int spDefense, int speed) {
		
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.speed = speed;
		this.actualHp = hp;
		this.actualAttack = attack;
		this.actualDefense = defense;
		this.actualSpAttack = spAttack;
		this.actualSpDefense = spDefense;
		this.actualSpeed = speed;
		this.precision = 100;
		this.avoidance = 100;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public int getAttack() {
		return this.attack;
	}

	@Override
	public int getDefense() {
		return defense;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void setActualHp(int actualHp) {
		this.actualHp = actualHp;
		
	}

	@Override
	public int getActualHp() {
		return this.actualHp;
	}

	@Override
	public void setActualAttack(int actualAttack) {
		this.actualAttack = actualAttack;
	}

	@Override
	public int getActualAttack() {
		return this.actualAttack;
	}

	@Override
	public void setActualDefense(int actualDefense) {
		this.actualDefense = actualDefense;
	}

	@Override
	public int getActualDefense() {
		return this.actualDefense;
	}

	@Override
	public void setActualSpeed(int actualSpeed) {
		this.actualSpeed = actualSpeed;
	}

	@Override
	public int getActualSpeed() {
		return this.actualSpeed;
	}

	@Override
	public int getSpAttack() {
		return this.spAttack;
	}

	@Override
	public int getSpDefense() {
		return this.spDefense;
	}

	@Override
	public void setActualSpAttack(int actualSpAttack) {
		this.actualSpAttack = actualSpAttack;
	}

	@Override
	public int getActualSpAttack() {
		return this.actualSpAttack;
	}

	@Override
	public void setActualSpDefense(int actualSpDefense) {
		this.actualSpDefense = actualSpDefense;
	}

	@Override
	public int getActualSpDefense() {
		return this.actualSpDefense;
	}

	@Override
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	@Override
	public void setAvoidance(int avoidance) {
		this.avoidance = avoidance;
	}

	@Override
	public int getPrecision() {
		return this.precision;
	}

	@Override
	public int getAvoidance() {
		return this.avoidance;
	}
	
	public Stats duplicate() {
		
		return new StatsImpl(this.hp, this.attack, this.defense, this.spAttack, this.spDefense, this.speed);
	}
}
