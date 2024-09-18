package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import interfaces.ViewObserver;
import interfaces.Pokemon;

public class InfoPanel extends JPanel{
	private ViewObserver observer;
	
	// pokemon image
	private JLabel pokemonImage;
	
	// panels
	private JPanel statsPanel, movesPanel, imagePanel, descPanel, typePanel;
	
	// tables
	private JTextField[] moveNames, moveTypes, moveDesc;
	private JTextField[] types;
	
	// stats labels
	private JTextField[] statNames, statValues;
	private JButton back;
	
	public InfoPanel(ViewObserver observer) {
		this.observer = observer;
		this.setLayout(new GridLayout(2,2));
		
		// creating panels
		imagePanel = new JPanel();
		movesPanel = new JPanel();
		movesPanel.setLayout(new GridLayout(4,2));
		descPanel = new JPanel();
		descPanel.setLayout(new GridLayout(4,1));
		statsPanel = new JPanel();
		
		// MOVES
		// creating move fields
		moveNames = new JTextField[4];
		moveTypes = new JTextField[4];
		moveDesc = new JTextField[4];
		
		// loading text into moves TextFields
		for(int i = 0; i < 4; i++) {
			moveNames[i] = new JTextField();
			moveTypes[i] = new JTextField();
			moveDesc[i] = new JTextField();
		}
		
		// setting uneditable
		for(int i = 0; i < 4; i++) {
			moveNames[i].setEditable(false);
			moveNames[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			moveTypes[i].setEditable(false);
			moveTypes[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			moveDesc[i].setEditable(false);		
			moveDesc[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}
		
		// and adding
		for(int i = 0; i < 4; i++) {
			movesPanel.add(moveNames[i]);
			movesPanel.add(moveTypes[i]);
			descPanel.add(moveDesc[i]);
		}
		
		// STATS
		statsPanel.setLayout(new GridLayout(6,2));
		statNames = new JTextField[6];
		statValues = new JTextField[6];
		statNames[0] = new JTextField("HP");
		statNames[1] = new JTextField("Attacco");
		statNames[2] = new JTextField("Difesa");
		statNames[3] = new JTextField("Velocita");
		statNames[4] = new JTextField("Att. Speciale");
		statNames[5] = new JTextField("Dif. Speciale");
		for(int i = 0; i < 6; i++) {
			statNames[i].setEditable(false);
			statNames[i].setHorizontalAlignment(JTextField.CENTER);
			statNames[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			statValues[i] = new JTextField();
			statValues[i].setEditable(false);
			statValues[i].setHorizontalAlignment(JTextField.CENTER);
			statValues[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}
		
		for(int i = 0; i < 6; i++) {
			statsPanel.add(statNames[i]);
			statsPanel.add(statValues[i]);
		}
		
		// IMAGE and Back
		imagePanel.setLayout(new GridLayout(3,1));
		back = new JButton("Indietro");
		pokemonImage = new JLabel();
		pokemonImage.setHorizontalAlignment(JLabel.CENTER);
		types = new JTextField[2];
		for(int i = 0; i < 2; i++) { 
			types[i] = new JTextField();
			types[i].setEditable(false);
			types[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}
		typePanel = new JPanel();
		typePanel.setLayout(new GridLayout(1,2));
		typePanel.add(types[0]);
		typePanel.add(types[1]);
		imagePanel.add(back);
		imagePanel.add(pokemonImage);
		imagePanel.add(typePanel);
		
		// setting colors
		for(int i = 0; i < 4; i++) {
			Color c = new Color(146, 231, 252);
			moveNames[i].setBackground(c);
			
		}
		for(int i = 0; i < 6; i++) {
			Color c = new Color(146, 231, 252);
			statNames[i].setBackground(c);
		}
		
		this.add(imagePanel);
		this.add(statsPanel);
		this.add(movesPanel);
		this.add(descPanel);
		initListeners();
	}
	
	public void initListeners() {
		this.back.addActionListener(e -> {
			observer.initTeam();
		});
	}
	
	// metodo per visualizzare le informazioni sul pokemon
	public void loadInfo(Pokemon pokemon) {
		// load image and text
		try {
			BufferedImage image = ImageIO.read(new File(pokemon.getIconString()));
			Icon icon = new ImageIcon((pokemon.getIconString()));
			pokemonImage.setIcon(icon);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		moveNames[0].setText(pokemon.getMoveSet().getMove1().getName());
		moveNames[1].setText(pokemon.getMoveSet().getMove2().getName());
		moveNames[2].setText(pokemon.getMoveSet().getMove3().getName());
		moveNames[3].setText(pokemon.getMoveSet().getMove4().getName());
		moveTypes[0].setText(pokemon.getMoveSet().getMove1().getType().toString());
		moveTypes[1].setText(pokemon.getMoveSet().getMove2().getType().toString());
		moveTypes[2].setText(pokemon.getMoveSet().getMove3().getType().toString());
		moveTypes[3].setText(pokemon.getMoveSet().getMove4().getType().toString());
		moveDesc[0].setText(pokemon.getMoveSet().getMove1().getDescription());
		moveDesc[1].setText(pokemon.getMoveSet().getMove2().getDescription());
		moveDesc[2].setText(pokemon.getMoveSet().getMove3().getDescription());
		moveDesc[3].setText(pokemon.getMoveSet().getMove4().getDescription());
		statValues[0].setText(String.valueOf(pokemon.getStats().getHp()));
		statValues[1].setText(String.valueOf(pokemon.getStats().getAttack()));
		statValues[2].setText(String.valueOf(pokemon.getStats().getDefense()));
		statValues[3].setText(String.valueOf(pokemon.getStats().getSpeed()));
		statValues[4].setText(String.valueOf(pokemon.getStats().getSpAttack()));
		statValues[5].setText(String.valueOf(pokemon.getStats().getSpDefense()));
		types[0].setText(pokemon.getType().get(0).toString());
		if(pokemon.getType().size() > 1)
			types[1].setText(pokemon.getType().get(1).toString());
	}
	
}
