import java.awt.Color;

/**
 * Jeu de la vie de Conway
 */
public class Vie extends Grille {
    
    public Vie(int nombreLignes, int nombreColonnes) {
	super(nombreLignes, nombreColonnes);
    }
    
    public void setCouleurs() {
	Color[] couleurs = new Color[nombreEtats];
	couleurs[0] = Color.WHITE;
	for (int i = 1; i < nombreEtats; i ++) {
	    couleurs[i] = new Color((int)(Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255));
	}
	this.couleurs = couleurs;
    }
    
    
    /**
     * Création et remplissage d'une nouvelle grille qui représente
     * l'état suivant, calculé à partir de l'état actuel et des règles
     * du jeu de la vie
     */
    public void etatSuivant() {
	int nombreVoisins;
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] nouvelleGrille = new int[nombreLignes][nombreColonnes];
	int[][] grille = getGrille();
	for (int i = 0; i < nombreLignes; i += 1) {
	    for (int j = 0; j < nombreColonnes; j += 1) {
		nouvelleGrille[i][j] = grille[i][j];
		nombreVoisins = getNombreVoisins(i, j);
		if (grille[i][j] == 1) {
		    if (nombreVoisins != 2 && nombreVoisins != 3) {
			nouvelleGrille[i][j] = 0;
		    }
		}
		else {
		    if (nombreVoisins == 3) {
			nouvelleGrille[i][j] = 1;
		    }
		}
	    }
	}
	setGrille(nouvelleGrille);
    }

    protected int getNombreVoisins(int i, int j) {
	int nombreVoisins = 0;
	int nombreLignes = getNombreLignes();
	int nombreColonnes = getNombreColonnes();
	int[][] grille = new int[nombreLignes][nombreColonnes];
	grille = getGrille();
	if(grille[(i-1+nombreLignes) % nombreLignes][j] == 1) {
		nombreVoisins ++;
	}
	if (grille[(i-1+nombreLignes) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	if (grille[(i-1+nombreLignes) % nombreLignes][(j+1) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	if (grille[(i+1) % nombreLignes][j] == 1) {
	    nombreVoisins ++;
	}
	if (grille[(i+1) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	if (grille[(i+1) % nombreLignes][(j+1) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	if (grille[i][(j-1+nombreColonnes) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	if (grille[i][(j+1) % nombreColonnes] == 1) {
	    nombreVoisins ++;
	}
	
	return nombreVoisins;
    }
}
