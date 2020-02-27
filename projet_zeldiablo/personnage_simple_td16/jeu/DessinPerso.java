package jeu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import jeu.JeuPerso;
import moteurJeu.DessinJeu;

public class DessinPerso implements DessinJeu{

    public static final int TAILLE = 20;
    private JeuPerso jeuEnCours;

    public DessinPerso(JeuPerso jp){
        jeuEnCours = jp;
    }

    public void dessiner(BufferedImage image){
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.BLUE);
        g.fillOval(jeuEnCours.getxPerso()*TAILLE, jeuEnCours.getyPerso()*TAILLE, TAILLE,TAILLE);
        g.dispose();
    }
}