import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.monde.Transition;
import exception.CoordonneeException;

/**
 * 
 */

/**
 * @author BERNARD Hugo 'Tenebrosful'
 *
 */
public class TransitionTest {

	/**
	 * Tableau d'entier utilise pour creer la carte
	 */
	private final int[][] carteInit = { { 1, 1, 2, 1, 1, 1 }, { 1, 0, 0, 0, 0, 1 }, { 2, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 2 }, { 1, 1, 1, 2, 1, 1 } };

	/**
	 * Teste le constructeur normal d'une transition
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Transition t1 = new Transition(0);
		Transition t2 = new Transition(1);
		Transition t3 = new Transition(2);
		Transition t4 = new Transition(3);
		
		assertEquals("La position de la transition devrait etre a 0",0,t1.getPos());
		assertEquals("La position de la transition devrait etre a 1",1,t2.getPos());
		assertEquals("La position de la transition devrait etre a 2",2,t3.getPos());
		assertEquals("La position de la transition devrait etre a 3",3,t4.getPos());
		
	}

}
