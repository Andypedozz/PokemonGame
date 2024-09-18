package interfaces.battle;

public interface MoveSet {
	
	Move getMove1();
	Move getMove2();
	Move getMove3();
	Move getMove4();
	MoveSet duplicate();
}
