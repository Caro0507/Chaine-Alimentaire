import java.awt.Point;
import gui.GUISimulator;
import java.util.ArrayList;

public class BallsSimulator extends SingleEventSimulator {
    private Balls balles;

    public BallsSimulator(GUISimulator gui) {
	super(gui);
	ArrayList<Point> listeBalles = new ArrayList<Point>();
	int[] guiSize = {gui.getPanelHeight(), gui.getPanelWidth()};
	Point balle1 = new Point(10, 10);
	Point balle2 = new Point(10, 50);
	Point balle3 = new Point(50, 10);
	listeBalles.add(balle1);
	listeBalles.add(balle2);
	listeBalles.add(balle3);
	balles = new Balls(guiSize, listeBalles);
	balles.afficherSimulateur(gui);
    }

    public void eventHandler() {
	balles.translate(10, 10);
	gui.reset();
	balles.afficherSimulateur(gui);
    }

    @Override
    public void restartSimulation() {
        balles.reInit();
	gui.reset();
	balles.afficherSimulateur(gui);
    }
}
