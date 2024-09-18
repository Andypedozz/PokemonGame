package view;

import javax.swing.JPanel;
import interfaces.menu.GameTypeObserver;
import java.awt.GridLayout;
import javax.swing.JButton;

public class GameTypePanel extends JPanel {
	private GameTypeObserver observer;
	
	public GameTypePanel(GameTypeObserver observer) {
		this.observer = observer;
		setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton btnBattagliaSingola = new JButton("Battaglia Singola");
		add(btnBattagliaSingola);
		
		JButton btnBattagliaDoppia = new JButton("Battaglia Doppia");
		add(btnBattagliaDoppia);
		
		JButton btnBattagliaTripla = new JButton("Battaglia Tripla");
		add(btnBattagliaTripla);
		
	}
}
