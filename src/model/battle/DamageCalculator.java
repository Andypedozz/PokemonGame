package model.battle;

import java.util.Random;
import interfaces.Pokemon;
import interfaces.battle.Move;
import model.MoveType;
import model.Type;

public class DamageCalculator {
	
	public static int calculateDamage(Pokemon pAttacker, Pokemon pTarget, Move usedMove, double debRes, boolean isProtect) {
		
		if(isProtect) {
				
			return 0;
		}
		else
		{
			int damageCalculate;
			usedMove.getMoveType();
			int pAttack = (usedMove.getMoveType() == MoveType.FISICA)? pAttacker.getStats().getActualAttack() : 
																	   	   pAttacker.getStats().getActualSpAttack(); //attaco o attacco speciale del pokemon attaccante in battaglia
			int targetDefense = (usedMove.getMoveType() == MoveType.FISICA)? pTarget.getStats().getActualDefense():
																		     	 pTarget.getStats().getActualSpDefense();//difesa o difesa speciale del pokemon target in battaglia
			int movePower = usedMove.getPower();
			Random random = new Random();
			int randomNumber = random.nextInt(16) + 85; //numero random aggiunto al calcolo dei danni (Compreso tra 85 e 100)
			double stabBonus = calculateStabBonus(pAttacker, usedMove.getType()); //calcolo dello STAB bonus
			damageCalculate = (int) Math.floor(((((2 * 50 / 5 + 2) * pAttack * movePower / targetDefense) / 50) + 2) * stabBonus * debRes * randomNumber  / 100); //danno calcolato
			return damageCalculate;
		}
	}
	
	// Calcolo per vedere se una mossa é STAB ovvero: se il tipo della mossa
	// corrisponde al tipo del pokemon la mossa diventa più potente
	private static double calculateStabBonus(Pokemon pAttacker, Type moveType) {
	    for (Type pAttackerType : pAttacker.getType()) {
	        if (pAttackerType == moveType) 
	        	return 1.5;
	    }
	        return 1.0; 
	}	
}