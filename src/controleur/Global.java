/**
 * 
 */
package controleur;

/**
 * Global contient les constantes du programme
 * @author emds
 *
 */
public interface Global {
	
	/**
	 * Vitesse de d?placement en pixels
	 */
	int VITESSE = 10;
	/**
	 * N? du port d'?coute du serveur
	 */
	int PORT = 6666;
	/**
	 * Nombre de personnages diff?rents
	 */
	int NBPERSOS = 3;
	/**
	 * Caract?re de s?paration dans un chemin de fichiers
	 */
	String CHEMINSEPARATOR = "/";
	/**
	 * Chemin du dossier des images de fonds
	 */
	String CHEMINFONDS = "fonds"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image de la boule
	 */
	String CHEMINBOULES = "boules"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier de l'image du mur
	 */
	String CHEMINMURS = "murs"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des images des personnages
	 */
	String CHEMINPERSONNAGES = "personnages"+CHEMINSEPARATOR;
	/**
	 * Chemin du dossier des sons
	 */
	String CHEMINSONS = "sons"+CHEMINSEPARATOR;
	/**
	 * Chemins des sons de l'ar?ne
	 */
	String[] SONSARENE = {CHEMINSONS+"fight.wav", CHEMINSONS+"hurt.wav", CHEMINSONS+"death.wav"};
	/**
	 * Chemin de l'image de fond de la vue ChoixJoueur
	 */
	String FONDCHOIX = CHEMINFONDS+"fondchoix.jpg";
	/**
	 * Chemin de l'image de fond de la vue Arene
	 */
	String FONDARENE = CHEMINFONDS+"fondarene.jpg";
	/**
	 * Extension des fichiers des images des personnages
	 */
	String EXTFICHIERPERSO = ".gif";
	/**
	 * D?but du nom des images des personnages
	 */
	String PERSO = "perso";
	/**
	 * Chemin de l'image de la boule
	 */
	String BOULE = CHEMINBOULES+"boule.gif";
	/**
	 * Chemin de l'image du mur
	 */
	String MUR = CHEMINMURS+"mur.gif";
	/**
	 * ?tat marche du personnage
	 */
	String MARCHE = "marche";
	/**
	 * ?tat touch? du personnage
	 */
	String TOUCHE = "touche";
	/**
	 * ?tat mort du personnage
	 */
	String MORT = "mort";
	/**
	 * Caract?re de s?paration dans les chaines transf?r?es
	 */
	String STRINGSEPARE = "~";
	
	// Ordres envoy?s par la classe Controle
	
	/**
	 * Message "connexion" envoy? par la classe Connection
	 */
	String CONNEXION = "connexion";
	/**
	 * Message "r?ception" envoy? par la classe Connection
	 */
	String RECEPTION = "r?ception";
	/**
	 * Message "d?connexion" envoy? par la classe Connection
	 */
	String DECONNEXION = "d?connexion";
	
	/**
	 * Message "pseudo" envoy? pour la cr?ation d'un joueur
	 */
	String PSEUDO = "pseudo";
	/**
	 * vie de d?part pour tous les joueurs
	 */
	int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	int PERTE = 2 ; 
	/**
	 * nombre de murs dans l'ar?ne
	 */
	int NBMURS = 20;
	
	// Taille des diff?rents objets
	
	/**
	 * hauteur de la zone de jeu de l'ar?ne
	 */
	int HAUTEURARENE = 600;
	/**
	 * largeur de la zone de heu de l'ar?ne
	 */
	int LARGEURARENE = 800;
	/**
	 * hauteur d'un mur
	 */
	int HAUTEURMUR = 35;
	/**
	 * largeur d'un mur
	 */
	int LARGEURMUR = 34;
	/**
	 * hauteur du personnage
	 */
	int HAUTEURPERSO = 44;
	/**
	 * largeur du personnage
	 */
	int LARGEURPERSO = 39;
	/**
	 * Largeur de la boule
	 */
	int LARGEURBOULE = 17;
	/**
	 * Hauteur de la boule
	 */
	int HAUTEURBOULE = 17;
	/**
	 * hauteur du message
	 */
	int HAUTEURMESSAGE = 8;
	
	
	/**
	 * orientation du personnage vers la gauche
	 */
	int GAUCHE = 0;
	/**
	 * orientation du personnage vers la droite
	 */
	int DROITE = 1;
	
	// Ordres de la classe Controle
	
	/**
	 * Message "serveur" pour la cr?ation d'un serveur
	 */
	String SERVEUR = "serveur";
	/**
	 * Message "Client" pour la cr?ation d'un client
	 */
	String CLIENT = "coteClient";
	/**
	 * ordre pour ajouter un mur dans l'ar?ne du serveur
	 */
	String AJOUTMUR = "ajout mur";
	/**
	 * ordre pour ajouter le panel des murs dans l'ar?ne du client
	 */
	String AJOUTPANELMURS = "ajout panel murs";
	/**
	 * ordre pour ajouter un jLabel dans l'ar?ne du serveur (joueur, message, boule)
	 */
	String AJOUTJLABELJEU = "ajout jLabel jeu";
	/**
	 * ordre pour modifier le panel du jeu dans l'ae?ne du client
	 */
	String MODIFPANELJEU = "modif panel jeu";
	/**
	 * ordre pour jouer un son
	 */
	String JOUESON = "joue son";
	/**
	 * Message "tchat" envoy? par "evenementArene" de la classe Controle
	 */
	String TCHAT = "tchat";
	/**
	 * Message "action" envoy? par "evenementArene" de la classe Controle
	 */
	String ACTION = "action";
	/**
	 * Message "modifTchat" envoy? par la classe Controle
	 */
	String MODIFTCHAT = "modifTchat";
	/**
	 * Message "ajoutTchat" envoy? par la classe Controle
	 */
	String AJOUTTCHAT = "ajoutTchat";
}
