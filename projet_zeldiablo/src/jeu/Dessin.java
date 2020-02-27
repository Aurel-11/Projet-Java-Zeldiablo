package jeu;

import static jeu.Dessin.TAILLE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import application.ennemi.Monstre;
import moteurJeu.moteur.DessinAbstract;
import moteurJeu.sprite.Sprites;

/**
 * Gere l'affichage du jeu
 */
public class Dessin implements DessinAbstract {

	private JeuZeldiablo jeuEnCours;

	/**
	 * Taille cubique de tous les elements du jeu
	 */
	public static final int TAILLE = 32;

	/**
	 * Constructeur de Dessin.java pour un jeu en cours
	 * 
	 * @param jz Jeu en cours
	 */
	public Dessin(JeuZeldiablo jz) {
		jeuEnCours = jz;
	}

	@Override
	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();

		for (int i = 0; i < jeuEnCours.getPerso().getCarte().getCases().length; i++) {
			for (int j = 0; j < jeuEnCours.getPerso().getCarte().getCases()[i].length; j++) {
				jeuEnCours.getPerso().getCarte().getCases()[i][j].afficher(g);
			}
		}

		for (Monstre tmp : this.jeuEnCours.getPerso().getCarte().getMonstres()) {
			tmp.afficher(g);
		}

		jeuEnCours.getPerso().afficher(g);

		if (this.jeuEnCours.getPerso().etreMort()) {
			Sprites.chargerImage("gameover", System.getProperty("user.dir") + "/img/gameover.png");
			Sprites.dessinerCentre(g, "gameover", 960 / 2, 640 / 2);
		}

		g.setColor(Color.green);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		g.drawString(new String(Integer.toString(this.jeuEnCours.getPerso().getPv())), 35, 53);
		g.fillRect(53, 43, this.jeuEnCours.getPerso().getPv() * 2, 10);

		if (JeuZeldiablo.bossKilled) {
			try {
				Sprites.chargerImage("Succes", System.getProperty("user.dir") + "/img/win.png");

			} catch (Error e) {

			}
			try {
				Sprites.dessinerCentre(g, "Succes", 960 / 2, 640 / 2);
			} catch (Error e) {

			}
		}

		g.dispose();
	}
}
