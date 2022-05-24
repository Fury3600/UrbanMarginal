package modele;



import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Global, Runnable{
	
	/**
	 * Instances des murs
	 */
	private Collection lesMurs;
	
	/**
	 * Instance de l'attaquant
	 */
	private Joueur attaquant;

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	
	/**
	 * Constructeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.jLabel = new JLabel();
		super.jLabel.setVisible(false);
		super.jLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(BOULE)));
		super.jLabel.setBounds(0, 0, LARGEURBOULE, HAUTEURBOULE);
	}
	
	/**
	 * Tire d'une boule
	 */
	public void tireBoule(Joueur attaquant, Collection lesMurs) {
		this.lesMurs = lesMurs;
		this.attaquant = attaquant;
		// Base sa position d'origine du tir, avec la direction
		this.posY = this.attaquant.getPosY() + (HAUTEURPERSO / 2);
		if (attaquant.getOrientation() == GAUCHE) {
			this.posX = this.attaquant.getPosX() - 1 - LARGEURBOULE; 
		}
		else {
			this.posX = this.attaquant.getPosX() + LARGEURPERSO + 1;
		}
		new Thread(this).run();
	}
	
	/**
	 * Méthode run
	 * @return 
	 */
	@Override
	public void run() {
		// Son du tir
		jeuServeur.envoi(0);
		// Affichage des images
		this.attaquant.affiche(MARCHE, 1);
		super.jLabel.setVisible(true);
		Joueur victime = null;
		int lePas;
		// L'orientation du tir (gauche/droite)
		if (attaquant.getOrientation() == GAUCHE) {
			lePas = -VITESSE;
		} else {
			lePas = VITESSE;
		}
		// Lance la boule vers une direction jusqu'à la collision
		do {
			Collection lesJoueurs = this.jeuServeur.getLesJoueurs();
			posX += lePas;
			super.jLabel.setBounds(posX, posY, LARGEURBOULE, HAUTEURBOULE);
			this.jeuServeur.envoiJeuATous();
			victime = (Joueur)this.toucheCollectionObjet(lesJoueurs);
		} while(this.posX >= 0 && this.posX <= LARGEURARENE - LARGEURBOULE && victime == null && this.toucheCollectionObjet(lesMurs) == null);
		// Cherche si une personne est touchée
		if (victime != null) {
			// Son des dégâts
			jeuServeur.envoi(1);
			// Gestion de la vie
			victime.perteVie();
			attaquant.gainVie();
			for (int k = 1; k <= 2; k++) {
				victime.affiche(TOUCHE, k);
				pause(80, 0);
			}
			// Cherche si la victime est morte
			if (victime.estMort()) {
				for (int k = 1; k <= 2; k++) {
					// Son de la mort
					jeuServeur.envoi(2);
					// Animation de la mort
					victime.affiche(MORT, k);
					pause(80, 0);
				}
			}
			else {
				victime.affiche(MARCHE, 1);
			}
		}
		// Effacement de l'image de la boule sur le serveur
		super.jLabel.setVisible(false);
		jeuServeur.envoiJeuATous();
	}
	
	/**
	 * Met en pause une action selon des millisecondes/nanosecondes données
	 * @param millisecondes
	 * @param nanosecondes
	 */
	public void pause(long millisecondes, int nanosecondes) {
		try {
			Thread.sleep(millisecondes, nanosecondes);
		}
		catch (InterruptedException e){
			System.out.println("Erreur: la pause n'a pas été supportée");
		}
	}
	
}
