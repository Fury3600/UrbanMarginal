package controleur;

import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Mur;
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

	/*
	 * Jeu
	 */
	private JeuServeur jeuServ;

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
			((JeuServeur) this.leJeu).constructionMurs();

		} else {
			new ClientSocket(this, info, PORT);
		}
	}

	/**
	 * Evenements lors du choix du joueur
	 */
	public void evenementChoixJoueur(String pseudo, int selectedPerso) {
		((JeuClient) this.leJeu).envoi(("pseudo" + SEPARATIONCHOIX + pseudo + SEPARATIONCHOIX + selectedPerso));
		this.frmChoixJoueur.dispose();
		frmArene.setVisible(true);
	}

	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
		case "ajout mur":
			frmArene.ajoutMur(info);
			break;
		case "ajout panel murs":
			leJeu.envoi((Connection) info, frmArene.getJpnMurs());
			break;
		}
	}

	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
		case "ajout panel murs":
			frmArene.setJpnMurs((JPanel)info);
			break;
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case CONNECTION:
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connection(connection);
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
