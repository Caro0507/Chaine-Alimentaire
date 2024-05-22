import gui.GUISimulator;
import java.awt.Color;

public class TestSegregationSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
        gui.setSimulable(new SegregationSimulator(gui));
    }
}
