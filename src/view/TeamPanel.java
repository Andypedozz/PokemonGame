package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import interfaces.menu.TeamObserver;

public class TeamPanel extends JPanel{
	
	private TeamObserver observer;
	private JPanel leftPanel, rightPanel, centerPanel, leftCorner, rightCorner;
	private LobbyPokemonButton pokeButtons[];
	private JScrollPane gridPane;
	private List<LobbyPokemonButton> teams[];
	private ImageIcon icons[];
	private JButton back, addPokemonLeft, seePoke, removePokeLeft, deselect, play, addPokemonRight, removePokeRight;
	private LobbyPokemonButton selected;
	
	public TeamPanel(TeamObserver observer) {
		this.observer = observer;
		this.setSize(800,600);
		this.setLayout(new GridLayout(1,3));
		
		// init components
		// buttons panel
		back = new JButton("Indietro");
		addPokemonLeft = new JButton("Aggiungi");
		addPokemonRight = new JButton("Aggiungi");
		seePoke = new JButton("Info");
		removePokeLeft = new JButton("Rimuovi");
		removePokeRight = new JButton("Rimuovi");
		deselect = new JButton("Deseleziona");
		play = new JButton("Gioca");
		
		// panels
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		centerPanel = new JPanel();
		leftCorner = new JPanel();
		rightCorner = new JPanel();
		leftCorner.setLayout(new BorderLayout());
		rightCorner.setLayout(new BorderLayout());
		leftPanel.setLayout(new GridLayout(4,1));
		rightPanel.setLayout(new GridLayout(4,1));
		centerPanel.setLayout(new GridLayout(observer.getPokedexSize(),1));
		leftCorner.add(back, BorderLayout.WEST);
		leftCorner.add(addPokemonLeft, BorderLayout.EAST);
		leftCorner.add(seePoke, BorderLayout.NORTH);
		leftCorner.add(removePokeLeft, BorderLayout.SOUTH);
		leftCorner.add(deselect, BorderLayout.CENTER);
		rightCorner.add(addPokemonRight, BorderLayout.WEST);
		rightCorner.add(removePokeRight, BorderLayout.EAST);
		rightCorner.add(play, BorderLayout.CENTER);

		// teams and grid
		gridPane = new JScrollPane(centerPanel);
		gridPane.setLayout(new ScrollPaneLayout());
		gridPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		gridPane.setWheelScrollingEnabled(true);
		gridPane.getVerticalScrollBar().setUnitIncrement(16);
		pokeButtons = new LobbyPokemonButton[observer.getPokedexSize()];
		this.teams = new LinkedList[2];
		teams[0] = new LinkedList<LobbyPokemonButton>();
		teams[1] = new LinkedList<LobbyPokemonButton>();

		this.leftPanel.add(leftCorner);
		this.rightPanel.add(rightCorner);
		for(int i = 0; i < observer.getPokedexSize(); i++) {
			pokeButtons[i] = new LobbyPokemonButton(i);
			centerPanel.add(pokeButtons[i]);
			if(i < 3) {
				teams[0].add(new LobbyPokemonButton(i));
				leftPanel.add(teams[0].get(i));
				teams[1].add(new LobbyPokemonButton(i));
				rightPanel.add(teams[1].get(i));
			}
		}
		
		//add components
		this.add(leftPanel);
		this.add(gridPane);
		this.add(rightPanel);
		this.setVisible(true);
		initListeners();
	}
	
	private void initListeners() {
		// back logic
		this.back.addActionListener(e -> observer.backFromTeam());
		
		// select from table
		ActionListener ac = e -> {
			resetSlots();
			final LobbyPokemonButton jb = (LobbyPokemonButton)e.getSource();
			jb.setEnabled(false);
			selected = jb;
			observer.selectPokemon(jb.getPokemon().getName());
		};
		for(int i = 0; i < observer.getPokedexSize(); i++)
			this.pokeButtons[i].addActionListener(ac);
		
		// select from team	
		ActionListener ac2 = e ->  {
			resetSlots();
			final LobbyPokemonButton jb = (LobbyPokemonButton)e.getSource();
			selected = jb;
			observer.selectFromTeam(0, jb.getIndex());
		};
		
		ActionListener ac3 = e -> {
			resetSlots();
			final LobbyPokemonButton jb = (LobbyPokemonButton)e.getSource();
			selected = jb;
			observer.selectFromTeam(1, jb.getIndex());
		};
		for(LobbyPokemonButton i : this.teams[0])
			i.addActionListener(ac2);
		for(LobbyPokemonButton i : this.teams[1])
			i.addActionListener(ac3);
		
		// add logic
		this.addPokemonLeft.addActionListener(e -> observer.addPokemon(0));
		this.addPokemonRight.addActionListener(e -> observer.addPokemon(1));
		
		// deselect logic
		this.deselect.addActionListener(e -> {
			selected = null;
			observer.deselect();
		});
		
		// info logic
		this.seePoke.addActionListener(e -> observer.infoScreen());
		
		// remove logic		
		this.removePokeLeft.addActionListener(e -> observer.removePokemon(0));
		this.removePokeRight.addActionListener(e -> observer.removePokemon(1));
		
		// play logic
		this.play.addActionListener(e -> observer.play());
	}
	
	public void pokemonNotSelected() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Pokemon non selezionato!");
	}
	
	public void teamFull() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Team pieno!");
	}
	
	public void resetSlots() {
		for(int i = 0; i < observer.getPokedexSize(); i++) {
			pokeButtons[i].setEnabled(true);
			if(i < 3) {
				teams[0].get(i).setEnabled(true);
				teams[1].get(i).setEnabled(true);
			}
		}
	}
	
	public void addPokemon(int select) {
		boolean added = false;
		for(JButton jb : this.teams[select]) {
			if(jb.getIcon() == null && !added) {
				jb.setIcon(this.selected.getIcon());
				added = true;
			}
		}
	}
	
	public JButton getSelected() {
		return this.selected;
	}
	
	public void removePokemon(int select, int index) {
		this.teams[select].get(index).setIcon(null);
	}
	
	public void resetTeam() {
		for(LobbyPokemonButton b : this.teams[0])
			b.setIcon(null);
	}
	
	public void selectFromTeam(int select, int index) {
		this.teams[select].get(index).setEnabled(false);
	}
	
	public void alreadyPresent() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Pokemon già presente in squadra!");
	}
	
	public void notReady() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "I due team non sono pronti!");
	}

	public LobbyPokemonButton[] getPokeButtons() {
		return pokeButtons;
	}
	
}
