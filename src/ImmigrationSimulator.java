import gui.GUISimulator;

public class ImmigrationSimulator extends SingleEventSimulator {
    private Immigration grilleImmigration;
    
    public ImmigrationSimulator(GUISimulator gui) {
	super(gui);
	grilleImmigration = new Immigration(40, 40);
	grilleImmigration.setEtats(3);
	this.grilleImmigration = grilleImmigration;
	grilleImmigration.afficherSimulateur(gui);
    }

    public void eventHandler() {
	grilleImmigration.etatSuivant();
	gui.reset();
	grilleImmigration.afficherSimulateur(gui);
    }

    @Override
    public void restartSimulation() {
	grilleImmigration.reInit();
	gui.reset();
	grilleImmigration.afficherSimulateur(gui);
    }
}
