import static org.junit.Assert.assertEquals;

import org.junit.Test;

import application.monde.Etage;
import exception.CoordonneeException;

/**
 * 
 */

/**
 * @author BERNARD Hugo 'Tenebrosful'
 *
 */
public class EtageTest {

	/**
	 * Teste le constructeur normal d'un etage
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Etage e = new Etage();

		assertEquals("L'etage devrait contenir plus d'une carte", true, e.getCartes().size() > 1);
	}

}
