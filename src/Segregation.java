import java.awt.Color;
import java.util.Queue;
import java.util.LinkedList;



/**
 * Modèle de Schelling
 */
public class Segregation extends Grille {
    private int seuilDemenagement;
    private Queue<Integer> habitationsVacantes;
    private Queue<Integer> habitationsVacantesOriginales;

    public Segregation(int nombreLignes, int nombreColonnes, int seuilDemenagement) {
        super(nombreLignes, nombreColonnes);
        this.seuilDemenagement = seuilDemenagement;
        this.habitationsVacantes = new LinkedList<Integer>();
	this.habitationsVacantesOriginales = new LinkedList<Integer>();
    }

    /**
     * Place dans la grille un état initial aléatoire avec une
     * répartition homogène des états possibles dans la grille
     * (état 0 compris)
     */
    @Override
    public void setEtats(int nombreEtats) {
        setNombreEtats(nombreEtats + 1);
        setCouleurs();
        int nombreLignes = getNombreLignes();
        int nombreColonnes = getNombreColonnes();
        int[][] grille = new int[nombreLignes][nombreColonnes];
	int pos;
        for (int i = 0; i < nombreLignes; i += 1) {
            for (int j = 0; j < nombreColonnes; j += 1) {
            	grille[i][j] = (int)(Math.random() * this.nombreEtats);
		grilleOriginale[i][j] = grille[i][j];
            	if (grille[i][j] == 0) {
                    pos = nombreLignes * i + j;
            	    habitationsVacantes.add(pos);
		    habitationsVacantesOriginales.add(pos);
                }
            }
        }
	setGrille(grille);
    }




    /**
    * Cette méthode ajoute des couleurs randoms à la liste de couleurs
    * qui seront afficher, la première est blanche (pour les cellules vides).
    * Il y a nombreEtats de coleurs
    */
    protected void setCouleurs() {
     	Color[] couleurs = new Color[nombreEtats];
     	couleurs[0] = Color.WHITE;
     	for (int i = 1; i < nombreEtats; i ++) {
     	    couleurs[i] = new Color((int)(Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255));
     	}
     	this.couleurs = couleurs;
    }




    /**
    * Cette méthode compte le nombre de cellules voisines à un état
    * différent de notre cellule (état 0 non compris) en prenant en
    * compte la circularité de la grille
    * @param i  	Numéro de ligne de la cellule
    * @param j  	Numéro de colonne de la cellule
    * @return int  Le nombre de voisines à l'état un état différent
    * 				de la cellule
    */
    protected int getNombreVoisins(int i, int j) {
     	int nombreVoisins = 0;
     	int nombreLignes = getNombreLignes();
     	int nombreColonnes = getNombreColonnes();
     	int[][] grille = new int[nombreLignes][nombreColonnes];
     	grille = getGrille();
     	int etat = grille[i][j];

	if(grille[(i-1+nombreLignes) % nombreLignes][j] != etat && grille[(i-1+nombreLignes) % nombreLignes][j] != 0) {
	    nombreVoisins ++;
	}

	if (grille[(i-1+nombreLignes) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] != etat && grille[(i-1+nombreLignes) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
	
	if (grille[(i-1+nombreLignes) % nombreLignes][(j+1) % nombreColonnes] != etat && grille[(i-1+nombreLignes) % nombreLignes][(j+1) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
	
	if (grille[(i+1) % nombreLignes][j] != etat && grille[(i+1) % nombreColonnes][j] != 0) {
	    nombreVoisins ++;
	}

	if (grille[(i+1) % nombreLignes][(j-1+nombreColonnes) % nombreColonnes] != etat && grille[(i+1) % nombreColonnes][(j-1+nombreColonnes) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
	
	if (grille[(i+1) % nombreLignes][(j+1) % nombreColonnes] != etat && grille[(i+1) % nombreColonnes][(j+1) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
	
	if (grille[i][(j-1+nombreColonnes) % nombreColonnes] != etat && grille[i][(j-1+nombreColonnes) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
	
	if (grille[i][(j+1) % nombreColonnes] != etat && grille[i][(j+1) % nombreColonnes] != 0) {
	    nombreVoisins ++;
	}
        
        return nombreVoisins;
    }




    /**
    * Création et remplissage d'une nouvelle grille qui représente
    * l'état suivant, calculé à partir de l'état actuel et des règles
    * du modèle de Schelling
    */
    public void etatSuivant() {
    	int nombreVoisins;

    	int nombreLignes = getNombreLignes();
    	int nombreColonnes = getNombreColonnes();

    	int[][] nouvelleGrille = new int[nombreLignes][nombreColonnes];
    	int[][] grille = new int[nombreLignes][nombreColonnes];

    	grille = getGrille();
    	int nouvI;
    	int nouvJ;
    	int pos;
    	int etat;

    	for (int i = 0; i < nombreLignes; i += 1) {
    	    for (int j = 0; j < nombreColonnes; j += 1) {
        		etat = grille[i][j];
        		nombreVoisins = getNombreVoisins(i, j);
        		if (etat != 0) {
        		    nouvelleGrille[i][j] = etat;
        		    if (nombreVoisins >= seuilDemenagement) {
            			nouvelleGrille[i][j] = 0;
            			pos = habitationsVacantes.remove();
            			nouvI = (int)(pos / nombreLignes);
            			nouvJ = pos - nouvI * nombreLignes;
            			nouvelleGrille[nouvI][nouvJ] = etat;
            			habitationsVacantes.add(i * nombreLignes + j);
        		    }
                }
    	    }
    	}
	setGrille(nouvelleGrille);
    }

    public void reInit() {
	int[][] grille = new int[getNombreLignes()][getNombreColonnes()];
	for (int i = 0; i < getNombreLignes(); i += 1) {
	    for (int j = 0; j < getNombreColonnes(); j += 1) {
		grille[i][j] = grilleOriginale[i][j];
	    }
	}
	setGrille(grille);
	habitationsVacantes = new LinkedList<Integer>();
	for (int i = 0; i < habitationsVacantesOriginales.size(); i++) {
	    int habitation = habitationsVacantesOriginales.remove();
	    habitationsVacantes.add(habitation);
	    habitationsVacantesOriginales.add(habitation);
	}
	this.habitationsVacantes = habitationsVacantes;
    }
}

