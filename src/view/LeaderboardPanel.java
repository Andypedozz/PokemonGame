package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import interfaces.menu.Account;
import interfaces.menu.LeaderboardObserver;

public class LeaderboardPanel extends JPanel {
    private LeaderboardObserver observer;
    private JPanel centerPanel;
    private NonEditableTableModel tableModel;
    private JTable table;
    private JLabel title;
    private JButton back;

    public LeaderboardPanel(LeaderboardObserver observer) {
        this.observer = observer;
        initComponents();
        initListeners();
        loadRecords();
    }
    
    class NonEditableTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Tutte le celle sono non editabili
        }
    }

    private void initComponents() {
    	centerPanel = new JPanel();
    	title = new JLabel();
    	back = new JButton("Indietro");
    	setLayout(new BorderLayout());
    	title.setHorizontalAlignment(SwingConstants.CENTER);
    	title.setText("Classifica");
    	tableModel = new NonEditableTableModel();
    	tableModel.addColumn("Username");
    	tableModel.addColumn("Partite");
    	tableModel.addColumn("Vittorie");
    	tableModel.addColumn("Sconfitte");
    	
    	table = new JTable(tableModel);
    	JScrollPane rankingPanel = new JScrollPane(table);
    	
    	table.getTableHeader().setReorderingAllowed(false);
    	table.setColumnSelectionAllowed(false);
    	table.setRowSelectionAllowed(false);
    	table.setCellSelectionEnabled(false);
    	
    	add(title, BorderLayout.NORTH);
    	add(back, BorderLayout.SOUTH);
    	add(rankingPanel, BorderLayout.CENTER);
    }

    private void initListeners() {
        back.addActionListener(e -> {
            observer.back();
        });
    }
    
    private void loadRecords() {
    	List<Account> accounts = observer.getAccounts();
    	
    	for(Account account : accounts) {
    		int posizione = tableModel.getRowCount() + 1;
    		tableModel.addRow(new Object[] {
    				account.getUsername(),
    				account.getMatches(),
    				account.getWins(),
    				account.getLosses()});
    	}
    }
}
