package application.monde;

/**
 * Represente une transition entre 2 cartes
 */
public class Transition {

	private int pos;
	private Carte carte;

	/**
	 * Constructeur de Transition.java pour une case donnee
	 * 
	 * @param p Position ou se trouve la transition
	 */
	public Transition(int p) {
		pos = p;
	}

	/**
	 * Permet d'ajouter une carte d'arrivee
	 * 
	 * @param c Carte liee a la transition
	 */
	public void ajouterCarte(Carte c) {
		carte = c;
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
	 * Retourne pos
	 * 
	 * @return pos
	 */
	public int getPos() {
		return pos;
	}
}