package interfaces.battle;

import model.MoveType;
import model.MovesEffects;
import model.Type;
public interface Move {
	
	public void setActualPp(int pp);
	public int getActualPp();
	public int getPp();
	public MovesEffects getMoveEffect();
	public int getPrecision();
	public Type getType();
	public String getName();
	public int getPower();
	public String getDescription();
	public Move duplicateMove();
	MoveType getMoveType();
	int getPriority();

}
