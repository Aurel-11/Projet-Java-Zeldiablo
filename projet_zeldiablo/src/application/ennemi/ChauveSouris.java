package application.ennemi;

import static jeu.Dessin.TAILLE;
import static jeu.JeuZeldiablo.VITESSE_DEFAULT;

import java.awt.Color;
import java.awt.Graphics;

import application.Attaquant;
import application.cerveau.Cerveau;
import application.cerveau.CerveauAlea;
import application.monde.Carte;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represente une chauve-souris
 */
public class ChauveSouris extends Monstre {
	private final int ATK_DEFAULT = 1;
	private final int VATK_DEFAULT = 0;
	private final int VDEP_DEFAULT = VITESSE_DEFAULT;
	private final int PV_DEFAULT = 3;
	private final Cerveau CERVEAU_DEFAULT = new CerveauAlea();

	private boolean peur = false;

	/**
	 * Constructeur de ChauveSouris.java pour une position et une carte
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le monstre
	 */
	public ChauveSouris(int x, int y, Carte carte) {
		super(x, y, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de ChauveSouris.java pour une position, une carte, une vitesse
	 * et des stats
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param atk                Valeur d'attaque
	 * @param vAtk               Vitesse d'attaque
	 * @param pv                 Points de vie
	 */
	public ChauveSouris(int x, int y, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, carte, vitesseDeplacement, atk, vAtk, pv);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de ChauveSouris.java pour une position, une carte et une IA
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param c     IA
	 * @param carte Carte sur laquelle est le monstre
	 */
	public ChauveSouris(int x, int y, Cerveau c, Carte carte) {
		super(x, y, c, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de ChauveSouris.java pour une position, une IA, une carte et une
	 * vitesse
	 * 
	 * @param x       Position en x
	 * @param y       Position en y
	 * @param c       IA
	 * @param carte   Carte sur laquelle est le monstre
	 * @param vitesse Vitesse de deplacement
	 */
	public ChauveSouris(int x, int y, Cerveau c, Carte carte, int vitesse) {
		super(x, y, c, carte, vitesse);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de ChauveSouris.java pour une position, une IA, une carte, une
	 * vitesse et des stats
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
	public ChauveSouris(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, cerveau, carte, vitesseDeplacement, atk, vAtk, pv);
	}

	@Override
	public void afficher(Graphics g) {
		super.afficher(g);
		if (JeuZeldiablo.debugMod) {
			g.setColor(new Color(173, 79, 9));
			g.fillRect(this.getX(), this.getY(), TAILLE, TAILLE);
		}
		try {
			Sprites.dessinerCentre(g, "ChauveSouris", this.getX() + TAILLE / 2, this.getY() + TAILLE / 2);
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
			if (!peur) {
				this.changerVitesse(this.getVitesseDeplacement() / 3);
				this.peur = true;
			}
		}
		return 0;
	}

	private void chargerSprite() {
		try {
			Sprites.chargerImage("ChauveSouris", System.getProperty("user.dir") + "/img/navi.png", 0,
					0, TAILLE, TAILLE);
		} catch (Error e) {

		}
	}

}
