import java.util.ArrayList;
import java.awt.Point;
import gui.GUISimulator;
import gui.Oval;
import java.awt.Color;

public class Balls {
    private ArrayList<Point> listeBalles;
    private ArrayList<Point> originalListeBalles;
    private ArrayList<Point> translationMultipliers;
    private int nombreBalles;
    private int[] guiSize = new int[2];
    
    public Balls(int[] guiSize, ArrayList<Point> listeBalles) {
	this.listeBalles = new ArrayList<Point>();
	this.listeBalles.addAll(listeBalles);
	originalListeBalles = new ArrayList<Point>();
	translationMultipliers = new ArrayList<Point>();
	for (int i = 0; i < listeBalles.size(); i++) {
	    originalListeBalles.add(new Point((int)(listeBalles.get(i).getX()), (int)(listeBalles.get(i).getY())));
	    translationMultipliers.add(new Point(1, 1));
	}
	this.nombreBalles = listeBalles.size();
	this.guiSize = guiSize;
    }
    
    public void ajouterBalle(Point balle) {
	listeBalles.add(balle);
	translationMultipliers.add(new Point(1, 1));
	nombreBalles += 1;
    }
    
    public void enleverBalle(int position) {
	if (nombreBalles <= position) {
	    throw new NullPointerException("Nombre de balles inférieur à cette position");
	}
	listeBalles.remove(position);
	translationMultipliers.remove(position);
	nombreBalles -= 1;
    }
    
    public void translate(int dx, int dy) {
	Point balle;
	for (int i = 0; i < nombreBalles; i++) {
	    balle = listeBalles.get(i);
	    balle.translate(dx*translationMultipliers.get(i).x, dy*translationMultipliers.get(i).y);
	    
	    if (balle.x < 0) {
		balle.x = -balle.x;
		translationMultipliers.get(i).x = -1*translationMultipliers.get(i).x;
	    }
	    else if (balle.x >= guiSize[0]) {
		balle.x = guiSize[0] - (balle.x-guiSize[0]);
		translationMultipliers.get(i).x = -1*translationMultipliers.get(i).x;
	    }
	    
	    if (balle.y < 0) {
		balle.y = -balle.y;
		translationMultipliers.get(i).y = -1*translationMultipliers.get(i).y;
	    }
	    else if (balle.y >= guiSize[1]) {
		balle.y = guiSize[1] - (balle.y - guiSize[1]);
		translationMultipliers.get(i).y = -1*translationMultipliers.get(i).y;
	    }
	}
    }
    
    public void reInit() {
	listeBalles = new ArrayList<Point>();
	nombreBalles = originalListeBalles.size();
	listeBalles.addAll(originalListeBalles);
	translationMultipliers = new ArrayList<Point>();
	for (int i = 0; i < listeBalles.size(); i++) {
	    translationMultipliers.add(new Point(1, 1));
	}
	this.listeBalles = listeBalles;
    }
    
    public void afficherSimulateur(GUISimulator fenetre) {
	Point balle;
	for (int i = 0; i < nombreBalles; i++) {
	    balle = listeBalles.get(i);
	    fenetre.addGraphicalElement(new Oval((int)balle.getX(), (int)balle.getY(), Color.YELLOW, Color.YELLOW, 10));
	}
    }
    
    public String toString() {
	String s = "Liste des balles :";
	Point balle;
	for (int i = 0; i < nombreBalles; i++) {
	    balle = listeBalles.get(i);
	    s += "\n(" + balle.getX() + ", " + balle.getY() + ")";
	}
	s += "\n";
	return s;
    }
}
