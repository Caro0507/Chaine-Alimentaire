import gui.GUISimulator;

public class VieSimulator extends SingleEventSimulator {
    Vie grilleVie;
    
    public VieSimulator(GUISimulator gui) {
        super(gui);
	Vie grilleVie = new Vie(50, 50);
	grilleVie.setEtats(2);
	this.grilleVie = grilleVie;
	grilleVie.afficherSimulateur(gui);
    }

    public void eventHandler() {
        grilleVie.etatSuivant();
        gui.reset();
        grilleVie.afficherSimulateur(gui);
    }

    @Override
    public void restartSimulation() {
	grilleVie.reInit();
	gui.reset();
        grilleVie.afficherSimulateur(gui);
    }
}
