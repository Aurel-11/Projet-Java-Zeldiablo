package application.ennemi;

import static jeu.Dessin.TAILLE;

import java.awt.Color;
import java.awt.Graphics;

import application.Attaquant;
import application.cerveau.Cerveau;
import application.cerveau.CerveauImmobile;
import application.monde.Carte;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represente une tourelle
 */
public class Tourelle extends Monstre {
	private final int ATK_DEFAULT = 2;
	private final int VATK_DEFAULT = 20;
	private final int VDEP_DEFAULT = 0;
	private final int PV_DEFAULT = 4;
	private final Cerveau CERVEAU_DEFAULT = new CerveauImmobile();

	/**
	 * Constructeur de Tourelle.java pour une position et une carte
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Tourelle(int x, int y, Carte carte) {
		super(x, y, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Tourelle.java pour une position, une carte, une vitesse et
	 * des stats
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param atk                Valeur d'attaque
	 * @param vAtk               Vitesse d'attaque
	 * @param pv                 Points de vie
	 */
	public Tourelle(int x, int y, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, carte, vitesseDeplacement, atk, vAtk, pv);
		this.setCerveau(CERVEAU_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Tourelle.java pour une position, une carte et une IA
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param c     IA
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Tourelle(int x, int y, Cerveau c, Carte carte) {
		super(x, y, c, carte);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setVitesseDeplacement(VDEP_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Tourelle.java pour une position, une IA, une carte et une
	 * vitesse
	 * 
	 * @param x       Position en x
	 * @param y       Position en y
	 * @param c       IA
	 * @param carte   Carte sur laquelle est le monstre
	 * @param vitesse Vitesse de deplacement
	 */
	public Tourelle(int x, int y, Cerveau c, Carte carte, int vitesse) {
		super(x, y, c, carte, vitesse);
		this.setAtk(ATK_DEFAULT);
		this.setvAtk(VATK_DEFAULT);
		this.setPv(PV_DEFAULT);
		this.chargerSprite();
	}

	/**
	 * Constructeur de Tourelle.java pour une position, une IA, une carte, une
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
	public Tourelle(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		super(x, y, cerveau, carte, vitesseDeplacement, atk, vAtk, pv);
		this.chargerSprite();
	}

	@Override
	public void afficher(Graphics g) {
		super.afficher(g);
		if (JeuZeldiablo.debugMod) {
			g.setColor(new Color(45, 36, 30));
			g.fillRect(this.getX(), this.getY(), TAILLE, TAILLE);
		}
		try {
			Sprites.dessinerCentre(g, "Tourelle", this.getX() + TAILLE / 2, this.getY() + TAILLE / 2);
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
		}
		return 0;
	}

	private void chargerSprite() {
		try {
			Sprites.chargerImage("Tourelle", System.getProperty("user.dir") + "/img/Archer.png", 0, 0,
					TAILLE, TAILLE);
		} catch (Error e) {

		}
	}

}
