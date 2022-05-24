package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import controleur.Global;
import outils.son.Son;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * frame de l'arène du jeu
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {
	
	/**
	 * Sons de l'arène
	 */
	private Son[] sonsArene = new Son[SONSARENE.length];
	/**
	 * Controle
	 */
	private Controle controle;
	/**
	 * Panel général
	 */
	private JPanel contentPane;
	
	
	// Variable et méthodes de Tchat
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtTchat ;
	/**
	 * Getter de txtChat
	 * @return  the txtChat
	 */
	public String getTxtTchat() {
		return this.txtTchat.getText();
	}
	/**
	 * Setter de txtChat
	 * @param txtChat the txtChat to set
	 */
	public void setTxtTchat(String txtChat) {
		this.txtTchat.setText(txtChat);
		this.txtTchat.setCaretPosition(this.txtTchat.getDocument().getLength());
	}
	/**
	 * Ajoute à txtChat un message
	 * @param message à envoyer
	 */
	public void ajoutTchat(String message) {
		this.txtTchat.setText(this.txtTchat.getText() + message + "\r\n");
		this.txtTchat.setCaretPosition(this.txtTchat.getDocument().getLength());
	}
	
	
	// Variables et méthodes du panel des murs
	/**
	 * Panel contenant les murs
	 */
	private JPanel jpnMurs;
	/**
	 * @return the jpnMurs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}
	/**
	 * @param jpnMurs the jpnMurs to set
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}
	/**
	 * Ajoute un mur dans le panel des murs
	 * @param unMur le mur à ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel)unMur);
		jpnMurs.repaint();
	}
	
	
	// Variables et méthodes du panel du jeu
	/**
	 * Panel contenant les joueurs et les boules
	 */
	private JPanel jpnJeu;
	/**
	 * @return the jpnJeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}
	/**
	 * @param jpnJeu the jpnJeu to set
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
		this.contentPane.requestFocus();
	}
	/**
	 * Ajout d'un joueur, son message ou sa boule, dans le panel de jeu
	 * @param unJLabel le label à ajouter
	 */
	public void ajoutJLabelJeu(JLabel unJLabel) {
		this.jpnJeu.add(unJLabel);
		this.jpnJeu.repaint();
	}
	
	/**
	 * Valide la saisie d'un message dans le chat, via "Entrée"
	 * @param e la touche appuyée
	 */
	public void txtSaisie_KeyPressed(KeyEvent e) {
		// si validation
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			// si la zone de saisie n'est pas vide
			if(!this.txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
				this.contentPane.requestFocus();
			}
		}
	}

	/**
	 * Récupère la touche directionnelle
	 * @param e la touche appuyée
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int numTouche = -1;
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_SPACE:
			numTouche = e.getKeyCode();
			break;
		}
		if (numTouche != -1){
			this.controle.evenementArene((Integer)numTouche);
		}

	}
	
	public void joueSon(int son) {
		sonsArene[son].play();
	}
	
	/**
	 * Create the frame.
	 * @param controle information venant du controlleur
	 * @param typeJeu type de jeu client/serveur
	 */
	public Arene(Controle controle, String typeJeu) {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(LARGEURARENE, HAUTEURARENE + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);		
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);		
		contentPane.add(jpnMurs);
		
		/// Vérifie si l'Arène est côté client
		if (typeJeu.equals(CLIENT)) {
			txtSaisie = new JTextField();
			txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// si entrée appuyé
					txtSaisie_KeyPressed(e);
				}
			});
			txtSaisie.setBounds(0, 600, 800, 25);
			contentPane.add(txtSaisie);
			txtSaisie.setColumns(10);
			
			//Touches directionnelles
			contentPane.addKeyListener(new KeyAdapter (){
				@Override
				public void keyPressed(KeyEvent e) {
					contentPane_KeyPressed(e);
				}
			});
			for (int k = 0; k < SONSARENE.length; k++) {
				sonsArene[k] = new Son(getClass().getClassLoader().getResource(SONSARENE[k]));
			}
		}
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtTchat = new JTextArea();
		txtTchat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				contentPane_KeyPressed(e);
			}
		});
		txtTchat.setEditable(false);
		jspChat.setViewportView(txtTchat);
		
		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(FONDARENE);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		this.controle = controle;
	}

}
