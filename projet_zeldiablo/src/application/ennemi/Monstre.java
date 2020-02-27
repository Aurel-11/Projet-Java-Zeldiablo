package application.ennemi;

import static jeu.Dessin.TAILLE;
import static jeu.JeuZeldiablo.VITESSE_DEFAULT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import application.Attaquant;
import application.ElementDessin;
import application.cerveau.Cerveau;
import application.joueur.Joueur;
import application.monde.Carte;
import jeu.JeuZeldiablo;

/**
 * Modelise un monstre
 */
public abstract class Monstre implements ElementDessin, Attaquant {

	private int x;
	private int y;
	private Cerveau cerveau;
	private Joueur perso;
	private Carte carte;
	private int vitesseDeplacement = VITESSE_DEFAULT;
	private int atk = 1;
	private int vAtk = 1;
	private int pv = 1;
	private int nbrFrameAvantNouvelleAtk = 0;

	/**
	 * Constructeur de Monstre.java pour une position et une carte
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Monstre(int x, int y, Carte carte) {
		this.x = x;
		this.y = y;
		this.carte = carte;
	}

	/**
	 * Constructeur de Monstre.java pour une position, une carte, une vitesse et des
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
	public Monstre(int x, int y, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		this.x = x;
		this.y = y;
		this.carte = carte;
		this.vitesseDeplacement = vitesseDeplacement;
		this.atk = atk;
		this.vAtk = vAtk;
		this.pv = pv;
	}

	/**
	 * Constructeur de Monstre.java pour une position, une carte et une IA
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param c     IA
	 * @param carte Carte sur laquelle est le monstre
	 */
	public Monstre(int x, int y, Cerveau c, Carte carte) {
		this.x = x;
		this.y = y;
		this.cerveau = c;
		this.carte = carte;
	}

	/**
	 * Constructeur de Monstre.java pour une position, une IA, une carte et une
	 * vitesse
	 * 
	 * @param x       Position en x
	 * @param y       Position en y
	 * @param c       IA
	 * @param carte   Carte sur laquelle est le monstre
	 * @param vitesse Vitesse de deplacement
	 */
	public Monstre(int x, int y, Cerveau c, Carte carte, int vitesse) {
		this.x = x;
		this.y = y;
		this.cerveau = c;
		this.carte = carte;
		this.vitesseDeplacement = vitesse;
	}

	/**
	 * Constructeur de Monstre.java pour une position, une IA, une carte, une
	 * vitesse et des points de vie
	 * 
	 * @param x                  Position en x
	 * @param y                  Position en y
	 * @param cerveau            IA
	 * @param carte              Carte sur laquelle est le monstre
	 * @param vitesseDeplacement Vitesse de deplacement
	 * @param pv                 Points de vie
	 */
	public Monstre(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int pv) {
		this.x = x;
		this.y = y;
		this.cerveau = cerveau;
		this.carte = carte;
		this.vitesseDeplacement = vitesseDeplacement;
		this.pv = pv;
	}

	/**
	 * Constructeur de Monstre.java pour une position, une IA, une carte, une
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
	public Monstre(int x, int y, Cerveau cerveau, Carte carte, int vitesseDeplacement, int atk, int vAtk, int pv) {
		this.x = x;
		this.y = y;
		this.cerveau = cerveau;
		this.carte = carte;
		this.vitesseDeplacement = vitesseDeplacement;
		this.atk = atk;
		this.vAtk = vAtk;
		this.pv = pv;
	}

	@Override
	public void afficher(Graphics g) {
		if (JeuZeldiablo.debugMod) {
			g.setColor(Color.RED);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
			String pv = new String(this.pv + " pv");
			g.drawString(pv, x, y + TAILLE + 10);
		}
	}

	@Override
	public void ajouterVie(int heal) {
		this.pv += heal;
	}

	@Override
	public abstract void attaquer(Attaquant cible);

	/**
	 * Permet de changer la vitesse d'un monstre
	 * 
	 * @param dVitesse Ajout (ou retrait si la valeur est negative) de vitesse
	 */
	public void changerVitesse(int dVitesse) {
		this.vitesseDeplacement += dVitesse;
	}

	/**
	 * Permet de continuer une action precedente
	 * 
	 * @param d Action a repeter
	 */
	public void continuerDeplacement(String d) {
		int dx, dy;

		if (d != null) {
			switch (d) {
			case "N":
				dx = 0;
				dy = -this.vitesseDeplacement;
				break;
			case "NE":
				dx = this.vitesseDeplacement;
				dy = -this.vitesseDeplacement;
				break;
			case "E":
				dx = this.vitesseDeplacement;
				dy = 0;
				break;
			case "SE":
				dx = this.vitesseDeplacement;
				dy = this.vitesseDeplacement;
				break;
			case "S":
				dx = 0;
				dy = this.vitesseDeplacement;
				break;
			case "SO":
				dx = -this.vitesseDeplacement;
				dy = this.vitesseDeplacement;
				break;
			case "O":
				dx = -this.vitesseDeplacement;
				dy = 0;
				break;
			case "NO":
				dx = -this.vitesseDeplacement;
				dy = -this.vitesseDeplacement;
				break;
			default:
				dx = 0;
				dy = 0;
			}

			this.x += dx;
			this.y += dy;

			while (carte.getCases()[this.x / TAILLE][this.y / TAILLE].estBloquant()
					|| carte.getCases()[(this.x + TAILLE) / TAILLE][(this.y + TAILLE) / TAILLE].estBloquant()
					|| carte.getCases()[(this.x + TAILLE) / TAILLE][this.y / TAILLE].estBloquant()
					|| carte.getCases()[this.x / TAILLE][(this.y + TAILLE) / TAILLE].estBloquant()
					|| carte.getCases()[this.x / TAILLE][this.y / TAILLE].getTransition() != null
					|| carte.getCases()[(this.x + TAILLE) / TAILLE][(this.y + TAILLE) / TAILLE].getTransition() != null
					|| carte.getCases()[(this.x + TAILLE) / TAILLE][this.y / TAILLE].getTransition() != null
					|| carte.getCases()[this.x / TAILLE][(this.y + TAILLE) / TAILLE].getTransition() != null) {

				if (dx != 0)
					x -= dx / Math.abs(dx);
				if (dy != 0)
					y -= dy / Math.abs(dy);
			}
		}
	}

	/**
	 * Permet de decrementer le nombre de frame avant la prochaine attaque
	 */
	public void decrementerFrameAvantNouvelleAtk() {
		this.nbrFrameAvantNouvelleAtk--;
	}

	@Override
	public boolean etreMort() {
		return this.pv <= 0;
	}

	/**
	 * Retourne atk
	 * 
	 * @return atk
	 */
	public int getAtk() {
		return atk;
	}

	/**
	 * Retourne carte
	 * 
	 * @return carte
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Retourne cerveau
	 * 
	 * @return cerveau
	 */
	public Cerveau getCerveau() {
		return cerveau;
	}

	/**
	 * Retourne nbrFrameAvantNouvelleAtk
	 * 
	 * @return nbrFrameAvantNouvelleAtk
	 */
	public int getNbrFrameAvantNouvelleAtk() {
		return nbrFrameAvantNouvelleAtk;
	}

	/**
	 * Retourne perso
	 * 
	 * @return perso
	 */
	public Joueur getPerso() {
		return perso;
	}

	/**
	 * Retourne pv
	 * 
	 * @return pv
	 */
	public int getPv() {
		return pv;
	}

	/**
	 * Retourne vAtk
	 * 
	 * @return vAtk
	 */
	public int getvAtk() {
		return vAtk;
	}

	/**
	 * Retourne vitesseDeplacement
	 * 
	 * @return vitesseDeplacement
	 */
	public int getVitesseDeplacement() {
		return vitesseDeplacement;
	}

	/**
	 * Retourne x
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	@Override
	public void retirerVie(int degats) {
		this.pv -= degats;
	}

	/**
	 * Permet de deplacer un monstre
	 * 
	 * @return Retourne la commande effectuee
	 */
	public String seDeplacer() {
		int dx, dy;

		int xp, yp;

		if (this.perso == null) {
			xp = 0;
			yp = 0;
		} else {
			xp = this.perso.getX();
			yp = this.perso.getY();
		}

		String decision = cerveau.decider(x, y, xp, yp);
		switch (decision) {
		case "N":
			dx = 0;
			dy = -this.vitesseDeplacement;
			break;
		case "NE":
			dx = this.vitesseDeplacement;
			dy = -this.vitesseDeplacement;
			break;
		case "E":
			dx = this.vitesseDeplacement;
			dy = 0;
			break;
		case "SE":
			dx = this.vitesseDeplacement;
			dy = this.vitesseDeplacement;
			break;
		case "S":
			dx = 0;
			dy = this.vitesseDeplacement;
			break;
		case "SO":
			dx = -this.vitesseDeplacement;
			dy = this.vitesseDeplacement;
			break;
		case "O":
			dx = -this.vitesseDeplacement;
			dy = 0;
			break;
		case "NO":
			dx = -this.vitesseDeplacement;
			dy = -this.vitesseDeplacement;
			break;
		case "Attaquer":
			this.attaquer(perso);
			return decision;
		default:
			dx = 0;
			dy = 0;
		}

		this.x += dx;
		this.y += dy;

		while (carte.getCases()[this.x / TAILLE][this.y / TAILLE].estBloquant()
				|| carte.getCases()[(this.x + TAILLE) / TAILLE][(this.y + TAILLE) / TAILLE].estBloquant()
				|| carte.getCases()[(this.x + TAILLE) / TAILLE][this.y / TAILLE].estBloquant()
				|| carte.getCases()[this.x / TAILLE][(this.y + TAILLE) / TAILLE].estBloquant()
				|| carte.getCases()[this.x / TAILLE][this.y / TAILLE].getTransition() != null
				|| carte.getCases()[(this.x + TAILLE) / TAILLE][(this.y + TAILLE) / TAILLE].getTransition() != null
				|| carte.getCases()[(this.x + TAILLE) / TAILLE][this.y / TAILLE].getTransition() != null
				|| carte.getCases()[this.x / TAILLE][(this.y + TAILLE) / TAILLE].getTransition() != null) {

			if (dx != 0)
				x -= dx / Math.abs(dx);
			if (dy != 0)
				y -= dy / Math.abs(dy);
		}
		return decision;
	}

	/**
	 * @param atk Nouvelle valeur de atk
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}

	/**
	 * @param cerveau Nouvelle valeur de cerveau
	 */
	public void setCerveau(Cerveau cerveau) {
		this.cerveau = cerveau;
	}

	/**
	 * @param nbrFrameAvantNouvelleAtk Nouvelle valeur de nbrFrameAvantNouvelleAtk
	 */
	public void setNbrFrameAvantNouvelleAtk(int nbrFrameAvantNouvelleAtk) {
		this.nbrFrameAvantNouvelleAtk = nbrFrameAvantNouvelleAtk;
	}

	/**
	 * @param perso Nouvelle valeur de perso
	 */
	public void setPerso(Joueur perso) {
		this.perso = perso;
	}

	/**
	 * @param pv Nouvelle valeur de pv
	 */
	public void setPv(int pv) {
		this.pv = pv;
	}

	/**
	 * @param vAtk Nouvelle valeur de vAtk
	 */
	public void setvAtk(int vAtk) {
		this.vAtk = vAtk;
	}

	/**
	 * @param vitesseDeplacement Nouvelle valeur de vitesseDeplacement
	 */
	public void setVitesseDeplacement(int vitesseDeplacement) {
		this.vitesseDeplacement = vitesseDeplacement;
	}

	@Override
	public abstract int subirDegats(int vAttaque);
}