import java.util.LinkedList;
import gui.GUISimulator;
import gui.Triangle;
import java.awt.Color;

/**
 * classe de test, ce n'est pas encore un boid
 */
public class Oiseaux {
    private LinkedList<LinkedList<Double>> listeOiseaux;
    private int nombreOiseaux;
    private double sommeTranslationsX;
    private double sommeTranslationsY;
    private double sommeRotations;

    public Oiseaux() {
	LinkedList<LinkedList<Double>> liste = new LinkedList<LinkedList<Double>>();
	this.listeOiseaux = liste;
	this.nombreOiseaux = 0;
	this.sommeTranslationsX = 0;
	this.sommeTranslationsY = 0;
	this.sommeRotations = 0;
    }

    public double getAngle() {
	return listeOiseaux.get(0).get(2);
    }

    public void ajouterOiseau(LinkedList<Double> oiseau) {
	listeOiseaux.add(oiseau);
	nombreOiseaux += 1;
    }

    public void enleverBalle(int position) {
	if (nombreOiseaux <= position) {
	    throw new NullPointerException("Nombre d'oiseaux inférieur à cette position");
	}
	listeOiseaux.remove(position);
	nombreOiseaux -= 1;
    }
    
    public void translate(double dx, double dy, double dAngle) {
	double x;
	double y;
	double angle;
	for (int i = 0; i < nombreOiseaux; i++) {
	    x = listeOiseaux.get(i).get(0);
	    y = listeOiseaux.get(i).get(1);
	    angle = listeOiseaux.get(i).get(2);
	    listeOiseaux.get(i).remove(0);
	    listeOiseaux.get(i).remove(0);
	    listeOiseaux.get(i).remove(0);
	    listeOiseaux.get(i).add(x + dx);
	    listeOiseaux.get(i).add(y + dy);
	    listeOiseaux.get(i).add(angle + dAngle);
	    sommeTranslationsX += dx;
	    sommeTranslationsY += dy;
	    sommeRotations += dAngle;
	}
    }
    
    public void reInit() {
		this.translate(-sommeTranslationsX, -sommeTranslationsY, -sommeRotations);
    }

    public void afficherSimulateur(GUISimulator fenetre) {
		LinkedList<Double> oiseau;
		int x;
		int y;
		for (int i = 0; i < nombreOiseaux; i++) {
			oiseau = listeOiseaux.get(i);
			x = oiseau.get(0).intValue();
			y = oiseau.get(1).intValue();
			fenetre.addGraphicalElement(new Triangle(x, y, oiseau.get(2), Color.GRAY, Color.WHITE, 15));
		}
    }
}
