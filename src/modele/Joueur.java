package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet implements Global {

	/**
	 * pseudo saisi
	 */
	private String pseudo ;
	/**
	 * n° correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso ; 
	/**
	 * message qui s'affiche sous le personnage (contenant pseudo et vie)
	 */
	private JLabel message;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	
	/**
	 * Constructeur : récupératon de jeuServeur et initialisaton de certaines propriétés
	 * @param jeuServeur instance de JeuServeur pour lui envoyer des informations
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = MAXVIE;
		this.etape = 1;
		this.orientation = DROITE;
	}
	
	/**
	 * Getter du pseudo
	 * @return pseudo pseudo du joueur
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Getter de l'orientation
	 * @return orientation l'orientation du joueur
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Getter de la position X du joueur
	 * @return posX position X du joueur
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * Getter de la position Y du joueur
	 * @return posY position Y du joueur
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position, affichage, création de la boule)
	 * @param pseudo pseudo du joueur
	 * @param numPerso numéro du personnage
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur>lesJoueurs, Collection<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur "+pseudo+" - num perso "+numPerso+" créé");
		// création du label du personnage
		super.jLabel = new JLabel();
		// création du label du message sous le personnage
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		// création du label de la boule
		this.boule = new Boule(this.jeuServeur);
		// calcul de la première position du personnage
		this.premierePosition(lesJoueurs, lesMurs);
		// demande d'ajout du label du personnage et du message dans l'arène du serveur
		this.jeuServeur.ajoutJLabelJeuArene(jLabel);
		this.jeuServeur.ajoutJLabelJeuArene(message);
		this.jeuServeur.ajoutJLabelJeuArene(boule.getjLabel());
		// demande d'affichage du personnage
		this.affiche(MARCHE, this.etape);
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param lesJoueurs collection contenant tous les joueurs
	 * @param lesMurs collection contenant les murs
	 */
	private void premierePosition(Collection lesJoueurs, Collection lesMurs) {
		jLabel.setBounds(0, 0, LARGEURPERSO, HAUTEURPERSO);
		do {
			posX = (int) Math.round(Math.random() * (LARGEURARENE - LARGEURPERSO)) ;
			posY = (int) Math.round(Math.random() * (HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE)) ;
		}while(toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null);
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param etape Etape dans le mouvement du personnage
	 * @param etat etat du personnage : "marche", "touche", "mort"
	 */
	public void affiche(String etat, int etape) {
		// positionnement du personnage et affectation de la bonne image
		super.jLabel.setBounds(posX, posY, LARGEURPERSO, HAUTEURPERSO);
		String chemin = CHEMINPERSONNAGES+PERSO+this.numPerso+etat+etape+"d"+this.orientation+EXTFICHIERPERSO;
		URL resource = getClass().getClassLoader().getResource(chemin);
		super.jLabel.setIcon(new ImageIcon(resource));
		// positionnement et remplissage du message sous le perosnnage
		this.message.setBounds(posX-10, posY+HAUTEURPERSO, LARGEURPERSO+10, HAUTEURMESSAGE);
		this.message.setText(pseudo+" : "+vie);
		// demande d'envoi à tous des modifications d'affichage
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tir de boule...)
	 */
	public void action(Integer info, Collection lesJoueurs, Collection lesMurs) {
		if (!this.estMort()) {
			switch (info) {
			case 37:
			case 38:
			case 39:
			case 40:
				deplace(info, lesJoueurs, lesMurs);
				// Gère l'étape du perso lors d'un pas
				switch (etape) {
				case 1:
				case 2:
				case 3:
					this.etape++;
					break;
				case 4:
					this.etape = 1;
					break;
				}
				// Affiche le perso
				this.affiche(MARCHE, this.etape);
				break;
			case 32:
				if (!boule.getjLabel().isVisible()) {
					boule.tireBoule(this, lesMurs);
				}
				break;
			}
		}
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace(int direction, Collection lesJoueurs, Collection lesMurs) { 
		int oldPos;
		switch (direction) {
		// Gauche
		case 37:
			oldPos = posX;
			this.posX -= VITESSE;
			if (this.posX < 0 || toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null) {
				this.posX = oldPos;
			}
			this.orientation = 0;
			break;
		// Haut
		case 38:
			oldPos = posY;
			this.posY -= VITESSE;
			if (this.posY < 0 || toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null) {
				this.posY = oldPos;
			}
			break;
		// Droite
		case 39:
			oldPos = posX;
			this.posX += VITESSE;
			if (this.posX > LARGEURARENE - LARGEURPERSO || toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null) {
				this.posX = oldPos;
			}
			this.orientation = 1;
			break;
		// Bas
		case 40:
			oldPos = posY;
			this.posY += VITESSE;
			if (this.posY > HAUTEURARENE - HAUTEURPERSO - HAUTEURMESSAGE || toucheCollectionObjet(lesJoueurs) != null || toucheCollectionObjet(lesMurs) != null) {
				this.posY = oldPos;
			}
			break;
		}
	}
	
	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie += GAIN;
	}
	
	/**
	 * Perte de points de vie après avoir été touché 
	 */
	public void perteVie() {
		this.vie = Math.max(0, this.vie - PERTE);
	}
	
	/**
	 * vrai si la vie est à 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return this.vie <= 0;
	}
	
	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
		if (super.jLabel != null) {
			super.jLabel.setVisible(false);
			this.message.setVisible(false);
			this.boule.getjLabel().setVisible(false);
			jeuServeur.envoiJeuATous();
		}
	}
	
}
