package main;

import java.io.File;

import exception.CoordonneeException;
import jeu.Dessin;
import jeu.JeuZeldiablo;
import moteurJeu.moteur.DessinAbstract;
import moteurJeu.moteur.JeuAbstract;
import moteurJeu.moteur.MoteurGraphique;

/**
 * 
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, CoordonneeException {

        JeuZeldiablo jeu = new JeuZeldiablo();
        DessinAbstract dessin = new Dessin(jeu);

        MoteurGraphique moteur = new MoteurGraphique((jeu), dessin);
        
        moteur.lancerJeu(960,640, 30);
    }
}
