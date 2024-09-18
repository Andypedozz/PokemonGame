package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import interfaces.BattleObserver;

public class BattlePanel extends JPanel{
	
	private JButton btnBack;
    private JButton btnPokemon;
    private JButton btnBack2;
    private JButton btnPokemon2;
    private MoveButton btnMove1;
    private MoveButton btnMove2;
    private MoveButton btnMove3;
    private MoveButton btnMove4;
    private MoveButton btnMove5;
    private MoveButton btnMove6;
    private MoveButton btnMove7;
    private MoveButton btnMove8;
    private JPanel pokemonPanel;
    private JPanel pokemonPanel2;
    private JPanel askPanel;
    private JPanel askPanel2;
    private JPanel actionPanel;
    private JPanel actionPanel2;
    private JProgressBar healthBar1;
    private JProgressBar healthBar2;
    private JLabel nameLabel;
    private JLabel nameLabel2;
    private JLabel iconLabel;
    private JLabel iconLabel2;
    private JLabel askLabel;
    private JLabel askLabel2;
    private SwitchFrame switchFrame;
    private SwitchFrame switchFrame2;
       
    private BattleObserver observer;
    
    public BattlePanel() {
    	
    	this.setLayout(new GridLayout(5,2,10,10));
        this.setSize(800,600);
        
        
        healthBar1 = new JProgressBar();
        healthBar2 = new JProgressBar();
        btnBack = new JButton("Indietro");
        btnPokemon = new JButton("Pokemon");
        btnMove1 = new MoveButton();
        btnMove2 = new MoveButton();
        btnMove3 = new MoveButton();
        btnMove4 = new MoveButton();
        btnPokemon2 = new JButton("Pokemon");
        btnBack2 = new JButton("indietro");
        btnMove5 = new MoveButton();
        btnMove6 =new MoveButton();
        btnMove7 = new MoveButton();
        btnMove8 = new MoveButton();
        pokemonPanel = new JPanel();
        pokemonPanel2 = new JPanel();
        askPanel = new JPanel();
        askPanel2 = new JPanel();
        actionPanel = new JPanel();
        actionPanel2 = new JPanel();
        nameLabel = new JLabel("pokemon 1");
        nameLabel2 = new JLabel("pokemon 2");
        iconLabel = new JLabel("icon 1");
        iconLabel2 = new JLabel("icon 2");
        askLabel = new JLabel("ask 1");
        askLabel2 = new JLabel("ask 2");
        
        pokemonPanel.setLayout(new GridLayout(2,1,10,10));
        pokemonPanel.add(healthBar1);
        pokemonPanel.add(nameLabel);
        pokemonPanel.add(iconLabel);
        
        pokemonPanel2.setLayout(new GridLayout(2,1,10,10));
        pokemonPanel2.add(healthBar2);
        pokemonPanel2.add(nameLabel2);
        pokemonPanel2.add(iconLabel2);
        
        askPanel.add(askLabel); 
        askPanel2.add(askLabel2);
        
        actionPanel.setLayout(new GridLayout(2,2));
        actionPanel.add(btnBack);
        actionPanel.add(btnMove1);
        actionPanel.add(btnMove2);
        actionPanel.add(btnPokemon);
        actionPanel.add(btnMove3);
        actionPanel.add(btnMove4);
        
        btnMove1.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove1, true);
			}	
        });
        
        btnMove2.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove2, true);
			}	
        });
        
        btnMove3.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove3, true);
			}	
        });
        
        btnMove4.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove4, true);
			}	
        });
        
        btnPokemon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSwitchFrame();
			}
        });
        
        btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				observer.deleteSelectedActionForTeam1();
			}
        });
          
        actionPanel2.setLayout(new GridLayout(2,2));
        actionPanel2.add(btnBack2);
        actionPanel2.add(btnMove5);
        actionPanel2.add(btnMove6);
        actionPanel2.add(btnPokemon2);
        actionPanel2.add(btnMove7);
        actionPanel2.add(btnMove8);
        
        btnMove5.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove5, false);
			}	
        });
        
        btnMove6.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove6, false);
			}	
        });
        
        btnMove7.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove7, false);
			}	
        });
        
        btnMove8.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				observer.storeMove(btnMove8, false);
			}	
        });
        
        btnPokemon2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSwitchFrame2();
			}
        });
        
        btnBack2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				observer.deleteSelectedActionForTeam2();
			}
        });
        
        this.add(pokemonPanel);
        this.add(pokemonPanel2);
        this.add(iconLabel);
        this.add(iconLabel2);
        this.add(askPanel);
        this.add(askPanel2);
        this.add(actionPanel);
        this.add(actionPanel2);
        
    }
                      
    public void setBtnMove1(MoveButton btnMove1) {
    	this.btnMove1 = btnMove1;
    }

    public void setBtnMove2(MoveButton btnMove2) {
    	
    	this.btnMove2 = btnMove2;
    }

    public void setBtnMove3(MoveButton btnMove3) {
    	
    	this.btnMove3 = btnMove3;
    }

    public void setBtnMove4(MoveButton btnMove4) {
    	
    	this.btnMove4 = btnMove4;
    }
    
    public void setBtnMove5(MoveButton btnMove5) {
    	this.btnMove5 = btnMove5;
    }

    public void setBtnMove6(MoveButton btnMove6) {
    	
    	this.btnMove6 = btnMove6;
    }

    public void setBtnMove7(MoveButton btnMove7) {
    	
    	this.btnMove7 = btnMove7;
    }

    public void setBtnMove8(MoveButton btnMove8) {
    	
    	this.btnMove8 = btnMove8;
    }

    public MoveButton getBtnMove1() {
    	
    	return this.btnMove1;
    }

    public MoveButton getBtnMove2() {
    	
    	return this.btnMove2;
    }

    public MoveButton getBtnMove3() {
    	
    	return this.btnMove3;
    }

    public MoveButton getBtnMove4() {
    	
    	return this.btnMove4;
    }
    
    public MoveButton getBtnMove5() {
    	
    	return this.btnMove5;
    }

    public MoveButton getBtnMove6() {
    	
    	return this.btnMove6;
    }

    public MoveButton getBtnMove7() {
    	
    	return this.btnMove7;
    }

    public MoveButton getBtnMove8() {
    	
    	return this.btnMove8;
    }
    
    public JButton getBtnBack() {
    	
    	return this.btnBack;
    }
    
    public JButton getBtnBack2() {
    	
    	return this.btnBack2;
    }
    
    public void setEnableforBtn(boolean enabled) {
    	
    	this.btnMove1.setEnabled(enabled);
    	this.btnMove2.setEnabled(enabled);
    	this.btnMove3.setEnabled(enabled);
    	this.btnMove4.setEnabled(enabled);
    	this.btnPokemon.setEnabled(enabled);
    }
    
    public void setEnableforBtn2(boolean enabled) {
    	this.btnMove5.setEnabled(enabled);
    	this.btnMove6.setEnabled(enabled);
    	this.btnMove7.setEnabled(enabled);
    	this.btnMove8.setEnabled(enabled);
    	this.btnPokemon2.setEnabled(enabled);
    }
    
    public void setLabel1Info(String s) {
    	
    	this.nameLabel.setText(s);;
    }

    public void setLabel2Info(String s) {
    	
    	this.nameLabel2.setText(s);;
    }

    public void setAskLabel(String s) {
    	
    	this.askLabel.setText(s);
    }
    
    public void setAskLabel2(String s) {
    	
    	this.askLabel2.setText(s);
    }

    public void setIcon1(String s) {
    	ImageIcon icon = null;
    	try {
    		BufferedImage image = ImageIO.read(new File(s));
    		icon = new ImageIcon(image);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	Image originalImage = icon.getImage();
    	Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    	this.iconLabel.setIcon(new ImageIcon(scaledImage));
    	this.iconLabel.setText("");
    }

    public void setIcon2(String s) {
    	ImageIcon icon = null;
    	try {
    		BufferedImage image = ImageIO.read(new File(s));
    		icon = new ImageIcon(image);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	Image originalImage = icon.getImage();
    	Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    	this.iconLabel2.setIcon(new ImageIcon(scaledImage));
    	this.iconLabel2.setText("");
    }
    
    public void setProgressBar1(int n) {
    	
    	this.healthBar1.setMaximum(n);
    }
    
    public void setProgressBar2(int n) {
    	
    	this.healthBar2.setMaximum(n);
    }
    
    public void updateProgressBar1(int n) {
    	
    	this.healthBar1.setValue(n);
    }
    
    public void updateProgressBar2(int n) {
    	
    	this.healthBar2.setValue(n);
    }
    
    public void setObserver(BattleObserver observer){
		this.observer = observer;
	}
    
    public JPanel getActionPanel1() {
    	return this.actionPanel;  	
    }
    
    public JPanel getActionPanel2() {
    	return this.actionPanel2;
    }
    
    public void setSwitchFrame() {
    	
    	this.switchFrame = new SwitchFrame(100,100, true);
    	this.switchFrame.setVisible(true);
    	this.observer.setSwitchViewForTeam1();
    }
    
    public void setSwitchFrame2() {
    	
    	this.switchFrame2 = new SwitchFrame(1000,100, false);
    	this.switchFrame2.setVisible(true);
    	this.observer.setSwitchViewForTeam2();
    }

    public SwitchFrame getSwitchFrame() {
    	return this.switchFrame;
    }
    
    public SwitchFrame getSwitchFrame2() {
    	return this.switchFrame2;
    }
    
    public void isFainted() {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame, "Il pokemon selezionato é troppo esausto per lottare!");
    }
    
    public void winScreen(String s) {
    	JFrame frame = new JFrame();
    	JOptionPane.showMessageDialog(frame, "Vincitore " + s);
    }
}
