package outils.connection;

/**
 * Permet la récupération asynchrone d'une réponse
 * @author emds
 *
 */
public interface AsyncResponse {
	/**
	 * Méthode à redéfinir pour récupérer la réponse de l'ordinateur distant
	 * @param connection contient l'objet qui permet de recontacter l'ordinateur distant (pour un envoi)
	 * @param ordre contient "connection" si nouvelle connection, "réception" si nvelle information reçue, "déconnection" si déconnection
	 * @param info contient l'information reçue (si ordre = "réception")
	 */
    void reception(Connection connection, String ordre, Object info);
}
