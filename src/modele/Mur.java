package modele;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Global;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet implements Global {
	
	/**
	 * Constructeur
	 */
	public Mur() {	
		/**
		 * Position du mur
		 */
		posX = (int) Math.round(Math.random() * (XARENE - XMUR));
		posY = (int) Math.round(Math.random() * (XARENE - XMUR));
		
		/**
		 * Instance du Label jLabel
		 */
		jLabel = new JLabel();		
		jLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(CHEMINMURS)));
		jLabel.setBounds(posX, posY, XMUR, YMUR);
	}
	
}
