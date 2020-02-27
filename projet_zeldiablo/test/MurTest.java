
/**
 * Teste la classe Mur.java
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.monde.Case;
import application.monde.Mur;
import exception.CoordonneeException;

/**
 * @author BERNARD Hugo 'Tenebrosful'
 *
 */
public class MurTest {

	/**
	 * Teste le constructeur avec des parametres normaux
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de Mur
	 *                             est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Case c = new Mur(2, 3);
		assertEquals("La position en X devrait etre egal a 2", 2, c.getX());
		assertEquals("La position en Y devrait etre egal a 3", 3, c.getY());
		assertEquals("Le boolean bloquant devrait etre initialise a true", true, c.estBloquant());
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
		new Mur(-2, 3);
	}

	/**
	 * Teste le constructeur avec un parametre negatif en y
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test(expected = CoordonneeException.class)
	public void constructeurNegatifY() throws CoordonneeException {
		new Mur(2, -3);
	}

}
