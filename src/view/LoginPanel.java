package view;

import java.awt.GridLayout;
import javax.swing.*;

import interfaces.menu.LoginObserver;

public class LoginPanel extends JPanel {
	private LoginObserver observer;
	private SignUpForm signUpForm;
	private JFrame frame;
	private LoginForm loginForms[];
	
	public LoginPanel(LoginObserver observer, JFrame frame) {
		// init settings
		this.observer = observer;
		this.frame = frame;
		this.setSize(800,600);
		this.setLayout(new GridLayout(1,2));
		
		this.loginForms = new LoginForm[2];
		this.loginForms[0] = new LoginForm(0,observer);
		this.loginForms[1] = new LoginForm(1,observer);
		this.add(loginForms[0]);
		this.add(loginForms[1]);
		this.setVisible(true);
		initListeners();
	}
	
	private void initListeners() {
		
		this.loginForms[0].getSignUpBtn().addActionListener(e -> {
			signUp();
		});
		
		this.loginForms[1].getSignUpBtn().addActionListener(e -> {
			signUp();
		});
	}
	
	public void signUp() {
		this.signUpForm = new SignUpForm(observer,frame,"Registrati",true);
	}
	
	public SignUpForm getSignUpForm() {
		return this.signUpForm;
	}
	
	public void accountNotFound() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Account non trovato, cambia credenziali");
	}
	
	public void alreadyInUse() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Account già in uso!");
	}

	public void obscurePanel(int select) {
		this.loginForms[select].disableButtons();
	}
	
	public void signUpFailed() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Campi invalidi!");
	}

	public void accountAlreadyRegistered() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Account già esistente!");
	}
	
	public void signedUpSuccessfully() {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Account registrato correttamente!");
	}
}
