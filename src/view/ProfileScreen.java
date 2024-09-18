package view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import interfaces.menu.ProfileObserver;

public class ProfileScreen extends JPanel {
	private ProfileObserver observer;
	private ProfilePanel panelProfile1;
	private ProfilePanel panelProfile2;
	
	public ProfileScreen(ProfileObserver observer) {
		this.observer = observer;
		this.setLayout(new GridLayout(1,2));
		this.panelProfile1 = new ProfilePanel(0);
		this.panelProfile2 = new ProfilePanel(1);
		this.panelProfile1.getBackButton().setVisible(false);
		this.add(panelProfile1);
		this.add(panelProfile2);
		initListeners();
		loadInfo();
	}
	
	private void initListeners() {
		this.panelProfile2.getBackButton().addActionListener(e -> {
			observer.back();
		});
	}
	
	private void loadInfo() {
		List<String> info1 = observer.getAccountInfo(0);
		List<String> info2 = observer.getAccountInfo(1);
		this.panelProfile1.loadData(info1);
		this.panelProfile2.loadData(info2);
	}
}
