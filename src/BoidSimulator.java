import gui.GUISimulator;

public class BoidSimulator extends SingleEventSimulator {
    public BoidSimulator(GUISimulator gui) {
	super(gui);
	
	// récupération de la taille de la fenêtre
        int largeur = gui.getWidth();
        int hauteur = gui.getHeight();
	
	// ajout de 120 boid avec un position et vitesse aléatoire
        for (int i = 0; i < 120; i++) {
            float[] boidPosition = {(float) Math.random()*hauteur, (float) Math.random()*largeur};
            float[] boidVitesse = {(float) Math.random()*6 - 3, (float) Math.random()*6 - 3};
            float[] poids = {0, 0, (float) 0.04, (float) 0.2, (float) 0.03};
            new SimpleBoid(boidPosition, boidVitesse, 50, 180, poids, 20);
        }
	
	// affichage
	Boid.afficherSimulateur(gui);
    }
    
    @Override
    public void eventHandler() {
	Boid.next();
	gui.reset();
	Boid.afficherSimulateur(gui);
    }
    
    @Override
    public void restartSimulation() {
	Boid.reInit();
	gui.reset();
	Boid.afficherSimulateur(gui);
    }
}

