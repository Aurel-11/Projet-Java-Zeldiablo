package moteurJeu.moteur;

import javax.swing.JFrame;


/**
 * cree une interface graphique avec son controleur et son afficheur
 * @author Graou
 *
 */
public class InterfaceGraphique  {

	
	/**
	 * le Panel lie a la JFrame
	 */
	private PanelDessin panel;
	
	/**
	 * le controleur lie a la JFrame
	 */
	CClavier clavier;
	CSouris souris;

	
	/**
	 * la construction de l'interface grpahique
	 * - construit la JFrame
	 * - construit les Attributs
	 * 
	 * @param afficheurUtil l'afficheur a utiliser dans le moteur
	 * 
	 */
	public InterfaceGraphique(DessinAbstract afficheurUtil,int x,int y)
	{
		//creation JFrame
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// creation panel
		this.panel=new PanelDessin(x, y,afficheurUtil);
		f.setContentPane(this.panel);
		
		//ajoute la souris au panel
		this.souris=new CSouris();
		this.panel.addMouseListener(souris);
		this.panel.addMouseMotionListener(souris);
		
		
		//ajout du controleur
		CClavier controlleurGraph=new CClavier();
		this.clavier=controlleurGraph;
		this.panel.addKeyListener(controlleurGraph);	
		
		//recuperation du focus
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}
	
	
	/**
	 * demande la mise a jour du dessin
	 */
	public void dessiner() {
		this.panel.dessinerJeu();	
	}

	/**
	 * retourne le controleur de l'affichage construit
	 * @return
	 */
	public CClavier getControleur() {
		return clavier;
	}
	
}
