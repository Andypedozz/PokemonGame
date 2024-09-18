package model.battle;

import java.util.Comparator;

import interfaces.battle.Action;

public class SpeedComparator implements Comparator<Action>{

	@Override
    public int compare(Action action1, Action action2) {
        
        if (Integer.compare(action2.getId(), action1.getId()) != 0) {
            return Integer.compare(action2.getId(), action1.getId());
        } else if (action1 instanceof AttackAction && action2 instanceof AttackAction) {
               AttackAction attackAction1 = (AttackAction) action1;
               AttackAction attackAction2 = (AttackAction) action2;

                if (Integer.compare(attackAction2.getUsedMove().getPriority(), attackAction1.getUsedMove().getPriority()) != 0) {
                    return Integer.compare(attackAction2.getUsedMove().getPriority(), attackAction1.getUsedMove().getPriority());
                } else {
                    int speedComparison = Integer.compare(attackAction2.getAttacker().getStats().getActualSpeed(), attackAction1.getAttacker().getStats().getActualSpeed());

                    if (speedComparison == 0) {
                        // In caso di parità, utilizza l'hash code delle istanze coinvolte
                        return Integer.compare(System.identityHashCode(action1), System.identityHashCode(action2));
                    } else {
                        return speedComparison;
                    }
                }
            } else {
                return Integer.compare(System.identityHashCode(action2), System.identityHashCode(action1));
            }
        }
    }
