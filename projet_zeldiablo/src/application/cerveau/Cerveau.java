package application.cerveau;

/**
 * Modelise l'intelligence artificiel d'une entite
 */
public interface Cerveau {
	
	/**
	 * Permet de decider de l'action a affectuer
	 * "N" = Deplacement Nord
	 * "S" = Deplacement Sud
	 * "O" = Deplacement Ouest
	 * "E" = Deplacement Est
	 * "NE" = Deplacement Nord-Est
	 * "NO" = Deplacement Nord-Ouest
	 * "SO" = Deplacement Sud-Ouest
	 * "SE" = Deplacement Sud-Est
	 * "Attaquer" = Lancement d'une attaque sur le monstre
	 * @param xm Position en x du monstre
	 * @param ym Position en y du monstre
	 * @param xp Position en x du presonnage focus
	 * @param yp Position en y du personnage focus
	 * @return Action a effectuer
	 */
	String decider(int xm, int ym, int xp, int yp);
}
