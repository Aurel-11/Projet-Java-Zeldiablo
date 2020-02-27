package application.monde;

import java.util.ArrayList;

import application.ennemi.Boss;
import application.ennemi.Monstre;
import application.joueur.Joueur;
import exception.CoordonneeException;
import jeu.JeuZeldiablo;
import moteurJeu.sprite.Sprites;

/**
 * Represante une carte
 */
public class Carte {

	/**
	 * tableau a 2 dimensions qui cree les cases du jeu
	 */
	private Case[][] cases;
	
	private ArrayList<Monstre> monstres;

	/**
	 * constructeur de la classe carte
	 * 
	 * @param map tableau des cases de la carte
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public Carte(int[][] map) throws CoordonneeException {
		int i = 0;
		cases = new Case[map.length][map[i].length];
		for (i = 0; i < cases.length; i++) {
			for (int j = 0; j < cases[i].length; j++) {
				if (map[i][j] == 1) {
					cases[i][j] = new Mur(i, j);
				} else if (map[i][j] == 2) {
					cases[i][j] = new Mur(i, j);
					if(i == 29){
						cases[i][j].ajouterTransition(new Transition(1));
					}
					else if(i == 0){
						cases[i][j].ajouterTransition(new Transition(3));
					}
					else if(j == 19){
						cases[i][j].ajouterTransition(new Transition(2));
					}
					else if(j == 0){
						cases[i][j].ajouterTransition(new Transition(0));
					}
				} else {
					cases[i][j] = new Case(i, j);
				}
			}
		}
		
		this.monstres = new ArrayList<Monstre>();
	}

	/**
	 * Permet d'actualiser la liste de monstre de la carte retirant les monstres morts
	 */
	public void actualiserMonstres() {
		for(int  i = 0; i < this.monstres.size(); i++) {
			Monstre tmp = this.monstres.get(i);
			if(tmp.etreMort()) {
				if(tmp instanceof Boss)
					JeuZeldiablo.bossKilled = true;
				
				this.monstres.remove(tmp);
				i--;
			}
				
		}
	}

	/**
	 * Permet d'ajouter un monstre a la carte
	 * @param m Monstre a ajouter
	 */
	public void ajouterMonstre(Monstre m) {
		this.monstres.add(m);
	}
	
	/**
	 * Permet de faire changer de focus a tous les monstres de la carte
	 * @param p Joueur a prendre pour cible
	 */
	public void changerFocus(Joueur p) {
		for(Monstre tmp : this.monstres) {
			tmp.setPerso(p);
		}
	}

	/**
	 * methode qui retourne une case par rapport a une coordonnee
	 * 
	 * @param i Position en x de la case
	 * @param j Position en y de la case
	 * @return Case correspondante a la coordonnee
	 */
	public Case get(int i, int j) {
		return cases[i][j];
	}
	
	/**
	 * Retourne cases
	 * 
	 * @return cases
	 */
	public Case[][] getCases() {
		return cases;
	}
	
	/**
	 * Retourne monstres
	 * @return monstres
	 */
	public ArrayList<Monstre> getMonstres() {
		return monstres;
	}
	
	/**
	 * Methode obsolete faisant se deplacer tous les monstres de la carte
	 */
	public void jouerMonstre() {
		for(Monstre tmp : this.monstres) {
			tmp.seDeplacer();
		}
	}

}
