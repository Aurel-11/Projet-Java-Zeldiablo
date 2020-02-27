package jeu;

import moteurJeu.Commande;
import moteurJeu.Jeu;

public class JeuPerso implements Jeu{
	private int xPerso;
	private int yPerso;

	public JeuPerso() {
		// TODO Auto-generated constructor stub
	}

	public void evoluer(Commande c) {
		if (c.haut)
			yPerso--;
		if (c.bas)
			yPerso++;
		if (c.droite)
			xPerso++;
		if (c.gauche)
			xPerso--;
	}

	public int getxPerso() {
		return xPerso;
	}

	public void setxPerso(int xPerso) {
		this.xPerso = xPerso;
	}

	public int getyPerso() {
		return yPerso;
	}

	public void setyPerso(int yPerso) {
		this.yPerso = yPerso;
	}

	public boolean etreFini() {
		return false;
	}
}
