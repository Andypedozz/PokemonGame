package model.battle;

import interfaces.Pokemon;
import interfaces.battle.Move;

public class AttackAction extends ActionImpl{
	
	private Pokemon pTarget;
	private Move usedMove;
	
	public AttackAction(Pokemon pAttacker, Pokemon pTarget, Move usedMove, int id) {
		
		super(pAttacker, id);
		this.pTarget = pTarget;
		this.usedMove = usedMove;
	}
	
	public Pokemon getTarget() {
		
		return this.pTarget;
	}
	
	public Move getUsedMove() {
		
		return this.usedMove;
	}
	
}
