package application.monde;

import static jeu.Dessin.TAILLE;

import java.awt.Color;
import java.awt.Graphics;

import application.ElementDessin;
import exception.CoordonneeException;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represente une case
 */
public class Case implements ElementDessin {

	private int x, y;
	private boolean bloquant;
	private Transition trans;

	/**
	 * Constructeur de Case.java pour une position
	 * 
	 * @param abs Position en x
	 * @param ord Position en y
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public Case(int abs, int ord) throws CoordonneeException {
		if (abs >= 0) {
			x = abs;
		} else {
			throw new CoordonneeException();
		}
		if (ord >= 0) {
			y = ord;
		} else {
			throw new CoordonneeException();
		}

		try {
			Sprites.chargerImage("Sol", System.getProperty("user.dir") + "/img/sol1.jpg", 167, 101, TAILLE, TAILLE);
		} catch (Error e) {

		}
	}

	@Override
	public void afficher(Graphics g) {
		if (JeuZeldiablo.debugMod) {
			g.setColor(Color.white);
			g.fillRect(this.x * TAILLE, this.y * TAILLE, TAILLE, TAILLE);
		}
		try {
			Sprites.dessiner(g, "Sol", x * TAILLE, y * TAILLE);
		} catch (Error e) {

		}
	}

	/**
	 * Permet d'ajouter une transition a la case
	 * 
	 * @param t Nouvelle transition
	 */
	public void ajouterTransition(Transition t) {
		trans = t;
	}

	/**
	 * Permet de savoir si la case est bloquante
	 * 
	 * @return True si la case est bloquante
	 */
	public boolean estBloquant() {
		return bloquant;
	}

	/**
	 * Retourne la transition de la case
	 * 
	 * @return Transition de la case
	 */
	public Transition getTransition() {
		return trans;
	}

	/**
	 * Retourne la position en x de la case
	 * 
	 * @return Position en x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne la position en y de la case
	 * 
	 * @return Position en y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Permet changer l'etat bloquant de la case
	 * 
	 * @param b Nouvel etat
	 */
	public void setBloquant(boolean b) {
		bloquant = b;
	}
}
