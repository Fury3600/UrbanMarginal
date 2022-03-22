package outils.connection;

/**
 * Permet la r�cup�ration asynchrone d'une r�ponse
 * @author emds
 *
 */
public interface AsyncResponse {
	/**
	 * M�thode � red�finir pour r�cup�rer la r�ponse de l'ordinateur distant
	 * @param connection contient l'objet qui permet de recontacter l'ordinateur distant (pour un envoi)
	 * @param ordre contient "connection" si nouvelle connection, "r�ception" si nvelle information re�ue, "d�connection" si d�connection
	 * @param info contient l'information re�ue (si ordre = "r�ception")
	 */
    void reception(Connection connection, String ordre, Object info);
}
