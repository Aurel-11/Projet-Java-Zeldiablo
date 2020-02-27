package application.cerveau;

/**
 * Represente une IA qui cherche a aller au corps-a-corps du joueur pour l'attaquer
 */
public class CerveauMelee implements Cerveau {

	private int distanceVoulue;

	/**
	 * Constructeur de CerveauMelee.java pour une distance donnee
	 * 
	 * @param distanceVoulue Distance que l'IA va chercher a avoir entre elle et le
	 *                       joueur
	 */
	public CerveauMelee(int distanceVoulue) {
		this.distanceVoulue = distanceVoulue;
	}

	@Override
	public String decider(int xm, int ym, int xp, int yp) {
		int distanceActuelle = (int) Math.sqrt(Math.pow(xm - xp, 2) + Math.pow(ym - yp, 2));

		if (distanceActuelle > this.distanceVoulue) {
			int deltaX = xm - xp;
			int deltaY = ym - yp;

			double radian = Math.atan2(deltaX, deltaY);
			int degres = (int) (radian * (180 / Math.PI));

			if (degres >= -10 && degres <= 10)
				return "N";
			else if (degres > 10 && degres < 80)
				return "NO";
			else if (degres >= 80 && degres <= 100)
				return "O";
			else if (degres > 100 && degres < 170)
				return "SO";
			else if (degres >= 170 || degres <= -170)
				return "S";
			else if (degres < -10 && degres > -80)
				return "NE";
			else if (degres <= -80 && degres >= -100)
				return "E";
			else if (degres < -100 && degres > -170)
				return "SE";
		} else {
			return "Attaquer";
		}

		return "";
	}

}
