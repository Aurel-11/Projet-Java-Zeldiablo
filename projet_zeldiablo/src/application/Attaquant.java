package application;

/**
 * Represente une entite capable d'attaquer et subir une attaque
 */
public interface Attaquant {

    /**
     * Permet de soigner
     * @param heal Points de vie gagnes
     */
    public void ajouterVie(int heal);

    /**
     * Permet d'attaquer une cible
     * @param cible Cible de l'attaque
     */
    public void attaquer(Attaquant cible);

    /**
     * Permet de determiner si l'attaquant est mort
     * @return True si l'attaquant est mort
     */
    public boolean etreMort();
    
    /**
     * Permet de retirer des points de vie
     * @param degats Points de vie a retirer
     */
    public void retirerVie(int degats);
    
    /**
     * Permet de subir des degats
     * @param vAttaque Valeur de degats de l'attaque subis
     * @return Degats retournes
     */
    public int subirDegats(int vAttaque);
}
