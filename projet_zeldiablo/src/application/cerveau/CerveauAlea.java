package application.cerveau;

/**
 * Represente une IA qui se deplace de facon aleatoire
 */
public class CerveauAlea implements Cerveau {
	
	private String derniereDecision;

	@Override
	public String decider(int xm, int ym, int xp, int yp) {
		switch((int)Math.rint(Math.random()*10)) {
		case 0 :
			this.derniereDecision = "N";
			return "N";
		case 1 :
			this.derniereDecision = "S";
			return "S";
		case 2 :
			this.derniereDecision = "O";
			return "O";
		case 3 :
			this.derniereDecision = "E";
			return "E";
		case 4 :
			this.derniereDecision = "NE";
			return "NE";
		case 5 :
			this.derniereDecision = "NO";
			return "NO";
		case 6 :
			this.derniereDecision = "SO";
			return "SO";
		case 7 :
			this.derniereDecision = "SE";
			return "SE";
		default :
			this.derniereDecision = "N";
			return "N";
		}
	}

	/**
	 * Retourne derniereDecision
	 * @return derniereDecision
	 */
	public String getDerniereDecision() {
		return derniereDecision;
	}

}
