package modele;

import controleur.Controle;
import outils.connection.Connection;

/**
 * Informations et méthodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {

	protected Controle controle;

	/**
	 * Réception d'une connection (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connection(Connection connection) ;
	
	/**
	 * Réception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object objet) ;
	
	/**
	 * Déconnection de l'ordinateur distant
	 */
	public abstract void deconnection() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi(Connection connection, Object objet) {
		this.controle.envoi(connection, objet);
	}
	
}
