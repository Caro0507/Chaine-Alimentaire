import gui.GUISimulator;
import java.awt.Color;

public class TestImmigrationSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
        gui.setSimulable(new ImmigrationSimulator(gui));
    }
}
