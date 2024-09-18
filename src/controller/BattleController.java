package controller;

import interfaces.BattleObserver;
import interfaces.Model;
import interfaces.Pokemon;
import interfaces.Team;
import interfaces.View;
import interfaces.battle.Match;
import interfaces.battle.Move;
import view.MoveButton;
import view.PokemonButton;

public class BattleController implements BattleObserver {
	private Controller father;
	private View view;
	private Model model;
	
	public BattleController(View view, Model model, Controller father) {
		this.view = view;
		this.model = model;
		this.father = father;
	}
    
	@Override
	public void startBattle(Match match) {
        
		this.model.setMatch(match);
		this.view.battleMenu();
		this.view.getBattlePanel().setObserver(this);
		updateView();
	}
	
	@Override
	public void setViewForTeam1(Pokemon pokemon1, Pokemon pokemon2) {
	    Move move1 = pokemon1.getMoveSet().getMove1();
	    Move move2 = pokemon1.getMoveSet().getMove2();
	    Move move3 = pokemon1.getMoveSet().getMove3();
	    Move move4 = pokemon1.getMoveSet().getMove4();
	    this.view.getBattlePanel().getBtnMove1().setUsedMove(move1);
	    this.view.getBattlePanel().getBtnMove2().setUsedMove(move2);
	    this.view.getBattlePanel().getBtnMove3().setUsedMove(move3);
	    this.view.getBattlePanel().getBtnMove4().setUsedMove(move4);
	    if(move1.getActualPp() == 0)
	    	this.view.getBattlePanel().getBtnMove1().setEnabled(false);
    	if(move2.getActualPp() == 0)
    		this.view.getBattlePanel().getBtnMove2().setEnabled(false);
		if(move3.getActualPp() == 0)
			this.view.getBattlePanel().getBtnMove3().setEnabled(false);
		if(move4.getActualPp() == 0)
			this.view.getBattlePanel().getBtnMove4().setEnabled(false);
	    setAttackersForTeam1(pokemon1);
	    setTargetsForTeam1(pokemon2);
	    setIconForTeam1(pokemon1);
	    setHealthBarForTeam1(pokemon1);
	    this.view.getBattlePanel().setAskLabel("Cosa deve fare " + pokemon1.getName() + " ?");
	}
	
	@Override
	public void setViewForTeam2(Pokemon pokemon1, Pokemon pokemon2) {
	    Move move5 = pokemon2.getMoveSet().getMove1();
	    Move move6 = pokemon2.getMoveSet().getMove2();
	    Move move7 = pokemon2.getMoveSet().getMove3();
	    Move move8 = pokemon2.getMoveSet().getMove4();
	    this.view.getBattlePanel().getBtnMove5().setUsedMove(move5);
	    this.view.getBattlePanel().getBtnMove6().setUsedMove(move6);
	    this.view.getBattlePanel().getBtnMove7().setUsedMove(move7);
	    this.view.getBattlePanel().getBtnMove8().setUsedMove(move8);
	    if(move5.getActualPp() == 0)
	    	this.view.getBattlePanel().getBtnMove5().setEnabled(false);
    	if(move6.getActualPp() == 0)
    		this.view.getBattlePanel().getBtnMove6().setEnabled(false);
		if(move7.getActualPp() == 0)
			this.view.getBattlePanel().getBtnMove7().setEnabled(false);
		if(move8.getActualPp() == 0)
			this.view.getBattlePanel().getBtnMove8().setEnabled(false);
	    setAttackersForTeam2(pokemon2);
	    setTargetsForTeam2(pokemon1);
	    setIconForTeam2(pokemon2);
	    setHealthBarForTeam2(pokemon2);
	    this.view.getBattlePanel().setAskLabel2("Cosa deve fare " + pokemon2.getName() + " ?");
	}
	
	@Override
	public void setAttackersForTeam1(Pokemon pokemon) {
		
		this.view.getBattlePanel().getBtnMove1().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove2().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove3().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove4().setAttacker(pokemon);
	}
	
	@Override
	public void setTargetsForTeam1(Pokemon pokemon) {
		
		this.view.getBattlePanel().getBtnMove1().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove2().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove3().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove4().setTarget(pokemon);
	}
	
	@Override
	public void setAttackersForTeam2(Pokemon pokemon) {
		
		this.view.getBattlePanel().getBtnMove5().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove6().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove7().setAttacker(pokemon);
	    this.view.getBattlePanel().getBtnMove8().setAttacker(pokemon);
	}
	
	@Override
	public void setTargetsForTeam2(Pokemon pokemon) {
		
		this.view.getBattlePanel().getBtnMove5().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove6().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove7().setTarget(pokemon);
	    this.view.getBattlePanel().getBtnMove8().setTarget(pokemon);
	}	
	
	@Override
	public void updateView() {
		Pokemon pokemon1 = this.model.getMatch().getPokemonInBattle();
	    Pokemon pokemon2 = this.model.getMatch().getPokemonInBattle2();
	    setViewForTeam1(pokemon1, pokemon2);
	    setViewForTeam2(pokemon1, pokemon2);
	}
	
	@Override
	public void setIconForTeam1(Pokemon pokemon) {
		
		try {
	        	String imageString = pokemon.getIconString();
	        	this.view.getBattlePanel().setLabel1Info(pokemon.getName() + " " + pokemon.getStats().getActualHp() + "/" + pokemon.getStats().getHp());
	        	this.view.getBattlePanel().setIcon1(imageString);
	    } catch (Exception e) {
	        	System.err.println("Errore nel caricamento dell'immagine per " + pokemon.getName() + ": " + e.getMessage());
	    }
	}
	
	@Override
	public void setIconForTeam2(Pokemon pokemon) {
		
		try {
	        	String imageString = pokemon.getIconString();
	        	this.view.getBattlePanel().setLabel2Info(pokemon.getName() + " " + pokemon.getStats().getActualHp() + "/" + pokemon.getStats().getHp());
	        	this.view.getBattlePanel().setIcon2(imageString);
	    } catch (Exception e) {
	        	System.err.println("Errore nel caricamento dell'immagine per " + pokemon.getName() + ": " + e.getMessage());
	    }
	}
	
	@Override
	public void setHealthBarForTeam1(Pokemon pokemon) {
		
		this.view.getBattlePanel().setProgressBar1(pokemon.getStats().getHp());
	    this.view.getBattlePanel().updateProgressBar1(pokemon.getStats().getActualHp()); 
	}
	
	@Override
	public void setHealthBarForTeam2(Pokemon pokemon) {
		
	    this.view.getBattlePanel().setProgressBar2(pokemon.getStats().getHp());
	    this.view.getBattlePanel().updateProgressBar2(pokemon.getStats().getActualHp());
	}
	
	@Override
	public void setSwitchViewForTeam1() {
		this.view.getBattlePanel().getSwitchFrame().setObserver(this);
		Pokemon attacker = this.model.getMatch().getPokemonInBattle();
		Pokemon pokemon1 = this.model.getMatch().getTeam1().getList().get(1);
		Pokemon pokemon2 = this.model.getMatch().getTeam1().getList().get(2);
		this.view.getBattlePanel().getSwitchFrame().getBtnSwitch1().setAttacker(attacker);
		this.view.getBattlePanel().getSwitchFrame().getBtnSwitch2().setAttacker(attacker);
		this.view.getBattlePanel().getSwitchFrame().getBtnSwitch1().setPokemonToSwitch(pokemon1);
		this.view.getBattlePanel().getSwitchFrame().getBtnSwitch2().setPokemonToSwitch(pokemon2);
		this.view.getBattlePanel().setEnableforBtn(false);
		this.view.getBattlePanel().getBtnBack().setEnabled(false);
	}
	
	@Override
	public void setSwitchViewForTeam2() {
		this.view.getBattlePanel().getSwitchFrame2().setObserver(this);
		Pokemon attacker = this.model.getMatch().getPokemonInBattle2();
		Pokemon pokemon1 = this.model.getMatch().getTeam2().getList().get(1);
		Pokemon pokemon2 = this.model.getMatch().getTeam2().getList().get(2);
		this.view.getBattlePanel().getSwitchFrame2().getBtnSwitch1().setAttacker(attacker);
		this.view.getBattlePanel().getSwitchFrame2().getBtnSwitch2().setAttacker(attacker);
		this.view.getBattlePanel().getSwitchFrame2().getBtnSwitch1().setPokemonToSwitch(pokemon1);
		this.view.getBattlePanel().getSwitchFrame2().getBtnSwitch2().setPokemonToSwitch(pokemon2);
		this.view.getBattlePanel().setEnableforBtn2(false);
		this.view.getBattlePanel().getBtnBack2().setEnabled(false);
	}
	
	@Override
	public void storeMove(MoveButton button, boolean teamAction) {    
	    Pokemon pAttacker = button.getAttacker();
	    Move usedMove = button.getUsedMove();
	    this.model.getMatch().createAttackAction(pAttacker, usedMove);
    	this.model.getMatch().addAction(this.model.getMatch().getAction(pAttacker));
	    if(teamAction) {
	    	this.view.getBattlePanel().setEnableforBtn(false);
	    }
	    else {
		    this.view.getBattlePanel().setEnableforBtn2(false); 
	    }
	    battle();
	}

	@Override
	public void deleteSelectedActionForTeam1() {
		this.model.getMatch().getActionList().removeAll(this.model.getMatch().getActionList());
		resetButtons(true);
		
	}
	
	@Override
	public void deleteSelectedActionForTeam2() {
		this.model.getMatch().getActionList().removeAll(this.model.getMatch().getActionList());
		resetButtons(false);
	}

	@Override
	public void storeSwitchAction(PokemonButton button, boolean teamAction) {
		Pokemon pAttacker = button.getAttacker();
		Pokemon pokemonToSwitch = button.getPokemonToSwitch();
		if(!pokemonToSwitch.isFainted()) {
			this.model.getMatch().createSwitchAction(pAttacker, pokemonToSwitch);
			this.model.getMatch().addAction(this.model.getMatch().getAction(pAttacker));
			if(teamAction) {
				this.view.getBattlePanel().getSwitchFrame().setVisible(false);
				this.view.getBattlePanel().setEnableforBtn(false);
			}
			else {
				this.view.getBattlePanel().getSwitchFrame2().setVisible(false);
				this.view.getBattlePanel().setEnableforBtn2(false);
			}
		}else {
			this.view.getBattlePanel().isFainted();
			return;
		}
		if(pAttacker.isFainted()) {
			this.model.getMatch().battle();
			updateView();
			this.view.getBattlePanel().setEnableforBtn(true);
			this.view.getBattlePanel().setEnableforBtn2(true);
		}
		this.view.getBattlePanel().getBtnBack().setEnabled(true);
		this.view.getBattlePanel().getBtnBack2().setEnabled(true);
		battle();
	}
	
	@Override
	public void battle() {
		if(this.model.getMatch().getActionList().size() == 2) {
			this.model.getMatch().battle();
			updateView();
			if(!this.model.getMatch().getPokemonInBattle().isFainted() && !this.model.getMatch().getPokemonInBattle2().isFainted()) {
				this.view.getBattlePanel().setEnableforBtn(true);
				this.view.getBattlePanel().setEnableforBtn2(true);
				updateView();
			}else {
				if(this.model.getMatch().isOver()) {
					endBattle(this.model.getMatch().getWinner());
				}else {
					if(this.model.getMatch().getPokemonInBattle().isFainted()) {
						this.switchFaintedPokemon(true);
					}else {
						this.switchFaintedPokemon(false);
					}
				}
			}
		}
	}
	
	@Override
	public void endTurn() {
		updateView();
	}

	@Override
	public void resetButtons(boolean teamAction) {
		if(teamAction) {
			 MoveButton move1 = this.view.getBattlePanel().getBtnMove1();
			 MoveButton move2 = this.view.getBattlePanel().getBtnMove2();
			 MoveButton move3 = this.view.getBattlePanel().getBtnMove3();
			 MoveButton move4 = this.view.getBattlePanel().getBtnMove4();
			 this.view.getBattlePanel().setEnableforBtn(true);

		    if(move1.getUsedMove().getActualPp() == 0)
		    	move1.setEnabled(false);
	    	if(move2.getUsedMove().getActualPp() == 0)
	    		move2.setEnabled(false);
			if(move3.getUsedMove().getActualPp() == 0)
				move3.setEnabled(false);
			if(move4.getUsedMove().getActualPp() == 0)
				move4.setEnabled(false);
			this.view.getBattlePanel().getBtnBack().setEnabled(true);
		}
		 else {
			 MoveButton move5 = this.view.getBattlePanel().getBtnMove5();
			 MoveButton move6 = this.view.getBattlePanel().getBtnMove6();
			 MoveButton move7 = this.view.getBattlePanel().getBtnMove7();
			 MoveButton move8 = this.view.getBattlePanel().getBtnMove8();
			 this.view.getBattlePanel().setEnableforBtn2(true);

		    if(move5.getUsedMove().getActualPp() == 0)
		    	move5.setEnabled(false);
	    	if(move6.getUsedMove().getActualPp() == 0)
	    		move6.setEnabled(false);
			if(move7.getUsedMove().getActualPp() == 0)
				move7.setEnabled(false);
			if(move8.getUsedMove().getActualPp() == 0)
				move8.setEnabled(false);
			this.view.getBattlePanel().getBtnBack2().setEnabled(true);
		 }
	}

	@Override
	public void endBattle(Team winningTeam) {
		int winner = -1;
		updateView();
		if(winningTeam.equals(this.model.getMatch().getTeam1())) {
			this.view.getBattlePanel().winScreen("Team 1!");
			winner = 0;
		}else {
			this.view.getBattlePanel().winScreen("Team 2!");
			winner = 1;
		}
		
		// Se il salvataggio è attivo aggiorno gli account
		if(this.model.isSaveEnabled()) {
			int other = 1 ^ winner;
			this.model.getFileManager().getUsedDataArray(winner).setMatches(this.model.getFileManager().getUsedDataArray(winner).getMatches() + 1);
			this.model.getFileManager().getUsedDataArray(other).setMatches(this.model.getFileManager().getUsedDataArray(other).getMatches() + 1);
			this.model.getFileManager().getUsedDataArray(winner).setWins(this.model.getFileManager().getUsedDataArray(winner).getWins() + 1);
			this.model.getFileManager().getUsedDataArray(other).setLosses(this.model.getFileManager().getUsedDataArray(other).getLosses() + 1);
			this.model.getFileManager().updateFile(this.model.getFileManager().getUsedDataArray(0));
			this.model.getFileManager().updateFile(this.model.getFileManager().getUsedDataArray(1));
		}
		
		this.model.exitLobby();
		this.father.initTeam();
	}

	@Override
	public void switchFaintedPokemon(boolean teamAction) {
		this.view.getBattlePanel().setEnableforBtn(false);
		this.view.getBattlePanel().setEnableforBtn2(false);
		this.view.getBattlePanel().getBtnBack().setEnabled(false);
		this.view.getBattlePanel().getBtnBack2().setEnabled(false);
		if(teamAction) {
			this.view.getBattlePanel().setSwitchFrame();
			this.view.getBattlePanel().getSwitchFrame().getBtnBack().setEnabled(false);
		}
		else {
			this.view.getBattlePanel().setSwitchFrame2();
			this.view.getBattlePanel().getSwitchFrame2().getBtnBack().setEnabled(false);
		}
		
	}
}
