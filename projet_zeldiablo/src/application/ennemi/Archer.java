/**
 * 
 */
package application.ennemi;

import static jeu.Dessin.TAILLE;
import static jeu.JeuZeldiablo.VITESSE_DEFAULT;

import java.awt.Color;
import java.awt.Graphics;

import application.Attaquant;
import application.cerveau.Cerveau;
import application.cerveau.CerveauDistance;
import application.monde.Carte;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represente un archer
 */
public class Archer extends Monstre {
	private final int ATK_DEFAULT = 2;
	private final int VATK_DEFAULT = 15;
	private final int VDEP_DEFAULT = (int) Math.round(VITESSE_DEFAULT * 0.4);
	private final int PV_DEFAULT = 3;
	private final int DISTANCE_ATK_DEFAULT = 250;
	private final Cerveau CERVEAU_DEFAULT = new CerveauDistance(DISTANCE_ATK_DEFAULT);

	/**
	 * Constructeur de Archer.java pour une position et une carte
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Archer(int x, int y, Carte carte) {
		super(x, y, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Archer.java pour une position, une carte, une vitesse et des
	 * stats
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param atk                Valeur d'attaque
	 * @param vAtk               Vitesse d'attaque
	 * @param pv                 Points de vie
	 */
	public Archer(int x, int y, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, carte, vitesseDeplacement, atk, vAtk, pv);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Archer.java pour une position, une carte et une IA
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param c     IA
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Archer(int x, int y, Cerveau c, Carte carte) {
		super(x, y, c, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Archer.java pour une position, une IA, une carte et une
	 * vitesse
	 * 
	 * @param x       Position en x
	 * @param y       Position en y
	 * @param c       IA
	 * @param carte   Carte sur laquelle est le monstre
	 * @param vitesse Vitesse de deplacement
	 */
	public Archer(int x, int y, Cerveau c, Carte carte, int vitesse) {
		super(x, y, c, carte, vitesse);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Archer.java pour une position, une IA, une carte, une vitesse
	 * et des points de vie
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param cerveau            IA
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param pv                 Points de vie
	 */
	public Archer(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int pv) {
		super(x, y, cerveau, carte, vitesseDeplacement, pv);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Archer.java pour une position, une IA, une carte, une vitesse
	 * et des stats
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param cerveau            IA
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param atk                Valeur d'attaque
	 * @param vAtk               Vitesse d'attaque
	 * @param pv                 Points de vie
	 */
	public Archer(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, cerveau, carte, vitesseDeplacement, atk, vAtk, pv);
		this.chargerSprite();
	}

	@Override
	public void afficher(Graphics g) {
		super.afficher(g);
		if (JeuZeldiablo.debugMod) {
			g.setColor(new Color(199, 44, 72));
			g.fillRect(this.getX(), this.getY(), TAILLE, TAILLE);
		}
		try {
			Sprites.dessinerCentre(g, "Archer", this.getX() + TAILLE / 2, this.getY() + TAILLE / 2);
		} catch (Error e) {

		}

	}

	@Override
	public void attaquer(Attaquant cible) {
		if (this.getNbrFrameAvantNouvelleAtk() == 0) {
			this.retirerVie(cible.subirDegats(this.getAtk()));
			this.setNbrFrameAvantNouvelleAtk(this.getvAtk());
		}

	}

	@Override
	public int subirDegats(int vAttaque) {
		if (vAttaque > 0) {
			this.retirerVie(vAttaque);

			if (this.getVitesseDeplacement() < JeuZeldiablo.VITESSE_DEFAULT - 1) {
				this.changerVitesse(1);
			}
		}

		return 0;
	}

	private void chargerSprite() {
		try {
			Sprites.chargerImage("Archer", System.getProperty("user.dir") + "/img/Archer.png", 0, 0,
					TAILLE, TAILLE);
		} catch (Error e) {

		}
	}

}
