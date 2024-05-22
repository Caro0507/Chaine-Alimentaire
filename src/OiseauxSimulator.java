import java.util.LinkedList;
import gui.GUISimulator;

/**
 * simulateur de test pour afficher un oiseau qui tourne en rond, ce n'est pas un boid
 */
public class OiseauxSimulator extends SingleEventSimulator {
    Oiseaux oiseaux;
	
    public OiseauxSimulator(GUISimulator gui) {
	super(gui);
	oiseaux = new Oiseaux();
	LinkedList<Double> oiseau = new LinkedList<Double>();
	oiseau.add((double) 250);
	oiseau.add((double) 250);
	oiseau.add((double) 0);
	oiseaux.ajouterOiseau(oiseau);
	oiseaux.afficherSimulateur(gui);
    }
    
    /**
     * traitant d'événement
     */
    public void eventHandler() {
	double angle = oiseaux.getAngle();
	oiseaux.translate(-10 * Math.sin(angle), -10 * Math.cos(angle), Math.toRadians(10));
	gui.reset();
	oiseaux.afficherSimulateur(gui);
    }
    
    /**
     * implémentation du redémarrage de la simulation
     */
    @Override
    public void restartSimulation() {
	oiseaux.reInit();
	gui.reset();
	oiseaux.afficherSimulateur(gui);
    }
}
