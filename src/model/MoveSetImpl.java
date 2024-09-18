package model;

import interfaces.battle.Move;
import interfaces.battle.MoveSet;

public class MoveSetImpl implements MoveSet{
	
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;
	
	public MoveSetImpl(Move move1, Move move2, Move move3, Move move4) {
		
		this.move1 = move1.duplicateMove();
		this.move2 = move2.duplicateMove();
		this.move3 = move3.duplicateMove();
		this.move4 = move4.duplicateMove();
		
	}
	
	public Move getMove1() {
		return this.move1;	
	}
	public Move getMove2() {
		return this.move2;	
	}
	public Move getMove3() {
		return this.move3;	
	}
	public Move getMove4() {
		return this.move4;	
	}
	
	public MoveSet duplicate() {
		
		Move newMove1 = this.move1.duplicateMove();
		Move newMove2 = this.move2.duplicateMove();
		Move newMove3 = this.move3.duplicateMove();
		Move newMove4 = this.move4.duplicateMove();
		return new MoveSetImpl(newMove1, newMove2, newMove3, newMove4);
	}

}
