package modele;

import controleur.Controle;
import outils.connection.Connection;

/**
 * Informations et m�thodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {

	protected Controle controle;

	/**
	 * R�ception d'une connection (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connection(Connection connection) ;
	
	/**
	 * R�ception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connection, Object objet) ;
	
	/**
	 * D�connection de l'ordinateur distant
	 */
	public abstract void deconnection() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi(Connection connection, Object objet) {
		this.controle.envoi(connection, objet);
	}
	
}
