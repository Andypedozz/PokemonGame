package view;

import javax.swing.*;

import interfaces.menu.LoginObserver;

import java.awt.Color;
import java.awt.Font;

public class SignUpForm extends JDialog {
	private LoginObserver observer;
	private JButton confirmBtn;
	private JTextField nameField;
	private JLabel nameLabel;
	private JLabel passwordLabel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public SignUpForm(LoginObserver observer) {
		this.observer = observer;
		initComponents();
	}

	public SignUpForm(LoginObserver observer, JFrame frame, String string, boolean b) {
		super(frame,string,b);
		this.observer = observer;
		initComponents();
	}

	private void initComponents() {
		getContentPane().setBackground(Color.DARK_GRAY);
		// creating frame
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,550);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Registrazione");
		getContentPane().setLayout(null);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(150, 71, 79, 14);
		getContentPane().add(usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setForeground(Color.WHITE);
		usernameField.setBackground(Color.GRAY);
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setBounds(30, 111, 319, 31);
		usernameField.setColumns(10);
		getContentPane().add(usernameField);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(150, 186, 79, 14);
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.WHITE);
		passwordField.setBackground(Color.GRAY);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(30, 229, 319, 31);
		getContentPane().add(passwordField);
		
		nameLabel = new JLabel("Nome");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setBounds(150, 311, 79, 14);
		getContentPane().add(nameLabel);
		
		nameField = new JTextField();
		nameField.setForeground(Color.WHITE);
		nameField.setBackground(Color.GRAY);
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(30, 355, 319, 31);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		confirmBtn = new JButton("Conferma");
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.setBackground(Color.GRAY);
		confirmBtn.setBounds(132, 461, 112, 23);
		getContentPane().add(confirmBtn);
		
		JLabel lblRegistrati = new JLabel("Registrati");
		lblRegistrati.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblRegistrati.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrati.setForeground(Color.WHITE);
		lblRegistrati.setBounds(132, 27, 112, 33);
		getContentPane().add(lblRegistrati);
		initListeners();
		this.setVisible(true);		
	}
	
	private void initListeners() {
		this.confirmBtn.addActionListener(e -> {
			String username = this.usernameField.getText();
			String password = this.passwordField.getText();
			String name = this.nameField.getText();
			observer.register(username, password, name);
			this.dispose();
		});
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getConfirm() {
		return confirmBtn;
	}
}
