package modele;

import javax.swing.JPanel;

import controleur.Controle;
import outils.connection.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu {
	
	/**
	 * Instance de Connection
	 */
	public Connection connection;
	
	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object objet) {
		if (objet instanceof JPanel) {
			controle.evenementJeuClient("ajout panel murs", objet);
		}
	}
	
	@Override
	public void deconnection() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois à l'envoi dans la classe Jeu
	 */
	public void envoi(String infoJoueur) {
		super.envoi(this.connection, infoJoueur);
	}

}
