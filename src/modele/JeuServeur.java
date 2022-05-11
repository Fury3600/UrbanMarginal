package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Dictionnaire de joueurs index� sur leur objet de connexion
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param controle instance du contr�leur pour les �changes
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	/**
	 * Re�oit une information
	 */
	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String)info).split(STRINGSEPARE);
		String ordre = infos[0];
		String pseudo = infos[1];
		switch(ordre) {
		case PSEUDO :
			// arriv�e des informations d'un nouveau joueur
			controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			int numPerso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesJoueurs.values(), this.lesMurs);
			controle.evenementJeuServeur(AJOUTTCHAT, "*** " + pseudo + " s'est connect� ***");
			break;
		// Re�oit un message de "evenementArene"
		case TCHAT :
			// re�oit les information d'un message envoy�
			String message = this.lesJoueurs.get(connection).getPseudo() + " > " + infos[1];
			controle.evenementJeuServeur(AJOUTTCHAT, message);
			break;
		// Re�oit un ordre de "evenementArene"
		case ACTION :
			
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Ajoute le label affichant les joueurs
	 * @param jLabel objet � afficher
	 */
	public void ajoutJLabelJeuArene(JLabel jLabel) {
		this.controle.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}
	
	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois � l'envoi de la classe Jeu
	 * @param info info venant du controlleur
	 */
	public void envoi(Object info) {
		for(Connection connection : this.lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
	}

	/**
	 * Envoi du panel de jeu � tous les joueurs
	 */
	public void envoiJeuATous() {
		for(Connection connection : this.lesJoueurs.keySet()) {
			this.controle.evenementJeuServeur(MODIFPANELJEU, connection);
		}
	}
	
	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for(int k=0 ; k < NBMURS ; k++) {
			this.lesMurs.add(new Mur());
			this.controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
	}
	
}
