package application.joueur;

import static jeu.Dessin.TAILLE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import application.Attaquant;
import application.ElementDessin;
import application.ennemi.Monstre;
import application.monde.Carte;
import exception.CoordonneeException;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represente un joueur
 */
public class Joueur implements ElementDessin, Attaquant {

	private int x, y;
	private Carte carte;
	private int atk;
	private int vAtk;
	private int pv;
	private int dir;
	private final int FRAME_INVULNERABLE_DEFAULT = 40;
	private int nbrFrameInvuRestante = 0;
	private int NbrFrameAvantNouvelleAtk = 0;
	private final int PORTEE = 24;

	/**
	 * Constructeur vide de Joueur.java La position est initialisee en (0,0)
	 * 
	 * @param c Carte sur laquelle le joueur se trouve
	 */
	public Joueur(Carte c) {
		x = 0;
		y = 0;
		carte = c;
		this.atk = 1;
		this.vAtk = 1;
		this.pv = 1;
		this.dir = 0;
		this.chargerSprite();
	}

	/**
	 * Constructeur de Joueur.java pour
	 * 
	 * @param carte Carte sur laquelle est le joueur
	 * @param atk   Valeur d'attaque
	 * @param vAtk  Vitesse d'attaque
	 */
	public Joueur(Carte carte, int atk, int vAtk) {
		this.x = 0;
		this.y = 0;
		this.carte = carte;
		this.atk = atk;
		this.vAtk = vAtk;
		this.chargerSprite();
	}

	/**
	 * Constructeur de Joueur.java pour une position donnee
	 * 
	 * @param x Position en x du personnage
	 * @param y Position en y du personnage
	 * @param c Carte sur laquelle se trouve le joueur
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public Joueur(int x, int y, Carte c) throws CoordonneeException {
		if (x < 0 || y < 0)
			throw new CoordonneeException();
		this.x = x;
		this.y = y;
		carte = c;
		this.chargerSprite();
	}

	/**
	 * Constructeur de Joueur.java pour
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le joueur
	 * @param pv    Points de vie
	 */
	public Joueur(int x, int y, Carte carte, int pv) {
		this.x = x;
		this.y = y;
		this.carte = carte;
		this.pv = pv;
		this.chargerSprite();
	}

	/**
	 * Constructeur de Joueur.java pour une position, une carte, une vitesse et des
	 * stats
	 * 
	 * @param x     Position en x
	 * @param y     Position en y
	 * @param carte Carte sur laquelle est le joueur
	 * @param atk   Valeur d'attaque
	 * @param vAtk  Vitesse d'attaque
	 * @param pv    Points de vie
	 */
	public Joueur(int x, int y, Carte carte, int atk, int vAtk, int pv) {
		this.x = x;
		this.y = y;
		this.carte = carte;
		this.atk = atk;
		this.vAtk = vAtk;
		this.pv = pv;
		this.chargerSprite();
	}

	@Override
	public void afficher(Graphics g) {

		if (this.etreMort()) {
			if (JeuZeldiablo.debugMod) {
				g.setColor(Color.red);
				g.fillRect(this.x, this.y, TAILLE, TAILLE);
				g.setColor(Color.black);
				g.drawLine(x + 5, y + 5, x + 10, y + 10);
				g.drawLine(x + 5, y + 10, x + 10, y + 5);
				g.drawLine(x + TAILLE - 5, y + 5, x + TAILLE - 10, y + 10);
				g.drawLine(x + TAILLE - 5, y + 10, x + TAILLE - 10, y + 5);
			}
			Sprites.dessinerCentre(g, "Joueur_mort", this.x + TAILLE / 2, this.y + TAILLE / 2);
		} else {
			if (this.estInvulnerable() && !this.estEnCdAtk()) {
				if (JeuZeldiablo.debugMod) {
					g.setColor(Color.yellow);
					g.fillRect(this.x, this.y, TAILLE, TAILLE);
				}
				try {
					Sprites.dessinerCentre(g, "Joueur_invu", this.x + TAILLE / 2, this.y + TAILLE / 2);
				} catch (Error e) {

				}
			} else if (this.estEnCdAtk()) {
				try {
					switch(dir) {
						case 0 :
							Sprites.dessiner(g, "Joueur_cdAtkHaut", this.x, this.y - PORTEE);
							break;
						case 1 :
							Sprites.dessiner(g, "Joueur_cdAtkDroite", this.x, this.y);
							break;
						case 2 :
							Sprites.dessiner(g, "Joueur_cdAtk", this.x, this.y);
							break;
						case 3 :
							Sprites.dessiner(g, "Joueur_cdAtkGauche", this.x - PORTEE, this.y);
							break;
					}
				} catch (Error e) {

				}
			} else {
				if (JeuZeldiablo.debugMod) {
					g.setColor(Color.blue);
					g.fillRect(this.x, this.y, TAILLE, TAILLE);
				}
				try {
					switch(dir) {
						case 0 :
							Sprites.dessinerCentre(g, "JoueurHaut", this.x + TAILLE / 2, this.y + TAILLE / 2);
							break;
						case 1 :
							Sprites.dessinerCentre(g, "JoueurDroite", this.x + TAILLE / 2, this.y + TAILLE / 2);
							break;
						case 2 :
							Sprites.dessinerCentre(g, "Joueur", this.x + TAILLE / 2, this.y + TAILLE / 2);
							break;
						case 3 :
							Sprites.dessinerCentre(g, "JoueurGauche", this.x + TAILLE / 2, this.y + TAILLE / 2);
							break;
					}
				} catch (Error e) {

				}

			}
			if (JeuZeldiablo.debugMod) {
				g.setColor(Color.green);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
				g.drawString(new String(this.pv + " pv"), x, y + TAILLE + 10);

				if (this.estEnCdAtk())
					g.drawString(new String(this.NbrFrameAvantNouvelleAtk + " f"), x, y + TAILLE + 20);

				if (this.estInvulnerable())
					g.drawString(new String(this.nbrFrameInvuRestante + " f"), x, y + TAILLE + 30);
			}

			g.setColor(Color.black);
			if(JeuZeldiablo.debugMod) {
				switch (this.dir) {
					case 0:
						g.drawLine(x + TAILLE / 2, y, x + TAILLE / 2, y - PORTEE);
						break;
					case 1:
						g.drawLine(x + TAILLE, y + TAILLE / 2, x + TAILLE + PORTEE, y + TAILLE / 2);
						break;
					case 2:
						g.drawLine(x + TAILLE / 2, y + TAILLE, x + TAILLE / 2, y + TAILLE + PORTEE);
						break;
					case 3:
						g.drawLine(x, y + TAILLE / 2, x - PORTEE, y + TAILLE / 2);
						break;
				}
			}
		}

	}

	@Override
	public void ajouterVie(int heal) {
		this.pv += heal;
	}

	@Override
	public void attaquer(Attaquant cible) {
		if (!this.etreMort())
			this.retirerVie(cible.subirDegats(this.atk));
	}

	/**
	 * Permet de changer de carte en prenant en compte le changement de position
	 * 
	 * @param c Nouvelle carte
	 */
	public void changerCarte(Carte c) {
		if (!this.etreMort()) {
			if ((carte.getCases()[x / TAILLE][y / TAILLE].getTransition() != null
					&& carte.getCases()[x / TAILLE][y / TAILLE].getTransition().getPos() == 3)
					&& (carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null
							&& carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition().getPos() == 3)) {
				x = TAILLE * 30 - TAILLE * 2 - 1;
				carte = c;
			} else if ((carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null
					&& carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition().getPos() == 1)
					&& (carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition() != null
							&& carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition().getPos() == 1)) {
				x = TAILLE + 1;
				carte = c;
			} else if ((carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition() != null
					&& carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition().getPos() == 0)
					&& (carte.getCases()[x / TAILLE][y / TAILLE].getTransition() != null
							&& carte.getCases()[x / TAILLE][y / TAILLE].getTransition().getPos() == 0)) {
				y = TAILLE * 20 - TAILLE * 2 - 1;
				carte = c;
			} else if ((carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null
					&& carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition().getPos() == 2)
					&& (carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null
							&& carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition()
									.getPos() == 2)) {
				y = TAILLE + 1;
				carte = c;
			}

			this.carte.changerFocus(this);
		}

	}

	/**
	 * Permet de changer la direction du joueur
	 * 
	 * @param d direction vers laquelle le personnage se dirige
	 */
	public void changerDirection(int d) {
		dir = d;
	}

	/**
	 * Permet de decrementer le nombre de frame avant une nouvelle attaque
	 */
	public void decrementerFrameAvantNouvelleAtk() {
		this.NbrFrameAvantNouvelleAtk--;
	}

	/**
	 * Permet de decrementer le nombre de frame d'invulnerabilite
	 */
	public void decrementerFrameInvulnerabilite() {
		this.nbrFrameInvuRestante--;
	}

	/**
	 * Permet de demarrer le cooldown d'attaque
	 */
	public void demarrerCooldownAtk() {
		this.NbrFrameAvantNouvelleAtk = this.vAtk;
	}

	/**
	 * Permet de savoir si le joueur est en cooldown d'attaque
	 * 
	 * @return True si le joueur est en cooldown d'attaque
	 */
	public boolean estEnCdAtk() {
		return this.NbrFrameAvantNouvelleAtk > 0;
	}

	/**
	 * Retourne invulnerable
	 * 
	 * @return invulnerable
	 */
	public boolean estInvulnerable() {
		return this.nbrFrameInvuRestante > 0;
	}

	@Override
	public boolean etreMort() {
		return this.pv <= 0;
	}

	/**
	 * Permet au joueur de frapper au corps-a-corps
	 */
	public void frapper() {
		for (Monstre m : getCarte().getMonstres()) {
			switch (dir) {
			case 0:
				if (getX() + (TAILLE / 4) >= m.getX() && getX() + (TAILLE / 4) <= m.getX() + TAILLE
						&& getY() - PORTEE >= m.getY() && getY() - PORTEE <= m.getY() + TAILLE
						|| getX() + (3 * TAILLE / 4) >= m.getX() && getX() + (3 * TAILLE / 4) <= m.getX() + TAILLE
								&& getY() - PORTEE >= m.getY() && getY() - PORTEE <= m.getY() + TAILLE
								|| getX() + (TAILLE / 4) >= m.getX() && getX() + (TAILLE / 4) <= m.getX() + TAILLE
								&& getY() >= m.getY() && getY() <= m.getY() + TAILLE
						|| getX() + (3 * TAILLE / 4) >= m.getX() && getX() + (3 * TAILLE / 4) <= m.getX() + TAILLE
								&& getY() >= m.getY() && getY() <= m.getY() + TAILLE) {
					attaquer(m);
				}
				break;
			case 1:
				if (getY() + (TAILLE / 4) >= m.getY() && getY() + (TAILLE / 4) <= m.getY() + TAILLE
						&& getX() + PORTEE + TAILLE >= m.getX() && getX() + PORTEE + TAILLE <= m.getX() + TAILLE
						|| getY() + (3 * TAILLE / 4) >= m.getY() && getY() + (3 * TAILLE / 4) <= m.getY() + TAILLE
						&& getX() + PORTEE + TAILLE >= m.getX() && getX() + PORTEE + TAILLE <= m.getX() + TAILLE
						|| getY() + (TAILLE / 4) >= m.getY() && getY() + (TAILLE / 4) <= m.getY() + TAILLE
						&& getX() + TAILLE >= m.getX() && getX() + TAILLE <= m.getX() + TAILLE
						|| getY() + (3 * TAILLE / 4) >= m.getY() && getY() + (3 * TAILLE / 4) <= m.getY() + TAILLE
						&& getX() + TAILLE >= m.getX() && getX() + TAILLE <= m.getX() + TAILLE) {
					attaquer(m);
				}
				break;
			case 2:
				if (getX() + (TAILLE / 4) >= m.getX() && getX() + (TAILLE / 4) <= m.getX() + TAILLE
						&& getY() + PORTEE + TAILLE >= m.getY() && getY() + PORTEE + TAILLE <= m.getY() + TAILLE
						|| getX() + (3 * TAILLE / 4) >= m.getX() && getX() + (3 * TAILLE / 4) <= m.getX() + TAILLE
								&& getY() + PORTEE + TAILLE >= m.getY() && getY() + PORTEE + TAILLE <= m.getY() + TAILLE
								|| getX() + (TAILLE / 4) >= m.getX() && getX() + (TAILLE / 4) <= m.getX() + TAILLE
								&& getY() + TAILLE >= m.getY() && getY() + TAILLE <= m.getY() + TAILLE
						|| getX() + (3 * TAILLE / 4) >= m.getX() && getX() + (3 * TAILLE / 4) <= m.getX() + TAILLE
								&& getY() + TAILLE >= m.getY() && getY() + TAILLE <= m.getY() + TAILLE) {
					attaquer(m);
				}
				break;
			case 3:
				if (getY() + (TAILLE / 4) >= m.getY() && getY() + (TAILLE / 4) <= m.getY() + TAILLE
						&& getX() - PORTEE >= m.getX() && getX() - PORTEE <= m.getX() + TAILLE
						|| getY() + (3 * TAILLE / 4) >= m.getY() && getY() + (3 * TAILLE / 4) <= m.getY() + TAILLE
								&& getX() - PORTEE >= m.getX() && getX() - PORTEE <= m.getX() + TAILLE
								|| getY() + (TAILLE / 4) >= m.getY() && getY() + (TAILLE / 4) <= m.getY() + TAILLE
								&& getX() >= m.getX() && getX() <= m.getX() + TAILLE
						|| getY() + (3 * TAILLE / 4) >= m.getY() && getY() + (3 * TAILLE / 4) <= m.getY() + TAILLE
								&& getX() >= m.getX() && getX() <= m.getX() + TAILLE) {
					attaquer(m);
				}
			}
		}
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
	 * Retourne la carte sur laquelle se trouve le joueur
	 * 
	 * @return Carte du joueur
	 */
	public Carte getCarte() {
		return carte;
	}

	/**
	 * Retourne la direction du perosnnage
	 * 
	 * @return dir
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * Retourne nbrFrameAvantNouvelleAtk
	 * 
	 * @return nbrFrameAvantNouvelleAtk
	 */
	public int getNbrFrameAvantNouvelleAtk() {
		return NbrFrameAvantNouvelleAtk;
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
	 * Retourne vatk
	 * 
	 * @return vatk
	 */
	public double getVatk() {
		return vAtk;
	}

	/**
	 * Retourne la position en x du personnage
	 * 
	 * @return Position en x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne la position en y du personnage
	 * 
	 * @return Position en y
	 */
	public int getY() {
		return y;
	}

	@Override
	public void retirerVie(int degats) {
		this.pv -= degats;
	}

	/**
	 * Permet de deplacer un personnage
	 * 
	 * @param dx Deplacement en x du personnage
	 * @param dy Deplacement en y du personnage
	 */
	public void seDeplacer(int dx, int dy) {
		if (!this.etreMort()) {
			x += dx;
			y += dy;
			while (carte.getCases()[x / TAILLE][y / TAILLE].estBloquant()
					|| carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].estBloquant()
					|| carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].estBloquant()
					|| carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].estBloquant()) {
				if (dx != 0)
					x -= dx / Math.abs(dx);
				if (dy != 0)
					y -= dy / Math.abs(dy);
			}
			if (carte.getCases()[x / TAILLE][y / TAILLE].getTransition() != null) {
				changerCarte(carte.getCases()[x / TAILLE][y / TAILLE].getTransition().getCarte());
			} else if (carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null) {
				changerCarte(carte.getCases()[(x + TAILLE) / TAILLE][(y + TAILLE) / TAILLE].getTransition().getCarte());
			} else if (carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition() != null) {
				changerCarte(carte.getCases()[(x + TAILLE) / TAILLE][y / TAILLE].getTransition().getCarte());
			} else if (carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition() != null) {
				changerCarte(carte.getCases()[x / TAILLE][(y + TAILLE) / TAILLE].getTransition().getCarte());
			}
		}

	}

	/**
	 * Change la carte du joueur
	 *
	 * @param carte Nouvelle carte
	 */
	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	/**
	 * Change la position en x
	 * 
	 * @param x Nouvelle position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Change la position en y
	 * 
	 * @param y Nouvelle position
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int subirDegats(int vAttaque) {
		if (vAttaque != 0 && !this.estInvulnerable() && !this.etreMort()) {
			this.retirerVie(vAttaque);
			this.nbrFrameInvuRestante = FRAME_INVULNERABLE_DEFAULT;
		}

		return 0;
	}

	private void chargerSprite() {
		try {
			Sprites.chargerImage("Joueur", System.getProperty("user.dir") + "/img/joueur.png");
			Sprites.chargerImage("JoueurDroite", System.getProperty("user.dir") + "/img/joueur_d_droite.png");
			Sprites.chargerImage("JoueurGauche", System.getProperty("user.dir") + "/img/joueur_d_gauche.png");
			Sprites.chargerImage("JoueurHaut", System.getProperty("user.dir") + "/img/joueur_d_haut.png");
			Sprites.chargerImage("Joueur_invu",
					System.getProperty("user.dir") + "/img/joueur_invu.png");
			Sprites.chargerImage("Joueur_mort",
					System.getProperty("user.dir") + "/img/joueur_mort.png");
			Sprites.chargerImage("Joueur_cdAtk",
					System.getProperty("user.dir") + "/img/joueur_cdAtk.png");
			Sprites.chargerImage("Joueur_cdAtkDroite",
					System.getProperty("user.dir") + "/img/joueur_cdAtk_droite.png");
			Sprites.chargerImage("Joueur_cdAtkGauche",
					System.getProperty("user.dir") + "/img/joueur_cdAtk_gauche.png");
			Sprites.chargerImage("Joueur_cdAtkHaut",
					System.getProperty("user.dir") + "/img/joueur_cdAtk_haut.png");
		} catch (Error e) {

		}
	}
}