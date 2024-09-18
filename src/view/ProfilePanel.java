package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class ProfilePanel extends JPanel {
	
	private int id;
	private BufferedImage bg;
	private JButton backBtn;
	private JTextField usernameValue;
	private JTextField matchesValue;
	private JTextField winsValue;
	private JTextField lossesValue;
	private JTextField lblProfile;
	private JTextField lblUsername;
	private JTextField lblPartite;
	private JTextField lblVittorie;
	private JTextField lblSconfitte;
	
	public ProfilePanel() {
		initComponents();
	}
	
	public ProfilePanel(int id) {
		this.id = id;
		initComponents();
	}
	
	private void initComponents() {
        try {
        	if(id == 0)
        		bg = ImageIO.read(new File(".\\resources\\images\\blackSide.jpg"));
        	else
        		bg = ImageIO.read(new File(".\\resources\\images\\whiteSide.jpg"));
    	}catch(Exception e) {
    		System.out.println(e);
    	}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 61, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblProfile = new JTextField("");
		GridBagConstraints gbc_lblProfile = new GridBagConstraints();
		gbc_lblProfile.insets = new Insets(10, 0, 5, 5);
		gbc_lblProfile.gridx = 2;
		gbc_lblProfile.gridy = 1;
		add(lblProfile, gbc_lblProfile);
		
		lblUsername = new JTextField("Username");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblUsername, gbc_lblNewLabel);
		
		usernameValue = new JTextField("");
		usernameValue.setHorizontalAlignment(SwingConstants.CENTER);
		usernameValue.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		GridBagConstraints gbc_usernameValue = new GridBagConstraints();
		gbc_usernameValue.ipady = 10;
		gbc_usernameValue.ipadx = 30;
		gbc_usernameValue.insets = new Insets(0, 0, 5, 5);
		gbc_usernameValue.gridx = 3;
		gbc_usernameValue.gridy = 2;
		add(usernameValue, gbc_usernameValue);
		
		lblPartite = new JTextField("Partite");
		GridBagConstraints gbc_lblPartite = new GridBagConstraints();
		gbc_lblPartite.insets = new Insets(0, 0, 5, 5);
		gbc_lblPartite.gridx = 1;
		gbc_lblPartite.gridy = 4;
		add(lblPartite, gbc_lblPartite);
		
		matchesValue = new JTextField("");
		matchesValue.setHorizontalAlignment(SwingConstants.CENTER);
		matchesValue.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		GridBagConstraints gbc_matchesValue = new GridBagConstraints();
		gbc_matchesValue.ipady = 10;
		gbc_matchesValue.ipadx = 30;
		gbc_matchesValue.insets = new Insets(0, 0, 5, 5);
		gbc_matchesValue.gridx = 3;
		gbc_matchesValue.gridy = 4;
		add(matchesValue, gbc_matchesValue);
		
		lblVittorie = new JTextField("Vittorie");
		GridBagConstraints gbc_lblVittorie = new GridBagConstraints();
		gbc_lblVittorie.insets = new Insets(0, 0, 5, 5);
		gbc_lblVittorie.gridx = 1;
		gbc_lblVittorie.gridy = 6;
		add(lblVittorie, gbc_lblVittorie);
		
		winsValue = new JTextField("");
		winsValue.setHorizontalAlignment(SwingConstants.CENTER);
		winsValue.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		GridBagConstraints gbc_winsValue = new GridBagConstraints();
		gbc_winsValue.ipady = 10;
		gbc_winsValue.ipadx = 30;
		gbc_winsValue.insets = new Insets(0, 0, 5, 5);
		gbc_winsValue.gridx = 3;
		gbc_winsValue.gridy = 6;
		add(winsValue, gbc_winsValue);
		
		lblSconfitte = new JTextField("Sconfitte");
		GridBagConstraints gbc_lblSconfitte = new GridBagConstraints();
		gbc_lblSconfitte.insets = new Insets(0, 0, 5, 5);
		gbc_lblSconfitte.gridx = 1;
		gbc_lblSconfitte.gridy = 8;
		add(lblSconfitte, gbc_lblSconfitte);
		
		lossesValue = new JTextField("");
		lossesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lossesValue.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		GridBagConstraints gbc_lossesValue = new GridBagConstraints();
		gbc_lossesValue.ipady = 10;
		gbc_lossesValue.ipadx = 30;
		gbc_lossesValue.insets = new Insets(0, 0, 5, 5);
		gbc_lossesValue.gridx = 3;
		gbc_lossesValue.gridy = 8;
		add(lossesValue, gbc_lossesValue);
		
		backBtn = new JButton("Indietro");
		GridBagConstraints gbc_backBtn = new GridBagConstraints();
		gbc_backBtn.insets = new Insets(0, 0, 10, 5);
		gbc_backBtn.gridx = 3;
		gbc_backBtn.gridy = 10;
		add(backBtn, gbc_backBtn);
		
		usernameValue.setEditable(false);
		matchesValue.setEditable(false);
		winsValue.setEditable(false);
		lossesValue.setEditable(false);
		
		this.lblProfile.setEditable(false);
		this.lblUsername.setEditable(false);
		this.lblPartite.setEditable(false);
		this.lblVittorie.setEditable(false);
		this.lblSconfitte.setEditable(false);
		this.usernameValue.setEditable(false);
		this.lossesValue.setEditable(false);
	}
	
	public JButton getBackButton() {
		return this.backBtn;
	}
	
	public void loadData(List<String> info) {
		this.lblProfile.setText("Profilo "+(id+1));
		this.usernameValue.setText(info.get(0));
		this.matchesValue.setText(info.get(1));
		this.winsValue.setText(info.get(2));
		this.lossesValue.setText(info.get(3));
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(),this);
        }
    }

}
