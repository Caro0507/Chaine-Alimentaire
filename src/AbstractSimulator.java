import gui.GUISimulator;

abstract public class AbstractSimulator implements gui.Simulable {
    protected GUISimulator gui;
	protected EventManager events = new EventManager();

    AbstractSimulator(GUISimulator gui) {
        this.gui = gui;
    }

    public GUISimulator getGUI() {
        return gui;
    }

    @Override
	public void next() {
	events.next();
    }

    @Override
    public void restart() {
        events.restart();
        restartSimulation();
    }

    public abstract void restartSimulation();
}
