package application;

import java.awt.Graphics;

/**
 * Represente un element que l'on doit dessiner
 */
public interface ElementDessin {
	
	/**
	 * Permet d'afficher l'element
	 * @param g Graphisme
	 */
	void afficher(Graphics g);

}
