package model.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import interfaces.Pokemon;
import interfaces.Stats;
import interfaces.battle.Move;
import interfaces.battle.MoveSet;
import model.MoveImpl;
import model.MoveSetImpl;
import model.MoveType;
import model.MovesEffects;
import model.PokemonImpl;
import model.StatsImpl;
import model.Type;

public class PokemonLoader extends AbstractFileManager<String, Pokemon> {
	
	
	@Override
	public boolean readAllFiles() {
		
		// load moves
		Map<String, Move> moves = new HashMap<String, Move>();
		boolean result = true;
		File movesFile = new File(this.getOpenDirectory().getPath()+"\\"+"moves.txt");
		try {
			Scanner scan = new Scanner(movesFile);
			while(scan.hasNext()) {
				String name = scan.nextLine();
				Type type = Type.valueOf(scan.nextLine());
				int pp = Integer.parseInt(scan.nextLine());
				int power = Integer.parseInt(scan.nextLine());
				int precision = Integer.parseInt(scan.nextLine());
				String desc = scan.nextLine();
				MoveType moveType = MoveType.valueOf(scan.nextLine());
				MovesEffects moveEffect = MovesEffects.valueOf(scan.nextLine());
				int priority = Integer.parseInt(scan.nextLine());
				Move move = new MoveImpl(name,type,pp,power,precision,desc,moveType,moveEffect,priority);
				moves.put(move.getName(),move);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = false;
		}
		
		// load Pokemons
		File pokedexFile = new File(this.getOpenDirectory().getPath()+"\\"+"pokedex.txt");
		
		try {
			Scanner scan = new Scanner(pokedexFile);
			while(scan.hasNext()) {
				String name = scan.nextLine();
				Type type1 = Type.valueOf(scan.nextLine());
				Type type2 = Type.valueOf(scan.nextLine());
				int hp = Integer.parseInt(scan.nextLine());
				int attack = Integer.parseInt(scan.nextLine());
				int defense = Integer.parseInt(scan.nextLine());
				int spAttack = Integer.parseInt(scan.nextLine());
				int spDefense = Integer.parseInt(scan.nextLine());
				int speed = Integer.parseInt(scan.nextLine());
				String moveName1 = scan.nextLine();
				String moveName2 = scan.nextLine();
				String moveName3 = scan.nextLine();
				String moveName4 = scan.nextLine();
				Move move1 = moves.get(moveName1);
				Move move2 = moves.get(moveName2);
				Move move3 = moves.get(moveName3);
				Move move4 = moves.get(moveName4);
				List<Type> types = Arrays.asList(type1,type2);
				Stats stats = new StatsImpl(hp,attack,defense,spAttack,spDefense,speed);
				MoveSet moveSet = new MoveSetImpl(move1,move2,move3,move4);
				String spritesPath = ".\\resources\\pokemonSprites";
				String iconString = spritesPath+"\\"+name.toLowerCase()+".png";
				Pokemon pokemon = new PokemonImpl(name,types,stats,moveSet,iconString);
				this.getDataMap().put(pokemon.getName(), pokemon);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean readFile(String filename) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unused method for this child class");
	}

	@Override
	public boolean writeNewFile(Pokemon data) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unused method for this child class");
	}

	@Override
	public boolean updateFile(Pokemon data) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unused method for this child class");
	}
}
