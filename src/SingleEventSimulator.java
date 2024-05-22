import gui.GUISimulator;

/**
 * Classe de simulateur implémantant déjà de quoi gérer les simulations avec un seul type d'évênement
 */
abstract public class SingleEventSimulator extends AbstractSimulator {
    SingleEventSimulator(GUISimulator gui) {
        super(gui);
    }
    
    abstract void eventHandler();

    public void generateNextEvent(int delay) {
        events.addEvent(new SingleEvent(events.getCurrentDate() + delay, this));
	}

    @Override
	public void next() {
		events.next();
		generateNextEvent(1);
    }

    @Override
    public void restart() {
        events.restart();
        generateNextEvent(1);
        restartSimulation();
    }
}
