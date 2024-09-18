package model.menu;

import java.util.List;
import interfaces.Pokedex;
import interfaces.Pokemon;
import interfaces.Team;
import interfaces.menu.Lobby;
import model.PokedexImpl;
import model.TeamImpl;

public class LobbyImpl implements Lobby {
	
	private Team teams[];
	private Pokemon selectedPokemon;
	private Pokedex pokedex;
	private int teamSize;
	
	public LobbyImpl() {
		this.pokedex = PokedexImpl.getInstance();
		this.teamSize = 3;
		this.teams = new TeamImpl[2];
		this.teams[0] = new TeamImpl(teamSize);
		this.teams[1] = new TeamImpl(teamSize);
	}
	
	public LobbyImpl(int teamSize) {
		this.pokedex = PokedexImpl.getInstance();
		this.teamSize = teamSize;
		this.teams = new TeamImpl[2];
		this.teams[0] = new TeamImpl(teamSize);
		this.teams[1] = new TeamImpl(teamSize);
	}
		
	public int addPokemon(int select) {
		int added;
		if(this.teams[select].getList().size() < teamSize)
			if(this.selectedPokemon != null && !isPresent(select)) {
				this.teams[select].add(selectedPokemon.duplicate());
				System.out.println("Aggiunto "+this.selectedPokemon.getName()+" al team "+select);
				added = this.teams[select].getList().size() - 1;
			}else {
				if(isPresent(select)) {
					added = -3;
					System.out.println("Pokemon già presente in squadra!");
				}else {
					added = -1;	
					System.out.println("Pokemon non selezionato!");
				}
			}
		else {
			added = -2;
			System.out.println("Team pieno");
		}
		return added;
	}

	public int removePokemon(int select) {
		int removed;
		try {
			if(this.teams[select].getList().size() >= 1) {
				int index = this.teams[select].getList().size() - 1;
				System.out.println("Rimosso "+this.teams[select].getList().get(index).getName()+" dalla squadra "+select);
				this.teams[select].remove(index);
				removed = index;
			}else {
				System.out.println("Squadra vuota!");
				removed = -1;
			}
		}catch(Exception e) {
			System.out.println("Eccezione");
			removed = -1;
		}
		return removed;
	}

	
	public Pokemon getSelected() {
		return this.selectedPokemon;
	}

	
	public void deselect() {
		if(this.selectedPokemon != null) {
			this.selectedPokemon = null;
			System.out.println("Deselezionato!");
		}
	}

	
	public void selectPokemon(String name) {
		this.selectedPokemon = this.pokedex.getPokemon(name); 
		if(this.selectedPokemon == null)
			System.out.println("Errore: Pokemon non trovato");
		else
			System.out.println("Selezionato "+this.selectedPokemon.getName());
	}
	
	public Pokedex getPokedex() {
		return this.pokedex;
	}

	
	public boolean selectFromTeam(int select, int index) {
		boolean selected = false;
		try {
			if(this.teams[select].getList().size() > index) {
				this.selectedPokemon = this.teams[select].getList().get(index);
				System.out.println("Selezionato "+this.selectedPokemon.getName());
				selected = true;
			}else {
				selected = false;
			}
		}catch(Exception e) {
			System.out.println("Indice fuori range!");
		}
		return selected;
	}

	
	public boolean checkReady() {
		boolean ready = false;
		if(this.teams[0].getList().size() == teamSize && this.teams[1].getList().size() == teamSize)
			ready = true;
		return ready;		
	}

	
	public Team getTeam(int select) {
		return this.teams[select];
	}
	
	private boolean isPresent(int select) {
		boolean result = false;
		
		List<Pokemon> list = this.teams[select].getList();
		for(Pokemon pokemon : list) {
			if(pokemon.getName().equals(selectedPokemon.getName())) {
				result = true;
				return result;
			}
		}
		return result;
	}
}
