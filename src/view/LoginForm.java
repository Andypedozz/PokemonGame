package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import interfaces.menu.LoginObserver;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;

public class LoginForm extends JPanel {
	private LoginObserver observer;
	private int id;
	private BufferedImage bg;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton disconnect;
	private JButton signUp;
	private JLabel signUpLabel;
	private JButton login;
	private JLabel password;
	private JLabel username;
	private JLabel title;
	private JButton backBtn;
	
	public LoginForm(int id, LoginObserver observer) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 149, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		backBtn = new JButton("Indietro");
		backBtn.setForeground(Color.WHITE);
		backBtn.setEnabled(true);
		backBtn.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_backBtn = new GridBagConstraints();
		gbc_backBtn.insets = new Insets(0, 0, 5, 5);
		gbc_backBtn.gridx = 0;
		gbc_backBtn.gridy = 0;
		add(backBtn, gbc_backBtn);
		
		title = new JLabel("LOGIN");
		title.setBackground(Color.DARK_GRAY);
		title.setForeground(Color.WHITE);
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 2;
		gbc_title.gridy = 3;
		add(title, gbc_title);
		
		username = new JLabel("Username");
		username.setBackground(Color.DARK_GRAY);
		username.setForeground(Color.WHITE);
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.gridx = 2;
		gbc_username.gridy = 5;
		add(username, gbc_username);
		
		usernameField = new JTextField();
		usernameField.setBackground(Color.DARK_GRAY);
		usernameField.setForeground(Color.WHITE);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 2;
		gbc_usernameField.gridy = 6;
		add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		password = new JLabel("Password");
		password.setBackground(Color.DARK_GRAY);
		password.setForeground(Color.WHITE);
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.gridx = 2;
		gbc_password.gridy = 8;
		add(password, gbc_password);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setForeground(Color.WHITE);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 9;
		add(passwordField, gbc_passwordField);
		
		login = new JButton("Accedi");
		login.setBackground(Color.DARK_GRAY);
		login.setForeground(Color.WHITE);
		GridBagConstraints gbc_login = new GridBagConstraints();
		gbc_login.insets = new Insets(0, 0, 5, 5);
		gbc_login.gridx = 2;
		gbc_login.gridy = 11;
		add(login, gbc_login);
		try{
			if(id == 0)
				bg = ImageIO.read(new File(".\\resources\\images\\dialga_bg.jpg"));
			else
				bg = ImageIO.read(new File(".\\resources\\images\\palkia_bg.jpg"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		this.id = id;
		this.observer = observer;
		
		signUpLabel = new JLabel("Non hai un account?");
		signUpLabel.setBackground(Color.DARK_GRAY);
		signUpLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_signUpLabel = new GridBagConstraints();
		gbc_signUpLabel.insets = new Insets(0, 0, 5, 5);
		gbc_signUpLabel.gridx = 0;
		gbc_signUpLabel.gridy = 14;
		add(signUpLabel, gbc_signUpLabel);
		
		signUp = new JButton("Registrati");
		signUp.setBackground(Color.DARK_GRAY);
		signUp.setForeground(Color.WHITE);
		GridBagConstraints gbc_signUp = new GridBagConstraints();
		gbc_signUp.insets = new Insets(0, 0, 0, 5);
		gbc_signUp.gridx = 0;
		gbc_signUp.gridy = 15;
		add(signUp, gbc_signUp);
		
		disconnect = new JButton("Disconnetti");
		disconnect.setBackground(Color.DARK_GRAY);
		disconnect.setForeground(Color.WHITE);
		GridBagConstraints gbc_disconnect = new GridBagConstraints();
		gbc_disconnect.gridx = 4;
		gbc_disconnect.gridy = 15;
		add(disconnect, gbc_disconnect);
		this.disconnect.setEnabled(false);
		this.initListeners();
	}
	
	private void initListeners() {
		// listener login button
		this.login.addActionListener(e -> {
			login();
		});
		
		// listener signUp button
		this.signUp.addActionListener(e -> {
			signUp();
		});
		
		// logout logic
		this.disconnect.addActionListener(e -> {
			observer.disconnect(id);
			enableButtons();
		});

		KeyListener keyLogin = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		};
		this.usernameField.addKeyListener(keyLogin);
		this.passwordField.addKeyListener(keyLogin);
		
		if(this.id == 1) {
			this.remove(backBtn);
		}else {
			this.backBtn.addActionListener(e -> {
				observer.firstMenu();
			});
		}
	}

	private void login() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		observer.login(id,username,password);
	}
	
	public void loginFailed() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Account non trovato, cambia credenziali");
	}
	
	public void signUp() {
		SignUpForm sf = new SignUpForm();
		
		// sign up logic
		sf.confirm.addActionListener(e -> {
			String username = sf.usernameField.getText();
			String password = sf.passwordField.getText();
			String name = sf.nameField.getText();
			String gender = (String)sf.genderField.getSelectedItem();
			observer.register(username,password,name, gender);
		});
	}
	
	public void disableButtons() {
		this.usernameField.setEnabled(false);
		this.passwordField.setEnabled(false);
		this.signUp.setEnabled(false);
		this.login.setEnabled(false);
		this.disconnect.setEnabled(true);
	}
	
	public void enableButtons() {
		this.usernameField.setEnabled(true);
		this.usernameField.setText("");
		this.passwordField.setEnabled(true);
		this.passwordField.setText("");
		this.signUp.setEnabled(true);
		this.login.setEnabled(true);
		this.disconnect.setEnabled(false);
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(bg != null) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(),this);
        }
    }

}
