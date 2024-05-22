import java.awt.Point;
import java.util.ArrayList;

public class TestBalls {
    public static void main(String[] args) {
	Point balle1 = new Point(40, 50);
	Point balle2 = new Point(400, 100);
	Point balle3 = new Point(100, 400);
	int[] virtualGUISize = {1000, 1000};
	ArrayList<Point> listeBalles = new ArrayList<Point>();
	listeBalles.add(balle1);
	listeBalles.add(balle2);
	listeBalles.add(balle3);
	
	System.out.print("Test de l'initialisation d'un ensemble de balles : \n");
	Balls balles = new Balls(virtualGUISize, listeBalles);
	System.out.println(balles);

	System.out.print("Test de l'enlèvement de la balle à la deuxième position :\n");
	balles.enleverBalle(1);
	System.out.println(balles);

	System.out.print("Test de l'enlèvement de la balle à la cinquième position :\n");
	try {
	    balles.enleverBalle(5);
	}
	catch (NullPointerException e) {
	    System.out.println(e + "\n");
	}
	
	System.out.print("Test de la translation de l'ensemble de balles :\n");
	balles.translate(4, 5);
	System.out.println(balles);

	System.out.print("Test de la réinitialisation de l'ensemble de balles :\n");
	balles.reInit();
	System.out.println(balles);
    }
}
