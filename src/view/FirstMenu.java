package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import interfaces.ViewObserver;

public class FirstMenu extends JPanel {

    private ViewObserver observer;
    private BufferedImage background;
    private JButton guestBtn;
    private JButton loginBtn;
    private JTextArea messageTextArea;
    private JToolBar toolBar;

    public FirstMenu(ViewObserver observer) {
        this.observer = observer;
        initComponents();
        initListeners();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;
        try{
            background = ImageIO.read(new File(".\\resources\\images\\dialga_bg_dark.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }

        messageTextArea = new JTextArea();
        toolBar = new JToolBar();
        toolBar.setOpaque(false);
        guestBtn = new JButton();
        loginBtn = new JButton();

        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());

        messageTextArea.setEditable(false);
        messageTextArea.setColumns(20);
        messageTextArea.setFont(new Font("Verdana", 0, 12));
        messageTextArea.setRows(5);
        messageTextArea.setOpaque(false);
        messageTextArea.setForeground(Color.white);
        messageTextArea.setText("Benvenuto in PokemonBattleMania!\n\n"
        		+ "Istruzioni:\n\n"
        		+ "SCHERMATA INIZIALE\n"
        		+ "Da questa schermata potete scegliere se giocare in modalità Ospite oppure in modalità Logged.\n"
        		+ "Nel secondo caso vi verrà chiesto di creare un account per memorizzare i dati delle partite giocate.\n\n"
        		+ "MENU PRINCIPALE\n"
        		+ "All'interno del menu principale potrete visualizzare diverse schermate. Premendo su:\n"
        		+ "* 'Classifica' potrete visualizzare la classifica degli account registrati, ordinata in base al maggior numero di vittorie.\n"
        		+ "* 'Profilo' potrete visualizzare i dati del vostro account singolarmente.\n"
        		+ "* 'Gioca' potrete passare alla schermata di preparazione alla partita.\n"
        		+ "* 'Disconnetti' potrete disconnettervi e tornare alla schermata precedente.\n\n"
        		+ "SCHERMATA LOBBY\n"
        		+ "Da questa schermata potete premere su: \n"
        		+ "* 'Aggiungi' per aggiungere un Pokemon, a destra o sinistra in base alla squadra, dopo averlo selezionato premendovi sopra\n"
        		+ "* 'Rimuovi' per rimuovere un Pokemon da una squadra\n"
        		+ "* 'Info' per visualizzare le statistiche di un Pokemon\n"
        		+ "* 'Gioca' per iniziare la partita quando le squadre saranno entrambe al completo.\n");
        add(messageTextArea, new GridBagConstraints());

        toolBar.setRollover(true);

        guestBtn.setText("Gioca come ospite");
        guestBtn.setFocusable(false);
        guestBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        guestBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(guestBtn);

        loginBtn.setText("Accedi");
        loginBtn.setFocusable(false);
        loginBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        loginBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        toolBar.add(loginBtn);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        add(toolBar, gridBagConstraints);
    }

    private void initListeners() {
        this.loginBtn.addActionListener(e -> {
            observer.initLogin();
        });

        this.guestBtn.addActionListener(e -> {
            observer.quickPlay();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(),this);
        }
    }

}
