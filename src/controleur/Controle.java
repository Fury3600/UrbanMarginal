package controleur;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connection.AsyncResponse;
import outils.connection.ClientSocket;
import outils.connection.Connection;
import outils.connection.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Contrôleur et point d'entrée de l'applicaton
 * 
 * @author emds
 *
 */
public class Controle implements AsyncResponse, Global {
	/*
	 * Jeu
	 */
	private Jeu leJeu;

	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;

	/**
	 * Méthode de démarrage
	 * 
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}

	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	/**
	 * Demande provenant de la vue EntreeJeu
	 * 
	 * @param info information à traiter
	 */
	public void evenementEntreeJeu(String info) {
		if (info.equals("serveur")) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		} else {
			new ClientSocket(this, info, PORT);
		}
	}
	
	/**
	 * 
	 */
	public void evenementChoixJoueur(String pseudo, int selectedPerso) {
		((JeuClient)this.leJeu).envoi(("pseudo" + SEPARATIONCHOIX + pseudo + SEPARATIONCHOIX + selectedPerso));
		this.frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case CONNECTION:
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				leJeu.connection(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				leJeu.connection(connection);
			}
			break;
		case RECEPTION:
			leJeu.reception(connection, info);
			break;
		case DECONNECTION:
			break;
		}
	}
	
	/**
	 * Méthode envoi
	 */
	public void envoi(Connection connection, Object objet) {
		connection.envoi(objet);
	}
}
