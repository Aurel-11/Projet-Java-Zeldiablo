import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.monde.Case;
import application.monde.Transition;
import exception.CoordonneeException;

/**
 * Test de la classe Case.java
 */
public class CaseTest {

	/**
	 * Teste le constructeur avec des parametres normaux
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Case c = new Case(2, 3);
		assertEquals("La position en X devrait etre egal a 2", 2, c.getX());
		assertEquals("La position en Y devrait etre egal a 3", 3, c.getY());
		assertEquals("Le boolean bloquant devrait etre initialise a false", false, c.estBloquant());
		assertEquals("La case ne devrait pas avoir de transition", true, c.getTransition() == null);
	}

	/**
	 * Teste le constructeur avec un parametre negatif en x
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test(expected = CoordonneeException.class)
	public void constructeurNegatifX() throws CoordonneeException {
		new Case(-2, 3);
	}

	/**
	 * Teste le constructeur avec un parametre negatif en y
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test(expected = CoordonneeException.class)
	public void constructeurNegatifY() throws CoordonneeException {
		new Case(2, -3);
	}

	/**
	 * Teste la methode ajouterTransition
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void ajouterTransitionOK() throws CoordonneeException {
		Case c = new Case(2, 3);
		Transition t = new Transition(0);
		c.ajouterTransition(t);

		assertEquals("La transition de la case ne devrait pas etre null", true, c.getTransition() != null);
		assertEquals("La transition de la case devrait etre celle en parametre", t, c.getTransition());
	}

}
