import static jeu.Dessin.TAILLE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.joueur.Joueur;
import application.monde.Carte;
import exception.CoordonneeException;

/**
 * Teste la classe Joueur.java
 */

/**
 * @author BERNARD Hugo 'Tenebrosful'
 *
 */
public class JoueurTest {

	/**
	 * Tableau d'entier utilise pour creer la carte
	 */
	private final int[][] carteInit = { { 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1 } };

	/**
	 * Teste le constructeur vide
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurVide() throws CoordonneeException {
		Carte c = new Carte(carteInit);
		Joueur p = new Joueur(c);

		assertEquals("La position en x devrait etre initialisee a 0", 0, p.getX());
		assertEquals("La position en y devrait etre initialisee a 0", 0, p.getY());
		assertEquals("La carte du Joueur ne devrait pas etre null", true, p.getCarte() != null);
		assertEquals("La carte du Joueur devrait etre celle placee en parametre", c, p.getCarte());
	}

	/**
	 * Teste le constructeur dans un cas normal
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Carte c = new Carte(carteInit);
		Joueur p = new Joueur(3, 5, c);

		assertEquals("La position en x devrait etre initialisee a 3", 3, p.getX());
		assertEquals("La position en y devrait etre initialisee a 3", 5, p.getY());
		assertEquals("La carte du Joueur ne devrait pas etre null", true, p.getCarte() != null);
		assertEquals("La carte du Joueur devrait etre celle placee en parametre", c, p.getCarte());

	}

	/**
	 * Teste le constructeur avec le parametre x negatif
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test(expected = CoordonneeException.class)
	public void constructeurNegatifX() throws CoordonneeException {
		Carte c = new Carte(carteInit);
		Joueur p = new Joueur(-1, 8, c);
	}

	/**
	 * Teste le constructeur avec le parametre y negatif
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test(expected = CoordonneeException.class)
	public void constructeurNegatifY() throws CoordonneeException {
		Carte c = new Carte(carteInit);
		Joueur p = new Joueur(1, -8, c);
	}

	/**
	 * Teste la methode seDeplacer vers des cases libres non bloquantes
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void seDeplacerOK() throws CoordonneeException {
		Carte c = new Carte(this.carteInit);
		Joueur p = new Joueur(2 * TAILLE, 2 * TAILLE, c);

		p.seDeplacer(0, 1);
		assertEquals("La position en x du Joueur devrait etre a 2", 2 * TAILLE, p.getX());
		assertEquals("La position en y du Joueur devrait etre a 3", 2 * TAILLE + 1, p.getY());

		p.seDeplacer(1, 0);
		assertEquals("La position en x du Joueur devrait etre a 3", 2 * TAILLE + 1, p.getX());
		assertEquals("La position en y du Joueur devrait etre a 3", 2 * TAILLE + 1, p.getY());

		p.seDeplacer(0, -1);
		assertEquals("La position en x du Joueur devrait etre a 3", 2 * TAILLE + 1, p.getX());
		assertEquals("La position en y du Joueur devrait etre a 2", 2 * TAILLE, p.getY());

		p.seDeplacer(-1, 0);
		assertEquals("La position en x du Joueur devrait etre a 2", 2 * TAILLE, p.getX());
		assertEquals("La position en y du Joueur devrait etre a 2", 2 * TAILLE, p.getY());
	}

}
