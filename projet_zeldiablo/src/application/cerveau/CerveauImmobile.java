package application.cerveau;

/**
 * Represente une IA qui ne peut pas se deplacer et se contente d'attaquer
 */
public class CerveauImmobile implements Cerveau {

	@Override
	public String decider(int xm, int ym, int xp, int yp) {
		return "Attaquer";
	}

}
