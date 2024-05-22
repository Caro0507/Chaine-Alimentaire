import gui.GUISimulator;

/**
 * Simulation de mulots pourchasés par des faucons
 */
public class FauconsMulotSimulator extends AbstractSimulator {
    public FauconsMulotSimulator(GUISimulator gui) {
	super(gui);

        // récupération de la taille de la fenêtre
        int largeur = gui.getWidth();
        int hauteur = gui.getHeight();

        // création de 100 mulots à position et vitesse aléatoire
        for (int i = 0; i < 100; i++) {
            float[] boidPosition = {(float) Math.random()*hauteur, (float) Math.random()*largeur};
            float[] boidVitesse = {(float) Math.random()*6 - 3, (float) Math.random()*6 - 3};
            new Mulot(boidPosition, boidVitesse);
        }
	
        // création de 10 faucons à position et vitesse aléatoire (mais plus grande que les mulots) 
        for (int i = 0; i < 10; i++) {
            float[] boidPosition = {(float) Math.random()*hauteur, (float) Math.random()*largeur};
            float[] boidVitesse = {(float) Math.random()*10 - 5, (float) Math.random()*10 - 5};
            new Faucon(boidPosition, boidVitesse);
        }
	
        // affichage
	Boid.afficherSimulateur(gui);

	restart();
    }
    
    /**
     * traitant d'évênement qui simule un pas pour tous les boids
     */
    public void AllEventHandler() {
	Boid.next();
	gui.reset();
	Boid.afficherSimulateur(gui);
    }
    
    /**
     * traiant d'évênement qui simule un pas pour tous les faucons
     */
    public void FauconEventHandler() {
	Faucon.nextFaucon();
	gui.reset();
	Boid.afficherSimulateur(gui);
	generateNextFauconEvent(2);
    }
    
    /**
     * traitant d'évênement qui simule un pas pout tous les mulots
     */
    public void MulotEventHandler() {
	Mulot.nextMulot();
	gui.reset();
	Boid.afficherSimulateur(gui);
        generateNextMulotEvent(1);
    }
    
    /**
     * création un nouvelle évênement global (FauconMulotEvent.eventType = 0)
     */
    public void generateNextEvent(int delay) {
        events.addEvent(new FauconMulotEvent(events.getCurrentDate() + delay, 0, this));
    }
    
    /**
     * création un nouvelle évênement faucon (FauconMulotEvent.eventType = 1)
     */
    private void generateNextFauconEvent(int delay) {
        events.addEvent(new FauconMulotEvent(events.getCurrentDate() + delay, 1, this));
    }
    
    /**
     * création un nouvelle évênement mulot (FauconMulotEvent.eventType = 2)
     */
    private void generateNextMulotEvent(int delay) {
        events.addEvent(new FauconMulotEvent(events.getCurrentDate() + delay, 2, this));
    }
    
    /**
     * Redéfinition de restart avec les évênements particulier de cette simulation
     */
    @Override
    public void restart() {
        events.restart();
        generateNextMulotEvent(1);
        generateNextFauconEvent(1);
        restartSimulation();
    }
    
    /**
     * implémentation de restartSimulation
     */
    @Override
    public void restartSimulation() {
	Boid.reInit();
	gui.reset();
	Boid.afficherSimulateur(gui);
    }
}
