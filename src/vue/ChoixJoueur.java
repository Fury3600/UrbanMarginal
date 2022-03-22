package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;

import controleur.Controle;
import controleur.Global;

/**
 * Frame du choix du joueur
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame implements Global{
	
	/**
	 * Label d'affichage du personnage
	 */
	private JLabel lblPersonnage;
	/**
	 * Personnages à afficher
	 */
	public int selectedPerso;
	/**
	 * Instance du Contrôleur pour communiquer avec lui
	 */
	private Controle controle;
	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	
	/**
	 * Méthode affichant le perso choisi
	 */
	public void affichePerso(int selectedPerso) {
		String cheminPerso = CHEMINPERSO + selectedPerso + POSECHOIX + DIRECTION + FORMATIMG;
		URL ressourcePerso = getClass().getClassLoader().getResource(cheminPerso);
		this.lblPersonnage.setIcon(new ImageIcon(ressourcePerso));
	}
	
	/**
	 * Méthode affichant le curseur normal
	 */
	public void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * Méthode affichant le curseur doigt
	 */
	public void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/**
	 * Clic sur la flèche "précédent" pour afficher le personnage précédent
	 */
	private void lblPrecedent_clic() {
		System.out.println("Clic sur precedent");
		JLabel imagePerso = this.lblPersonnage;
		selectedPerso--;
		if (selectedPerso < 1)
		{
			selectedPerso = PERSOMAX;
		}
		affichePerso(selectedPerso);
	}
	
	/**
	 * Clic sur la flèche "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("Clic sur suivant");
		selectedPerso++;
		if (selectedPerso > PERSOMAX)
		{
			selectedPerso = 1;
		}
		affichePerso(selectedPerso);
	}
	
	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		if (!txtPseudo.getText().equals("")) {
			this.controle.evenementChoixJoueur(txtPseudo.getText(), selectedPerso);
		} else {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			this.txtPseudo.grabFocus();
		}
	}


	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		 
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		selectedPerso = 1;
		lblPersonnage = new JLabel("");
		lblPersonnage.setAlignmentY(0.0f);
		lblPersonnage.setBounds(142, 114, 120, 120);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);
		affichePerso(selectedPerso);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		URL ressource = getClass().getClassLoader().getResource(CHEMINFONDCHOIX);
		lblFond.setIcon(new ImageIcon(ressource));		
		contentPane.add(lblFond);
		
		// positionnement sur la zone de saisie
		txtPseudo.requestFocus();
		
		// Instance de controle
		this.controle = controle;
	}
}
