package view;
import javax.swing.*;

import interfaces.BattleObserver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchFrame extends JFrame{
	
	private PokemonButton btnSwitch; 
    private PokemonButton btnSwitch2;
    private JButton btnBack;
    private JLabel emptyLabel;
    private JPanel switchPanel;
    private BattleObserver observer;
    
    public SwitchFrame(int x, int y, boolean team) {
    	
    	setLocation(x,y);
    	setSize(400,200);
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	
        btnSwitch = new PokemonButton();
        btnSwitch2 = new PokemonButton();
        btnBack = new JButton("Indietro");
        emptyLabel = new JLabel();
        
        switchPanel = new JPanel();
        
        btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				observer.resetButtons(team);
				setVisible(false);
			}	
        });
        
        btnSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeSwitchAction(btnSwitch, team);
			}	
        });
        
        btnSwitch2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeSwitchAction(btnSwitch2, team);
			}	
        });
        switchPanel.setLayout(new GridLayout(2,2,10,10));
        switchPanel.add(btnBack);
        switchPanel.add(btnSwitch);
        switchPanel.add(emptyLabel);
        switchPanel.add(btnSwitch2);
        
        this.add(switchPanel);
    }
    
    public PokemonButton getBtnSwitch1() {
    	return this.btnSwitch;
    }
    
    public PokemonButton getBtnSwitch2() {
    	return this.btnSwitch2;
    }
    
    public JButton getBtnBack() {
    	return this.btnBack;
    }
    
    public void setObserver(BattleObserver observer){
		this.observer = observer;
	}
}
