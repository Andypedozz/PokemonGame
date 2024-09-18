package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Type {
	
	NORMALE,
	FUOCO,
	LOTTA,
	ACQUA,
	VOLANTE,
	ERBA,
	VELENO,
	ELETTRO,
	TERRA,
	PSICO,
	ROCCIA,
	GHIACCIO,
	COLEOTTERO,
	DRAGO,
	SPETTRO,
	BUIO,
	ACCIAIO,
	FOLLETTO;
	
	private ArrayList<Type> weaknesses = new ArrayList<>();
	private ArrayList<Type> resistence = new ArrayList<>();
	private ArrayList<Type> immune = new ArrayList<>(); 
	
	// blocco di inizializzazione statica delle debolezze dei tipi
	static {
		
		//Imposto le debolezze dei tipi dei pokemon
		NORMALE.weaknesses.add(LOTTA);
		FUOCO.weaknesses.addAll(Arrays.asList(TERRA, ROCCIA, ACQUA));
		LOTTA.weaknesses.addAll(Arrays.asList(VOLANTE,PSICO, FOLLETTO));
		ACQUA.weaknesses.addAll(Arrays.asList(ERBA, ELETTRO));
		VOLANTE.weaknesses.addAll(Arrays.asList(ELETTRO, ROCCIA, GHIACCIO));
		ERBA.weaknesses.addAll(Arrays.asList(FUOCO, COLEOTTERO, GHIACCIO, VELENO, VOLANTE));
		VELENO.weaknesses.addAll(Arrays.asList(TERRA, PSICO));
		ELETTRO.weaknesses.add(TERRA);
		TERRA.weaknesses.addAll(Arrays.asList(ACQUA, ERBA, GHIACCIO));
		PSICO.weaknesses.addAll(Arrays.asList(BUIO, SPETTRO, COLEOTTERO));
		ROCCIA.weaknesses.addAll(Arrays.asList(ACQUA, GHIACCIO, LOTTA, TERRA, ACCIAIO));
		GHIACCIO.weaknesses.addAll(Arrays.asList(FUOCO, LOTTA, ROCCIA, ACCIAIO));
		COLEOTTERO.weaknesses.addAll(Arrays.asList(FUOCO, VOLANTE, ROCCIA));
		DRAGO.weaknesses.addAll(Arrays.asList(DRAGO, GHIACCIO, FOLLETTO));
		SPETTRO.weaknesses.addAll(Arrays.asList(SPETTRO, BUIO));
		BUIO.weaknesses.addAll(Arrays.asList(LOTTA, COLEOTTERO, FOLLETTO));
		ACCIAIO.weaknesses.addAll(Arrays.asList(FUOCO, LOTTA, TERRA));
		FOLLETTO.weaknesses.addAll(Arrays.asList(ACCIAIO, VELENO));
		
		//imposto le resistenze dei tipi
		FUOCO.resistence.addAll(Arrays.asList(COLEOTTERO, ACCIAIO, FUOCO, ERBA, GHIACCIO, FOLLETTO));
		LOTTA.resistence.addAll(Arrays.asList(ROCCIA, COLEOTTERO, BUIO));
		ACQUA.resistence.addAll(Arrays.asList(ACCIAIO, FUOCO, ACQUA, GHIACCIO));
		VOLANTE.resistence.addAll(Arrays.asList(LOTTA, COLEOTTERO, ERBA));
		ERBA.resistence.addAll(Arrays.asList(TERRA, ACQUA, ERBA, ELETTRO));
		VELENO.resistence.addAll(Arrays.asList(VELENO, COLEOTTERO, ERBA, FOLLETTO));
		ELETTRO.resistence.addAll(Arrays.asList(VOLANTE, ELETTRO, ACCIAIO));
		TERRA.resistence.addAll(Arrays.asList(VELENO, ROCCIA));
		PSICO.resistence.addAll(Arrays.asList(LOTTA, PSICO));
		ROCCIA.resistence.addAll(Arrays.asList(NORMALE, VOLANTE, VELENO, FUOCO));
		GHIACCIO.resistence.addAll(Arrays.asList(GHIACCIO));
		COLEOTTERO.resistence.addAll(Arrays.asList(LOTTA, TERRA, ERBA));
		DRAGO.resistence.addAll(Arrays.asList(FUOCO, ACQUA, ERBA, ELETTRO));
		SPETTRO.resistence.addAll(Arrays.asList(VELENO, COLEOTTERO));
		BUIO.resistence.addAll(Arrays.asList(SPETTRO, BUIO));
		ACCIAIO.resistence.addAll(Arrays.asList(NORMALE, VOLANTE, ROCCIA, ACCIAIO, ERBA, PSICO, GHIACCIO, DRAGO, FOLLETTO));
		FOLLETTO.resistence.addAll(Arrays.asList(LOTTA, COLEOTTERO, BUIO));
		
		//Imposto le immunità dei tipi
		NORMALE.immune.add(SPETTRO);
		FOLLETTO.immune.add(DRAGO);
		SPETTRO.immune.addAll(Arrays.asList(NORMALE, LOTTA));
		VOLANTE.immune.add(TERRA);
		TERRA.immune.add(ELETTRO);
		BUIO.immune.add(PSICO);
		ACCIAIO.immune.add(VELENO);	
	}
	
	// restituisce true se il tipo passato come argomento é contenuto nella lista delle debolezze
	public boolean isWeakAgainst(Type type) {
		
		return weaknesses.contains(type);
	}
	
	// restituisce true se il tipo passato come argomento é contenuto nella lista delle immunità
	public boolean isImmuneAgainst(Type type) {
		
		return immune.contains(type);
	}
	
	// restituisce true se il tipo passato come argomento é contenuto nella lista delle resistenze
	public boolean isResistantAgainst(Type type) {
		
		return resistence.contains(type);
	}

}
