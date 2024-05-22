import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;

/**
 * Grille en 2 dimensions qui prend en compte la circularité de la grille :
 * 		le bord droit et gauche sont reliés
 * 		le bord du supérieur et le bord du inférieur le sont aussi
 */
public abstract class Grille {
    private int nombreLignes;
    private int nombreColonnes;
    private int[][] grille;
    protected int[][] grilleOriginale;
    protected int nombreEtats;
    protected Color[] couleurs;
    

    public Grille(int nombreLignes, int nombreColonnes) {
	this.nombreLignes = nombreLignes;
	this.nombreColonnes = nombreColonnes;
	this.grille = new int[nombreLignes][nombreColonnes];
        this.grilleOriginale = new int[nombreLignes][nombreColonnes];
    }
    
    // Getters
    public int getNombreLignes() {
	return nombreLignes;
    }
    
    public int getNombreColonnes() {
	return nombreColonnes;
    }
    
    public int[][] getGrille() {
	return grille;
    }
    
    // Setters
    public void setGrille(int[][] grille) {
	this.grille = grille;
    }
    
    public void setEtats(int nombreEtats) {
	setNombreEtats(nombreEtats);
	setCouleurs();
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] grille = new int[nombreLignes][nombreColonnes];
	grille = getGrille();
	for (int i = 0; i < nombreLignes; i += 1) {
	    for (int j = 0; j < nombreColonnes; j += 1) {
		grille[i][j] = (int)(Math.random() * nombreEtats);
		grilleOriginale[i][j] = grille[i][j];
	    }
	}
    }
    
    public void setNombreEtats(int nombreEtats){
	this.nombreEtats = nombreEtats;
    }
    
    protected abstract void setCouleurs();
    
    protected abstract int getNombreVoisins(int i, int j);
    
    public abstract void etatSuivant();

      public void reInit() {
	int[][] grille = new int[nombreLignes][nombreColonnes];
	for (int i = 0; i < nombreColonnes; i += 1) {
	    for (int j = 0; j < nombreColonnes; j += 1) {
		grille[i][j] = grilleOriginale[i][j];
	    }
	}
	this.grille = grille;
    }
    
    /**
     * Simulation graphique des différents jeux. Les cellules des différents
     * états sont dans des couleurs différents.
     */
    public void afficherSimulateur(GUISimulator fenetre) {
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] grille = new int[nombreLignes][nombreColonnes];
	grille = getGrille();
       	for (int i = 0; i < nombreLignes; i += 1)  {
	    for (int j = 0; j < nombreColonnes; j += 1)  {
		fenetre.addGraphicalElement(new Rectangle(5+10*j, 5+10*i, Color.GRAY, couleurs[grille[i][j]], 10));
	    }
	}
    }
}
