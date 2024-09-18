package model.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;
import interfaces.BattleObserver;
import interfaces.Pokemon;
import interfaces.Team;
import interfaces.battle.Action;
import interfaces.battle.Match;
import interfaces.battle.Move;
import model.MovesEffects;
import model.Type;

public class BattleManager {

	private Match match;
	private String message;

	public BattleManager(Match match) {
		this.match = match;
	}

	public void startActions(ArrayList<Action> actions) {
		this.message = "";
		boolean battleEnded = false;
		actions = setAttackOrder(actions);
		for (Action action : actions) {
			if (battleEnded) { // Se la battaglia è terminata, esci dal ciclo
	            break;
	        }
			if (action instanceof SwitchAction) {
				switchPokemon(action.getAttacker(), ((SwitchAction) action).getPokemonToSwitch());
			} else if(((AttackAction) action).getUsedMove().getActualPp() > 0) {

				Move usedMove = ((AttackAction) action).getUsedMove();
				Pokemon pAttacker = ((AttackAction) action).getAttacker();
				Pokemon pTarget = (pAttacker.equals(match.getPokemonInBattle())) ? match.getPokemonInBattle2()
						: match.getPokemonInBattle();
				usedMove.setActualPp(usedMove.getActualPp() - 1);
				setBattleMessage(pAttacker.getName() + " usa " + usedMove.getName() + "\n");
				handleEffect(usedMove.getMoveEffect(), pAttacker, pTarget, usedMove);
				
				// handle knockout
				if (pTarget.getStats().getActualHp() == 0) {
	                knockout(pTarget);
	                battleEnded = true; 
	            }
				
			}
		}
		printBattleMessage();
	}
	
	private void handleEffect(MovesEffects effect, Pokemon pAttacker, Pokemon pTarget, Move usedMove) {
		switch (effect) {

		case DANNO:
			attack(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
			break;

		case AUMENTA_ATTACCOX2:
			boostAttackX2(pAttacker);
			break;

		case AUMENTA_DIFESAX2:
			boostDefenseX2(pAttacker);
			break;

		case AUMENTA_VELOCITAX2:
			boostSpeedX2(pAttacker);
			break;
		
		case AUMENTA_ATTACCO_VELOCITA:
			boostAttack(pAttacker);
			boostSpeed(pAttacker);
			break;

		case DANNO_AUMENTA_VELOCITA:
			attack(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
			boostSpeed(pAttacker);
			break;

		case DANNO_RIDUZIONE_DIFESA_E_DIFESA_SPECIALE_SE_STESSO:
			attack(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
			reduceSpDefense(pAttacker);
			break;

		case ANNULLA_DANNI:
			setProtect(pAttacker, usedMove);
			break;
		
		
		case RECUPERO_HP:
			recover(pAttacker);
			break;

		case DANNO_RECUPERO_HP:
			attackAndRecover(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
			break;
			
		case AUMENTA_ELUSIONE:
			 boostAvoidance(pAttacker);
			break;
			
		case DANNO_RIDUZIONE_PRECISIONE:
			attack(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
			reducePrecision(pTarget);
			break;
		default:
			break;
		}
	}

	private ArrayList<Action> setAttackOrder(ArrayList<Action> actions) {

		SpeedComparator comparator = new SpeedComparator();
		TreeSet<Action> treeSet = new TreeSet<Action>(comparator);
		treeSet.addAll(actions);
		ArrayList<Action> orderedActions = new ArrayList<>(treeSet);
		return orderedActions;
	}

	private void attack(Pokemon pAttacker, Pokemon pTarget, Move usedMove, boolean isProtect) {
		if (isMoveSuccessful(pAttacker.getStats().getPrecision(), pTarget.getStats().getAvoidance(),
				usedMove.getPrecision())) {
			double debRes = isEffective(usedMove, pTarget);
			int damage = DamageCalculator.calculateDamage(pAttacker, pTarget, usedMove, debRes, isProtect);
			pTarget.getStats().setActualHp(Math.max(pTarget.getStats().getActualHp() - damage, 0));
		}else
			setBattleMessage(pAttacker.getName() + " ha mancato il bersaglio!\n");
	}
	
	private void attackAndRecover(Pokemon pAttacker, Pokemon pTarget, Move usedMove, boolean isProtect) {
		attack(pAttacker, pTarget, usedMove, pTarget.getIsProtect());
		if(!pTarget.getIsProtect()) {
			int hpToRecover = (int) (pTarget.getStats().getHp() - pTarget.getStats().getActualHp()) / 2;
			pAttacker.getStats().setActualHp(pAttacker.getStats().getActualHp() + hpToRecover);
			if(pAttacker.getStats().getActualHp() > pAttacker.getStats().getHp())
				pAttacker.getStats().setActualHp(pAttacker.getStats().getHp());
			setBattleMessage(pAttacker.getName() + " ha recuperato degli hp!\n");
		}
	}
	
	private void reduceDefense(Pokemon pokemon) throws Exception {
		int decreaseValue = (int) pokemon.getStats().getDefense() / 2;
		// Diminuisce difesa del pokemon
		pokemon.getStats().setActualDefense(pokemon.getStats().getActualDefense() - decreaseValue);
		if(pokemon.getStats().getActualDefense() <= 0) {
			setBattleMessage("Message: la difesa di "+pokemon.getName()+" non può diminuire di più.\n");
			pokemon.getStats().setActualDefense(1);
		}
		setBattleMessage("La difesa di " + pokemon.getName() + " diminuisce!\n");
	}

	private void reduceSpDefense(Pokemon pokemon) {
		int decreaseValue = (int) pokemon.getStats().getSpDefense() / 2;
		// Diminuisce difesa speciale del pokemon
		pokemon.getStats().setActualSpDefense(pokemon.getStats().getActualSpDefense() - decreaseValue);
		if(pokemon.getStats().getActualSpDefense() <= 0) {
			setBattleMessage("Message: la difesa speciale di "+pokemon.getName()+" non può diminuire di più.\n");
			pokemon.getStats().setActualSpDefense(1);
		}
		setBattleMessage("La difesa speciale di " + pokemon.getName() + " diminuisce!\n");
	}
	
	private void reducePrecision(Pokemon pokemon) {
		int decreaseValue = 15;
		// Diminuisce precisione del pokemon
		pokemon.getStats().setPrecision(pokemon.getStats().getPrecision() - decreaseValue);
		if(pokemon.getStats().getPrecision() <= 0) {
			setBattleMessage("Message: la precisione di "+pokemon.getName()+" non può diminuire di più.\n");
			pokemon.getStats().setPrecision(1);
		}
		setBattleMessage("La precisione di " + pokemon.getName() + " diminuisce!\n");
	}
	
	private void boostAvoidance(Pokemon pokemon) {
		int increaseValue = 15;
		// Incrementa l'elusione del pokemon
		pokemon.getStats().setAvoidance(pokemon.getStats().getAvoidance() + increaseValue);
		setBattleMessage("L'elusione di " + pokemon.getName() + " aumenta!\n");
	}

	private void boostAttack(Pokemon pokemon) {
		int increaseValue = (int) pokemon.getStats().getAttack() / 2;
		// Incrementa attacco del pokemon
		if(pokemon.getStats().getActualAttack() >= pokemon.getStats().getAttack() * 3) {
			setBattleMessage("L'attacco di "+pokemon.getName()+" non può aumentare di più.");
			pokemon.getStats().setActualAttack(pokemon.getStats().getAttack() * 3);
		}else {			
			pokemon.getStats().setActualAttack(pokemon.getStats().getActualAttack() + increaseValue);
			setBattleMessage("L'attacco di " + pokemon.getName() + " aumenta!\n");
		}
	}

	private void boostSpeed(Pokemon pokemon) {
		int increaseValue = (int) pokemon.getStats().getSpeed() / 2;
		
		if(pokemon.getStats().getActualSpeed() >= pokemon.getStats().getSpeed() * 3) {
			setBattleMessage("La velocità di "+pokemon.getName()+" non può aumentare di più.\n");
			pokemon.getStats().setActualSpeed(pokemon.getStats().getSpeed() * 3);
		}else {
			// Incrementa velocita del pokemon
			pokemon.getStats().setActualSpeed(pokemon.getStats().getActualSpeed() + increaseValue);
			setBattleMessage("La velocità di " + pokemon.getName() + " aumenta!\n");			
		}
	}

	private void boostAttackX2(Pokemon pokemon) {
		int increaseValue = (int) pokemon.getStats().getAttack();
		// Raddoppia attacco del pokemon
		if(pokemon.getStats().getActualAttack() >= pokemon.getStats().getAttack() * 3) {
			setBattleMessage("L'attacco di "+pokemon.getName()+" non può aumentare di più.");
			pokemon.getStats().setActualAttack(pokemon.getStats().getAttack() * 3);
		}else {			
			pokemon.getStats().setActualAttack(pokemon.getStats().getActualAttack() + increaseValue);
			setBattleMessage("L'attacco di " + pokemon.getName() + " aumenta di molto!\n");
		}
	}

	private void boostDefenseX2(Pokemon pokemon) {
		int increaseValue = (int) pokemon.getStats().getDefense();
		// Raddoppia difesa del pokemon
		if(pokemon.getStats().getActualDefense() >= pokemon.getStats().getDefense() * 3) {
			setBattleMessage("La difesa di "+pokemon.getName()+" non può aumentare di più.");
			pokemon.getStats().setActualDefense(pokemon.getStats().getDefense() * 3);
		}else {			
			pokemon.getStats().setActualDefense(pokemon.getStats().getActualDefense() + increaseValue);
			setBattleMessage("La difesa di " + pokemon.getName() + " aumenta di molto!\n");
		}
	}

	private void boostSpeedX2(Pokemon pokemon) {
		int increaseValue = (int) pokemon.getStats().getSpeed();
		// Raddoppia velocita del pokemon
		if(pokemon.getStats().getActualSpeed() >= pokemon.getStats().getSpeed() * 3) {
			setBattleMessage("La velocità di "+pokemon.getName()+" non può aumentare di più.\n");
			pokemon.getStats().setActualSpeed(pokemon.getStats().getSpeed() * 3);
		}else {
			// Incrementa velocita del pokemon
			pokemon.getStats().setActualSpeed(pokemon.getStats().getActualSpeed() + increaseValue);
			setBattleMessage("La velocità di " + pokemon.getName() + " aumenta di molto!\n");			
		}
	}

	private void setProtect(Pokemon pokemon, Move usedMove) {
		pokemon.setIsProtect(true);
		setBattleMessage(pokemon.getName() + " si protegge!\n");
	}

	private void switchPokemon(Pokemon pokemon, Pokemon pokemonToSwitch) {
		Team team1 = this.match.getTeam1();
		Team team2 = this.match.getTeam2();
		if (pokemon.equals(this.match.getPokemonInBattle())) {
			this.match.setPokemonInBattle(pokemonToSwitch);
			Collections.swap(team1.getList(), team1.getList().indexOf(pokemon),
					team1.getList().indexOf(pokemonToSwitch));
		} else {
			this.match.setPokemonInBattle2(pokemonToSwitch);
			Collections.swap(team2.getList(), team2.getList().indexOf(pokemon),
					team2.getList().indexOf(pokemonToSwitch));
		}
		resetStats(pokemon);
		setBattleMessage(pokemon.getName() + " rientra!\n" + "Entra in campo " + pokemonToSwitch.getName() + "!\n");
	}
	
	private void recover(Pokemon pokemon) {
		int hpToRecover = pokemon.getStats().getHp() / 100 * 70;
		if(pokemon.getStats().getActualHp() == pokemon.getStats().getHp())
			setBattleMessage(pokemon.getName() + " ha già tutti gli hp!\n");
		else {
			pokemon.getStats().setActualHp(pokemon.getStats().getActualHp() + hpToRecover);
			if(pokemon.getStats().getActualHp() > pokemon.getStats().getHp())
				pokemon.getStats().setActualHp(pokemon.getStats().getHp());
			setBattleMessage(pokemon.getName() + " ha recuperato degli hp!\n");
		}
	}

	public void setBattleMessage(String s) {

		this.message += s;
	}

	public void printBattleMessage() {

		System.out.println(this.message);
	}

	// Controllo se la mossa é andata a segno
	private boolean isMoveSuccessful(int attackerPrecision, int targetEvasion, int moveAccuracy) {
	    Random random = new Random();
	    int randomNumber = random.nextInt(100) + 1; // Genera un numero casuale tra 1 e 100

	    // Calcola l'accuratezza effettiva considerando precisione ed elusione
	    int effectiveAccuracy = (int) (moveAccuracy * (attackerPrecision / 100.0) / (targetEvasion / 100.0));

	    // Verifica se la mossa colpisce
	    return randomNumber <= effectiveAccuracy;
	}

	// Controllo le debolezze dei tipi dei pokemon rispetto alle mosse
	private double isEffective(Move usedMove, Pokemon pTarget) {

	    double debRes = 1.0;

	    for (Type pTargetType : pTarget.getType()) {
	        if (pTargetType.isImmuneAgainst(usedMove.getType())) {
	            debRes = 0;
	            break; // Se è immune, il danno è 0, non serve continuare.
	        }
	        else if (pTargetType.isWeakAgainst(usedMove.getType())) {
	            debRes *= 2; // Moltiplica per 2 se il tipo è debole.
	        }
	        else if (pTargetType.isResistantAgainst(usedMove.getType())) {
	            debRes *= 0.5; // Moltiplica per 0.5 se il tipo è resistente.
	        }
	    }

	    // Mostra il messaggio in base al moltiplicatore di danno finale.
	    if (debRes > 1.0) {
	        setBattleMessage("È superefficace!\n");
	    }
	    else if (debRes < 1.0 && debRes > 0) {
	        setBattleMessage("Non è molto efficace!\n");
	    }
	    else if (debRes == 0) {
	        setBattleMessage("Non ha effetto!\n");
	    }

	    return debRes;
	}
	
	public void knockout(Pokemon pokemon) {
	    pokemon.setIsFainted(true);
	    setBattleMessage(pokemon.getName() + " é esausto!\n");
	    int koCounter = 0;
	    Team team = (pokemon.equals(match.getPokemonInBattle())) ? match.getTeam1() : match.getTeam2();
	    for (Pokemon pkmn : team.getList()) {
	        if (pkmn.isFainted()) {
	            koCounter++;
	        }
	    }
	    if (koCounter == 3) {
	        Team winningTeam = (team.equals(match.getTeam1())) ? match.getTeam2() : match.getTeam1();
	        this.match.setWinner(winningTeam);
	    } else {
	        if (pokemon.equals(match.getPokemonInBattle())) {
	            match.getPokemonInBattle().setIsFainted(true);
	        } else {
	        	match.getPokemonInBattle2().setIsFainted(true);
	        }
	    }
	}
	
	private void resetStats(Pokemon pokemon) {
		pokemon.getStats().setActualAttack(pokemon.getStats().getAttack());
		pokemon.getStats().setActualDefense(pokemon.getStats().getDefense());
		pokemon.getStats().setActualSpAttack(pokemon.getStats().getSpAttack());
		pokemon.getStats().setActualSpDefense(pokemon.getStats().getSpDefense());
		pokemon.getStats().setActualSpeed(pokemon.getStats().getSpeed());
		pokemon.getStats().setAvoidance(100);
		pokemon.getStats().setPrecision(100);
	}
}
