package jeu;

import static jeu.Dessin.TAILLE;

import java.awt.event.KeyEvent;

import application.cerveau.CerveauAlea;
import application.ennemi.Archer;
import application.ennemi.Guerrier;
import application.ennemi.Monstre;
import application.ennemi.Tourelle;
import application.joueur.Joueur;
import application.monde.Case;
import application.monde.Etage;
import application.monde.Transition;
import exception.CoordonneeException;
import moteurJeu.moteur.CClavier;
import moteurJeu.moteur.CSouris;
import moteurJeu.moteur.JeuAbstract;

/**
 * Gere le deroulement du jeu
 */
public class JeuZeldiablo implements JeuAbstract {

	private Joueur perso;
	private Etage etage;

	private int v = VITESSE_DEFAULT;
	long iteration;
	/**
	 * Permet de savoir si le boss est mort
	 */
	public static boolean bossKilled = false;
	/**
	 * Permet d'activer ou non l'affichage des hitbox
	 */
	public static boolean debugMod = false;
	/**
	 * Vitesse par defaut de toutes les entites du jeu
	 */
	public static final int VITESSE_DEFAULT = 10;

	/**
	 * Constructeur vide de JeuZeldiablo.java
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public JeuZeldiablo() throws CoordonneeException {
		etage = new Etage();
		perso = new Joueur(100, 100, etage.getCartes().get(0), 2, 15, 99);
		perso.getCarte().changerFocus(perso);
		iteration = 0;
	}

	@Override
	public boolean etreFini() {
		return perso.etreMort() || JeuZeldiablo.bossKilled;
	}

	@Override
	public String evoluer(CClavier clavier, CSouris souris) {

		iteration++;
		if (iteration > 1000) {
			iteration = 0;
		}
		if (perso.estInvulnerable())
			perso.decrementerFrameInvulnerabilite();

		if (perso.getNbrFrameAvantNouvelleAtk() > 0)
			perso.decrementerFrameAvantNouvelleAtk();

		// decale le Joueur en fonction des touches
		if (clavier.isPressed(KeyEvent.VK_LEFT)) {
			perso.seDeplacer(-v, 0);
			perso.changerDirection(3);
		}

		if (clavier.isPressed(KeyEvent.VK_RIGHT)) {
			perso.seDeplacer(v, 0);
			perso.changerDirection(1);
		}

		if (clavier.isPressed(KeyEvent.VK_UP)) {
			perso.seDeplacer(0, -v);
			perso.changerDirection(0);
		}

		if (clavier.isPressed(KeyEvent.VK_DOWN)) {
			perso.seDeplacer(0, v);
			perso.changerDirection(2);
		}

		if (clavier.getTyped(KeyEvent.VK_SPACE)) {
			if (perso.getNbrFrameAvantNouvelleAtk() == 0) {
				perso.frapper();
				perso.demarrerCooldownAtk();
			}
		}

		if (perso.getCarte().getMonstres().isEmpty()) {
			for (int i = 0; i < perso.getCarte().getCases().length; i++) {
				for (int j = 0; j < perso.getCarte().getCases()[i].length; j++) {
					if (perso.getCarte().getCases()[i][j].getTransition() != null) {
						Transition t = perso.getCarte().getCases()[i][j].getTransition();
						try {
							perso.getCarte().getCases()[i][j] = new Case(i, j);
						} catch (CoordonneeException e) {
							e.printStackTrace();
						}
						perso.getCarte().getCases()[i][j].ajouterTransition(t);
					}
				}
			}
		}

		if (clavier.getTyped(KeyEvent.VK_B)) {
			JeuZeldiablo.debugMod = !JeuZeldiablo.debugMod;
		}

		if (JeuZeldiablo.debugMod) {
			// modifie vitesse
			if (clavier.getTyped(KeyEvent.VK_A)) {
				this.v++;
				System.out.println("Vitesse = " + this.v);
			}
			if (clavier.getTyped(KeyEvent.VK_Q)) {
				this.v--;
				System.out.println("Vitesse = " + this.v);
			}
			if (clavier.getTyped(KeyEvent.VK_R)) {
				this.v = VITESSE_DEFAULT;
				System.out.println("Vitesse = " + this.v);
			}

			// Inflige des degats a tous les ennemies dans la salle pendant une frame
			if (clavier.isPressed(KeyEvent.VK_K)) {
				for (Monstre tmp : this.perso.getCarte().getMonstres()) {
					tmp.subirDegats(1);
					System.out.println(tmp + " : hp = " + tmp.getPv() + " v = " + tmp.getVitesseDeplacement());

				}
			}
			
			if (clavier.isPressed(KeyEvent.VK_EQUALS)) {
				this.perso.ajouterVie(1);
			}
			
			if (clavier.isPressed(KeyEvent.VK_1)) {
				Monstre m = new Guerrier(this.perso.getX(),this.perso.getY(),this.perso.getCarte());
				m.setPerso(perso);
				this.perso.getCarte().ajouterMonstre(m);
			}
			
			if (clavier.isPressed(KeyEvent.VK_2)) {
				Monstre m = new Archer(this.perso.getX(),this.perso.getY(),this.perso.getCarte());
				m.setPerso(perso);
				this.perso.getCarte().ajouterMonstre(m);
			}
			
			/*if (clavier.isPressed(KeyEvent.VK_3)) {
				Monstre m = new ChauveSouris(this.perso.getX(),this.perso.getY(),this.perso.getCarte());
				m.setPerso(perso);
				this.perso.getCarte().ajouterMonstre(m);
			}*/
			
			if (clavier.isPressed(KeyEvent.VK_4)) {
				Monstre m = new Tourelle(this.perso.getX(),this.perso.getY(),this.perso.getCarte());
				m.setPerso(perso);
				this.perso.getCarte().ajouterMonstre(m);
			}
		}

		for (Monstre m : perso.getCarte().getMonstres()) {
			CerveauAlea c = new CerveauAlea();
			if (m.getNbrFrameAvantNouvelleAtk() > 0)
				m.decrementerFrameAvantNouvelleAtk();

			if (m.getCerveau().getClass() != c.getClass()) {
				m.seDeplacer();
			} else {
				if (iteration > 30 || iteration == 0) {
					m.seDeplacer();
					iteration = 0;
				}
			}
		}
		for (Monstre m : perso.getCarte().getMonstres()) {
			CerveauAlea c = new CerveauAlea();
			if (m.getCerveau().getClass() == c.getClass() && iteration < 10) {
				m.continuerDeplacement(((CerveauAlea) (m.getCerveau())).getDerniereDecision());
			}
		}

		for (Monstre m : perso.getCarte().getMonstres()) {
			if (!(perso.getX() >= m.getX() + TAILLE || perso.getX() + TAILLE <= m.getX()
					|| perso.getY() >= m.getY() + TAILLE || perso.getY() + TAILLE <= m.getY())) {
				m.attaquer(perso);
			}
		}

		perso.getCarte().actualiserMonstres();

		return ("PJ");
	}

	/**
	 * Retourne le personnage joueur
	 * 
	 * @return Joueur
	 */
	public Joueur getPerso() {
		return perso;
	}
}