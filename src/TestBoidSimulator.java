import gui.GUISimulator;
import java.awt.Color;

public class TestBoidSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(750, 750, Color.WHITE);
        gui.setSimulable(new BoidSimulator(gui));
    }
}
