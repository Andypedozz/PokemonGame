package view;

import javax.swing.*;

public class SignUpForm extends JFrame{
	
	JTextField usernameField, nameField;
	JPasswordField passwordField;
	JComboBox<String> genderField;
	JLabel usernameLabel, passwordLabel, nameLabel, genderLabel;
	JButton confirm;
	String[] genders = {"Maschio","Femmina","Altro"};
	
	public SignUpForm() {
		// creating frame
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Registrazione");
		
		// creating components
		usernameField = new JTextField();
		nameField = new JTextField();
		passwordField = new JPasswordField();
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		nameLabel = new JLabel("Nome");
		genderLabel = new JLabel("Genere");
		confirm = new JButton("Registrati");
		genderField = new JComboBox<String>(genders);
		
		// setting bounds
		usernameField.setBounds(170,100,150,30);
		passwordField.setBounds(170,150,150,30);
		nameField.setBounds(170,200,150,30);
		usernameLabel.setBounds(100,100,100,20);
		passwordLabel.setBounds(100,150,100,20);
		nameLabel.setBounds(100,200,100,20);
		genderLabel.setBounds(100,250,100,20);
		genderField.setBounds(170,250,100,20);
		confirm.setBounds(170,320,100,30);
		
		// adding components
		this.add(usernameField);
		this.add(passwordField);
		this.add(nameField);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(nameLabel);
		this.add(genderLabel);
		this.add(confirm);
		this.add(genderField);
		
		this.setVisible(true);
	}
}
