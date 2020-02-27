package application.monde;

import static jeu.Dessin.TAILLE;

import java.awt.Color;
import java.awt.Graphics;

import exception.CoordonneeException;
import moteurJeu.sprite.Sprites;

/**
 * Represente un mur
 */
public class Mur extends Case {

	/**
	 * Constructeur de Mur.java pour une position
	 * 
	 * @param abs Position en x
	 * @param ord Position en y
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public Mur(int abs, int ord) throws CoordonneeException {
		super(abs, ord);
		setBloquant(true);
		try {
			Sprites.chargerImage("Mur", System.getProperty("user.dir") + "/img/sol2.png", 0, 0, TAILLE, TAILLE);
		} catch (Error e) {

		}

	}

	@Override
	public void afficher(Graphics g) {
		super.afficher(g);
		try {
			Sprites.dessinerCentre(g, "Mur", (this.getX() * TAILLE) + TAILLE / 2, (this.getY() * TAILLE) + TAILLE / 2);
		} catch (Error e) {

		}
	}
}
