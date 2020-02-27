package moteurJeu.sprite;

import java.awt.Graphics;

/**
 * un sprite correspond a une image positionnee a un endroit de la scene
 * 
 * @author vthomas
 *
 */
public class Sprite {

	/**
	 * le nom de l'image stockee dans Images
	 */
	String nomImage;

	/**
	 * la position du sprite
	 */
	int x, y;

	/**
	 * creation du sprite
	 * 
	 * @param nom
	 *            nom de l'image
	 */
	public Sprite(String nom) {
		this.nomImage = nom;
		this.x = 0;
		this.y = 0;
	}

	/**
	 * permet de dessiner le sprite avec un graphics
	 * 
	 * @param g
	 *            graphics pour dessiner
	 */
	public void dessiner(Graphics g) {
		moteurJeu.sprite.Sprites.dessiner(g, this.nomImage, this.x, this.y);
	}

}
