package interfaces;

import java.util.List;

import interfaces.battle.MoveSet;
import model.Type;

public interface Pokemon {
	
	String getName();
	List<Type> getType();
	Stats getStats();
	MoveSet getMoveSet();
	String getIconString();
	Pokemon duplicate();
	void setIsFainted(boolean isFainted);
	boolean isFainted();
	void setIsProtect(boolean isProtect);
	boolean getIsProtect();
}
