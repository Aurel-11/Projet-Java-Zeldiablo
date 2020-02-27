import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import application.monde.Carte;
import application.monde.Case;
import application.monde.Mur;
import exception.CoordonneeException;

/**
 * Teste la classe Carte.java
 */

/**
 * @author BERNARD Hugo 'Tenebrosful'
 *
 */
public class CarteTest {

	/**
	 * Tableau d'entier utilise pour creer la carte
	 */
	private int[][] carteInit;
	
	@Before
	public void setup() {
		this.carteInit = new int[30][20];
		for (int i = 0; i < this.carteInit.length; i++) {
			for (int j = 0; j < this.carteInit[i].length; j++) {
				if (i == 0 || j == 0 || i == this.carteInit.length - 1 || j == this.carteInit[i].length - 1) {
					this.carteInit[i][j] = 1;
				} else {
					this.carteInit[i][j] = 0;
				}
			}
		}
	}

	/**
	 * Teste le constructeur utilise normalement avec en parametre un tableau a
	 * double entree de int
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	@Test
	public void constructeurOK() throws CoordonneeException {
		Carte c = new Carte(this.carteInit);

		int i = 0;

		for (int j = 0; j < this.carteInit.length; j++) {
			for (int k = 0; k < this.carteInit[j].length; k++) {
				if (carteInit[j][k] == 0)
					assertEquals("La case en (" + j + ";" + k + ") devrait etre du sol", true,
							c.get(i, i) instanceof Case);
				else if (carteInit[j][k] == 1)
					assertEquals("La case en (" + j + ";" + k + ") devrait etre un mur", true,
							c.get(i, i) instanceof Mur);
				else if (carteInit[j][k] == 2) {
					assertEquals("La case en (" + j + ";" + k + ") devrait etre du sol", true,
							c.get(i, i) instanceof Case);
					assertEquals("La case en (" + j + ";" + k + ") devrait avoir une transition", true,
							c.get(i, j).getTransition() != null);
				}

			}
		}
	}
}