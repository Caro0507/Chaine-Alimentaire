import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Color;



/**
 * Jeu de la l'immigration
 */
public class Immigration extends Grille {
    public Immigration(int nombreLignes, int nombreColonnes) {
	super(nombreLignes, nombreColonnes);
	this.nombreEtats = nombreEtats;
	this.couleurs = couleurs;
    }

    protected void setCouleurs() {
    	Color[] couleurs = new Color[nombreEtats];
	for (int i = 0; i < nombreEtats; i ++) {
	    couleurs[i] = new Color((int)(Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255));
	}
	this.couleurs = couleurs;
    }

    
    /**
     * Cette méthode compte le nombre de cellules voisines à l'état
     * +1 (modulo n) de notre cellule en prenant en compte la
      * circularité de la grille
      * @param i  	Numéro de ligne de la cellule
      * @param j  	Numéro de colonne de la cellule
      * @return int  Le nombre de voisines à l'état +1 de la cellule
      */
    
    protected int getNombreVoisins(int i, int j) {
	int nombreVoisins = 0;
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] grille = new int[nombreLignes][nombreColonnes];
	grille = getGrille();
	int etat = grille[i][j];
	int etatSuiv = (etat + 1) % nombreEtats;
	
	if(grille[(i-1+nombreLignes) % nombreLignes][j] == etatSuiv) {
	    nombreVoisins ++;
	}
	    
	if (grille[(i-1+nombreLignes) % nombreLignes][(j-1+nombreColonnes) % nombreLignes] == etatSuiv) {
	    nombreVoisins ++;
	}

	if (grille[(i-1+nombreLignes) % nombreLignes][(j+1) % nombreColonnes] == etatSuiv) {
	    nombreVoisins ++;
	}
	
	if (grille[(i+1) % nombreLignes][j] == etatSuiv) {
	    nombreVoisins ++;
	}
	
	if (grille[(i+1) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] == etatSuiv) {
	    nombreVoisins ++;
	}
	
	if (grille[(i+1) % nombreLignes][(j+1) % nombreColonnes] == etatSuiv) {
	    nombreVoisins ++;
	}
	
	if (grille[i][(j-1+nombreColonnes) % nombreColonnes] == etatSuiv) {
	    nombreVoisins ++;
	}
		
	if (grille[i][(j+1) % nombreColonnes] == etatSuiv) {
	    nombreVoisins ++;
	}
	
	return nombreVoisins;
    }
	
	
    /**
     * Création et remplissage d'une nouvelle grille qui représente
     * l'état suivant, calculé à partir de l'état actuel et des règles
     * du jeu de l'immigration
     */
    
    public void etatSuivant() {
	int nombreVoisins;
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] nouvelleGrille = new int[nombreLignes][nombreColonnes];
	int[][] grille = new int[nombreLignes][nombreColonnes];
	grille = getGrille();
	for (int i = 0; i < nombreLignes; i += 1) {
	    for (int j = 0; j < nombreColonnes; j += 1) {
		nouvelleGrille[i][j] = grille[i][j];
		int etatSuiv = (grille[i][j] + 1) % nombreEtats;
		nombreVoisins = getNombreVoisins(i, j);
		if (nombreVoisins >= 3) {
		    nouvelleGrille[i][j] = etatSuiv;
		}
	    }
	}
	setGrille(nouvelleGrille);
    }
}
