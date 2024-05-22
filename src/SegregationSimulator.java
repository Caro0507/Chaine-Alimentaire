import gui.GUISimulator;

public class SegregationSimulator extends SingleEventSimulator {
    Segregation grilleSegregation;

    public SegregationSimulator(GUISimulator gui) {
	super(gui);
	grilleSegregation = new Segregation(40, 40, 5);
	grilleSegregation.setEtats(3);
	grilleSegregation.afficherSimulateur(gui);
    }
    
    public void eventHandler() {
	grilleSegregation.etatSuivant();
	gui.reset();
	grilleSegregation.afficherSimulateur(gui);
    }

    @Override
    public void restartSimulation() {
	grilleSegregation.reInit();
	gui.reset();
	grilleSegregation.afficherSimulateur(gui);
    }
}
