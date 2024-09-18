package view;

import javax.swing.JButton;
import interfaces.Pokemon;
import interfaces.battle.Move;

public class MoveButton extends JButton{
	
	private Move usedMove;
	private Pokemon pAttacker;
	private Pokemon pTarget;
	
	public void setUsedMove(Move move) {
	    this.usedMove = move;
	    this.setText(move.getName() + " " + move.getActualPp() + "/" + move.getPp());
	}

	public void setAttacker(Pokemon pokemon) {
		
		this.pAttacker = pokemon;
	}
	
	public Move getUsedMove() {
		
		return this.usedMove;
	}
	
	public Pokemon getAttacker() {
		
		return this.pAttacker;
	}

	public void setTarget(Pokemon pTarget) {
		
		this.pTarget = pTarget;
	}

	public Pokemon getTarget() {
		
		return this.pTarget;
	}
	
}
