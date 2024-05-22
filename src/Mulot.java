import gui.GUISimulator;
import gui.Triangle;
import java.awt.Color;

public class Mulot extends Boid {
    static private float[] poids = {(float) 0.05, (float) 0.2, (float) 0.03};
    static private float m = 25;
    static private float distance = 40;
    static private float angle = 180;
    
    /**
     * calcul du pas suivant pour les mulots uniquement
     */
    static public void nextMulot() {
        for (Boid b : Boid.getBoids()) {
            if (b instanceof Mulot) {
                b.computeNext();
            }
            else {
                b.computeNextWithoutForce();
            }
        }
        for (Boid b : Boid.getBoids()) {
            b.applyNext();
        }
    }

    Mulot(float[] position, float[] vitesse) {
        super(position, vitesse, distance, angle);
    }

    /**
     * calul de la résultante avec les poids statiques
     */
    @Override
    public float[] resultante() {
        float[] resultat =  {0, 0};
        for (int i = 0; i < 2; i++) {
            resultat[i] += this.forcesExt[0][i]*poids[0];
            resultat[i] += this.forcesExt[1][i]*poids[1];
            resultat[i] += this.forcesExt[2][i]*poids[2];
        }
        resultat[0] = resultat[0] / m;
        resultat[1] = resultat[1] / m;
        return resultat;
    }

    /**
     * redéfinition du l'affichage pour représenter les mulots comme des petits triangles bleus
     */
    @Override
    public void afficherSimulateurBoid(GUISimulator fenetre) {
        int[] printedPosition = {Math.round(position[0]*fenetre.getPanelHeight()/virtualWindow[0])%fenetre.getPanelHeight(),
                                 Math.round(position[1]*fenetre.getPanelWidth()/virtualWindow[1])%fenetre.getPanelWidth()};
        float direction = (float) computeDirection() - (float) (Math.PI/2); // + Math.PI;
        fenetre.addGraphicalElement(new Triangle(printedPosition[0], printedPosition[1], direction, Color.BLUE, Color.BLUE, 10));
    }
}
