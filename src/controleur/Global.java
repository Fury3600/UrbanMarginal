package controleur;

import java.net.URL;
import vue.ChoixJoueur;

public interface Global {
	/**
	 * Port du serveur
	 */
	static int PORT = 6666;
	
	/**
	 * Etats du client
	 */
	String CONNECTION = "connection";
	String RECEPTION = "r�ception";
	String DECONNECTION = "d�connexion";
	
	/**
	 * Chemin et format des sons
	 */
	String CHEMINSONS = "sons/";
	String FORMATSON = ".wav";
	
	/**
	 * Format des images
	 */
	String FORMATIMG = ".gif";
	/**
	 * Chemin des fonds pour le meneu de s�lection et de l'ar�ne
	 */
	String CHEMINFONDCHOIX = "fonds/fondchoix.jpg";
	String CHEMINFONDARENE = "fonds/fondarene.jpg";
	/**
	 * Chemin pour les images des boules
	 */
	String CHEMINBOULE = "boules/boule" + FORMATIMG;
	/**
	 * Chemin pour l'image des murs
	 */
	String CHEMINMURS = "murs/mur" + FORMATIMG;
	/**
	 * Chemin pour l'affichage des personnages, ainsi que leur pose et leur direction
	 */
	String CHEMINPERSO = "personnages/perso";
	String POSECHOIX = "marche1";
	String DIRECTION = "d1";
	
	/**
	 * Nombre de persos maximum � choisir
	 */
	public final int PERSOMAX = 3;
	
	/**
	 * Caract�re s�parant les informations pour evenementChoixJoueur de Controle
	 */
	String SEPARATIONCHOIX = "~";
	
	/**
	 * Quantit� de vie, de gain et de perte de vie des joueurs
	 */
	static final int MAXVIE = 10 ;
	static final int GAIN = 1 ;
	static final int PERTE = 2 ;
}
