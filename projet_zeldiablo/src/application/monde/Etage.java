package application.monde;

import static jeu.JeuZeldiablo.VITESSE_DEFAULT;

import java.util.ArrayList;

import application.cerveau.CerveauMelee;
import application.ennemi.*;
import exception.CoordonneeException;

/**
 * Represente un etage compose de cartes
 */
public class Etage {

	private ArrayList<Carte> cartes;

	/**
	 * Constructeur vide de Etage.java
	 * 
	 * @throws CoordonneeException Lancee lorsqu'un parametre du constructeur de
	 *                             Case est negatif
	 */
	public Etage() throws CoordonneeException {
		cartes = new ArrayList<>();
		int[][] tab1 = new int[30][20];
		int[][] tab2 = new int[30][20];
		int[][] tab3 = new int[30][20];
		int[][] tab4 = new int[30][20];
		int[][] tab5 = new int[30][20];
		int[][] tab6 = new int[30][20];
		int[][] tab7 = new int[30][20];
		int[][] tab8 = new int[30][20];
		int[][] tab9 = new int[30][20];
		int[][] tab10 = new int[30][20];

		for (int i = 0; i < tab1.length; i++) {
			for (int j = 0; j < tab1[i].length; j++) {
				if (i == 0 || j == 0 || i == tab1.length - 1 || j == tab1[i].length - 1) {
					tab1[i][j] = 1;
				} else {
					tab1[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab2.length; i++) {
			for (int j = 0; j < tab2[i].length; j++) {
				if (i == 0 || j == 0 || i == tab2.length - 1 || j == tab2[i].length - 1) {
					tab2[i][j] = 1;
				} else {
					tab2[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab3.length; i++) {
			for (int j = 0; j < tab3[i].length; j++) {
				if (i == 0 || j == 0 || i == tab3.length - 1 || j == tab3[i].length - 1) {
					tab3[i][j] = 1;
				} else {
					tab3[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab4.length; i++) {
			for (int j = 0; j < tab4[i].length; j++) {
				if (i == 0 || j == 0 || i == tab4.length - 1 || j == tab4[i].length - 1) {
					tab4[i][j] = 1;
				} else {
					tab4[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab5.length; i++) {
			for (int j = 0; j < tab5[i].length; j++) {
				if (i == 0 || j == 0 || i == tab5.length - 1 || j == tab5[i].length - 1) {
					tab5[i][j] = 1;
				} else {
					tab5[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab6.length; i++) {
			for (int j = 0; j < tab6[i].length; j++) {
				if (i == 0 || j == 0 || i == tab6.length - 1 || j == tab6[i].length - 1) {
					tab6[i][j] = 1;
				} else {
					tab6[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab7.length; i++) {
			for (int j = 0; j < tab7[i].length; j++) {
				if (i == 0 || j == 0 || i == tab7.length - 1 || j == tab7[i].length - 1) {
					tab7[i][j] = 1;
				} else {
					tab7[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab8.length; i++) {
			for (int j = 0; j < tab8[i].length; j++) {
				if (i == 0 || j == 0 || i == tab8.length - 1 || j == tab8[i].length - 1) {
					tab8[i][j] = 1;
				} else {
					tab8[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab9.length; i++) {
			for (int j = 0; j < tab9[i].length; j++) {
				if (i == 0 || j == 0 || i == tab9.length - 1 || j == tab9[i].length - 1) {
					tab9[i][j] = 1;
				} else {
					tab9[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < tab10.length; i++) {
			for (int j = 0; j < tab10[i].length; j++) {
				if (i == 0 || j == 0 || i == tab9.length - 1 || j == tab9[i].length - 1) {
					tab10[i][j] = 1;
				} else {
					tab10[i][j] = 0;
				}
			}
		}

		tab1[29][9] = 2;
		tab1[29][10] = 2;

		tab1[14][0] = 2;
		tab1[15][0] = 2;

		tab1[0][9] = 2;
		tab1[0][10] = 2;

		tab1[25][8] = 1;
		tab1[25][11] = 1;
		tab1[21][8] = 1;
		tab1[21][11] = 1;
		tab1[17][8] = 1;
		tab1[17][11] = 1;
		tab1[13][8] = 1;
		tab1[13][11] = 1;
		tab1[9][8] = 1;
		tab1[9][11] = 1;

		tab1[14][19] = 2;
		tab1[15][19] = 2;

		tab2[0][9] = 2;
		tab2[0][10] = 2;

		tab2[25][15] = 1;
		tab2[26][15] = 1;
		tab2[24][15] = 1;
		tab2[25][16] = 1;
		tab2[26][16] = 1;
		tab2[24][16] = 1;
		tab2[25][14] = 1;
		tab2[26][14] = 1;
		tab2[24][14] = 1;

		tab2[14][0] = 2;
		tab2[15][0] = 2;

		tab3[14][19] = 2;
		tab3[15][19] = 2;

		tab3[0][9] = 2;
		tab3[0][10] = 2;

		tab4[29][9] = 2;
		tab4[29][10] = 2;

		tab4[14][19] = 2;
		tab4[15][19] = 2;

		tab5[29][9] = 2;
		tab5[29][10] = 2;

		tab6[14][0] = 2;
		tab6[15][0] = 2;
		tab6[14][19] = 2;
		tab6[15][19] = 2;

		tab7[14][0] = 2;
		tab7[15][0] = 2;
		tab7[29][9] = 2;
		tab7[29][10] = 2;

		tab8[0][9] = 2;
		tab8[0][10] = 2;
		tab8[29][9] = 2;
		tab8[29][10] = 2;
		tab8[14][19] = 2;
		tab8[15][19] = 2;

		tab9[0][9] = 2;
		tab9[0][10] = 2;

		tab10[14][0] = 2;
		tab10[15][0] = 2;

		Carte c1 = new Carte(tab1);
		Carte c2 = new Carte(tab2);
		Carte c3 = new Carte(tab3);
		Carte c4 = new Carte(tab4);
		Carte c5 = new Carte(tab5);
		Carte c6 = new Carte(tab6);
		Carte c7 = new Carte(tab7);
		Carte c8 = new Carte(tab8);
		Carte c9 = new Carte(tab9);
		Carte c10 = new Carte(tab10);

		cartes.add(c1);
		cartes.add(c2);
		cartes.add(c3);
		cartes.add(c4);
		cartes.add(c5);
		cartes.add(c6);
		cartes.add(c7);
		cartes.add(c8);
		cartes.add(c9);
		cartes.add(c10);

		c1.getCases()[29][9].getTransition().ajouterCarte(getCartes().get(1));
		c1.getCases()[29][10].getTransition().ajouterCarte(getCartes().get(1));

		c1.getCases()[14][0].getTransition().ajouterCarte(getCartes().get(3));
		c1.getCases()[15][0].getTransition().ajouterCarte(getCartes().get(3));

		c1.getCases()[0][9].getTransition().ajouterCarte(getCartes().get(4));
		c1.getCases()[0][10].getTransition().ajouterCarte(getCartes().get(4));

		c1.getCases()[14][19].getTransition().ajouterCarte(getCartes().get(5));
		c1.getCases()[15][19].getTransition().ajouterCarte(getCartes().get(5));

		c2.getCases()[0][9].getTransition().ajouterCarte(getCartes().get(0));
		c2.getCases()[0][10].getTransition().ajouterCarte(getCartes().get(0));

		c2.getCases()[14][0].getTransition().ajouterCarte(getCartes().get(2));
		c2.getCases()[15][0].getTransition().ajouterCarte(getCartes().get(2));

		c3.getCases()[14][19].getTransition().ajouterCarte(getCartes().get(1));
		c3.getCases()[15][19].getTransition().ajouterCarte(getCartes().get(1));

		c3.getCases()[0][9].getTransition().ajouterCarte(getCartes().get(3));
		c3.getCases()[0][10].getTransition().ajouterCarte(getCartes().get(3));

		c4.getCases()[29][9].getTransition().ajouterCarte(getCartes().get(2));
		c4.getCases()[29][10].getTransition().ajouterCarte(getCartes().get(2));

		c4.getCases()[14][19].getTransition().ajouterCarte(getCartes().get(0));
		c4.getCases()[15][19].getTransition().ajouterCarte(getCartes().get(0));

		c5.getCases()[29][9].getTransition().ajouterCarte(getCartes().get(0));
		c5.getCases()[29][10].getTransition().ajouterCarte(getCartes().get(0));

		c6.getCases()[14][0].getTransition().ajouterCarte(getCartes().get(0));
		c6.getCases()[15][0].getTransition().ajouterCarte(getCartes().get(0));

		c6.getCases()[14][19].getTransition().ajouterCarte(getCartes().get(6));
		c6.getCases()[15][19].getTransition().ajouterCarte(getCartes().get(6));

		c7.getCases()[14][0].getTransition().ajouterCarte(getCartes().get(5));
		c7.getCases()[15][0].getTransition().ajouterCarte(getCartes().get(5));

		c7.getCases()[29][9].getTransition().ajouterCarte(getCartes().get(7));
		c7.getCases()[29][10].getTransition().ajouterCarte(getCartes().get(7));

		c8.getCases()[0][9].getTransition().ajouterCarte(getCartes().get(6));
		c8.getCases()[0][10].getTransition().ajouterCarte(getCartes().get(6));

		c8.getCases()[29][9].getTransition().ajouterCarte(getCartes().get(8));
		c8.getCases()[29][10].getTransition().ajouterCarte(getCartes().get(8));

		c8.getCases()[14][19].getTransition().ajouterCarte(getCartes().get(9));
		c8.getCases()[15][19].getTransition().ajouterCarte(getCartes().get(9));

		c9.getCases()[0][9].getTransition().ajouterCarte(getCartes().get(7));
		c9.getCases()[0][10].getTransition().ajouterCarte(getCartes().get(7));

		c10.getCases()[14][0].getTransition().ajouterCarte(getCartes().get(7));
		c10.getCases()[15][0].getTransition().ajouterCarte(getCartes().get(7));

		c3.ajouterMonstre(new Archer(400, 400, c3));
		c3.ajouterMonstre(new Guerrier(33, 33, c3));
		c3.ajouterMonstre(new Guerrier(896, 33, c3));
		c3.ajouterMonstre(new Guerrier(896, 570, c3));
		c3.ajouterMonstre(new Guerrier(33, 570, c3));
		c4.ajouterMonstre(new ChauveSouris(400, 300, c4));
		c4.ajouterMonstre(new Tourelle(500, 400, c4));
		c2.ajouterMonstre(new Archer(33, 33, c2));
		c2.ajouterMonstre(new Archer(896, 576, c2));
		c2.ajouterMonstre(
				new ChauveSouris(200, 200, new CerveauMelee(0), c2, (int) Math.round(VITESSE_DEFAULT * 0.5)));
		c5.ajouterMonstre(new Archer(500, 50, c5));
		c5.ajouterMonstre(new Archer(850, 500, c5));
		c6.ajouterMonstre(new Archer(400, 400, c6));
		c6.ajouterMonstre(new Guerrier(33, 33, c6));
		c6.ajouterMonstre(new Guerrier(896, 33, c6));
		c7.ajouterMonstre(new Guerrier(896, 570, c7));
		c7.ajouterMonstre(new Guerrier(33, 570, c7));
		c7.ajouterMonstre(new ChauveSouris(400, 300, c7));

		c9.ajouterMonstre(new Boss(480, 320, c9));
		
		c10.ajouterMonstre(new ChauveSouris(200,200, c10));
		c10.ajouterMonstre(new ChauveSouris(300,300, c10));
		c10.ajouterMonstre(new ChauveSouris(400,400, c10));
		c10.ajouterMonstre(new ChauveSouris(350,200, c10));
		c10.ajouterMonstre(new ChauveSouris(600,60, c10));
		c10.ajouterMonstre(new ChauveSouris(85,120, c10));
		c10.ajouterMonstre(new ChauveSouris(555,450, c10));
		c10.ajouterMonstre(new ChauveSouris(450,500,new CerveauMelee(0),c10,(int) Math.round(VITESSE_DEFAULT * 0.8)));
	}

	/**
	 * Retourne la liste des cartes de l'etage
	 * 
	 * @return Cartes de l'etage
	 */
	public ArrayList<Carte> getCartes() {
		return cartes;
	}
}
