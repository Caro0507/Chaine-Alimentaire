import java.util.ArrayList;
import java.util.Iterator;
import gui.GUISimulator;
import gui.Triangle;
import java.awt.Color;

abstract public class Boid {
    static private ArrayList<Boid> boids = new ArrayList<Boid>();
    protected float[] position = new float[2]; // x, y
    protected float[] nextPosition = new float[2];
    protected float[] originalPosition = new float[2];
    protected float[] vitesse = new float[2]; // x', y'
    protected float[] nextVitesse = new float[2];
    protected float[] originalVitesse = new float[2];
    protected float[][] forcesExt = new float[3][2];
    protected float[] forceResultante = {0, 0};
    protected float distance;
    protected float angle;
    protected float[] virtualWindow = {1000, 1000};

    // La liste des boids est stockée en statique, les méthodes sur l'ensemble des boids aussi
    static private void addBoid(Boid boid) {
        boids.add(boid);
    }

    static public void removeBoid(Boid boid) {
        boids.remove(boid);
    }

    static public ArrayList<Boid> getBoids() {
        return boids;
    }

    /**
     * Retourne la liste des voisins
     * @param requester
     * @return
     */
    static public ArrayList<Boid> boidsVoisins(Boid requester) {
        ArrayList<Boid> result = new ArrayList<Boid>();
        Iterator<Boid> iterator = boids.iterator();
        while (iterator.hasNext()) {
            Boid b = iterator.next();

            float[] offsets = {0, 0};
            offsets[0] = b.getPosition()[0] - requester.getPosition()[0];
            offsets[1] = b.getPosition()[1] - requester.getPosition()[1];

            float computed_distance = (float) Math.sqrt(offsets[0]*offsets[0] + offsets[1]*offsets[1]);

            // tentative d'implémentation du champs de vision, mais les résultats semblaient étranges
            // elle a donc été désactivée
            // float bAngle = b.computeDirection() - requester.computeDirection();
            //float bAngle = 0;
            // angle entre 2 vecteurs unitaires u,v : arcos (u.v)
            float bAngle = (float) Math.acos((b.computeDirectorVector()[0] * offsets[0] + b.computeDirectorVector()[1] * offsets[1])/computed_distance);
            if ((computed_distance <= requester.getDistanceVision()) && (Math.abs(bAngle) <= requester.angle) && (b != requester)) {
                result.add(b);
            }
        }

        return result;
    }

    static public void afficherSimulateur(GUISimulator gui) {
        for (Boid b : boids) {
            b.afficherSimulateurBoid(gui);
        }
    }

    /**
     * calcul un pas de simulation sur l'ensemble des boids
     */
    static public void next() {
        for (Boid b : boids) {
            b.computeNext();
        }
        for (Boid b : boids) {
            b.applyNext();
        }
    }

    /**
     * reinit tous les boids
     */
    static public void reInit() {
        for (Boid b : boids) {
            b.reInitBoid();
        }
    }

    Boid(float[] position, float[] vitesse, float distanceVision, float angleVision) {
        this.originalPosition[0] = position[0];
        this.originalPosition[1] = position[1];
        this.originalVitesse[0] = vitesse[0];
        this.originalVitesse[1] = vitesse[1];
        this.distance = distanceVision;
        this.angle = (float) Math.toRadians(angleVision);
        reInitBoid();
        addBoid(this);
    }

    /**
     * la fonction de calcule de la résultante est implémentée par les classes filles
     * @return valeur de la résultante
     */
    abstract float[] resultante();

    /**
     * restore la position et vitesse originale
     */
    private void reInitBoid() {
        this.position[0] = originalPosition[0];
        this.position[1] = originalPosition[1];
        this.vitesse[0] = originalVitesse[0];
        this.vitesse[1] = originalVitesse[1];
    }

    /**
     * calcul des 3 forces fondamentales
     */
    private void computeForces() {
        ArrayList<Boid> voisins = boidsVoisins(this);
        Iterator<Boid> iterator = voisins.iterator();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                forcesExt[i][j] = 0;
            }
        }
        float[][] temp = {{0, 0}, {0, 0}, {0, 0}};
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                Boid voisin = iterator.next();

                // cohesion
                temp[0][0] += voisin.position[0];
                temp[0][1] += voisin.position[1];

                // alignement
                temp[1][0] += voisin.vitesse[0];// /vitesseNorme;
                temp[1][1] += voisin.vitesse[1];// /vitesseNorme;

                // separation
                float x = (voisin.position[0] - this.position[0]);
                float y = (voisin.position[1] - this.position[1]);
                float norme = (float) Math.sqrt((x*x) + (y*y));
                temp[2][0] += (distance*x)/norme - x;
                temp[2][1] += (distance*y)/norme - y;
            }
            // cohesion
            forcesExt[0][0] = (temp[0][0] / voisins.size()) - this.position[0];
            forcesExt[0][1] = (temp[0][1] / voisins.size()) - this.position[1];

            // alignement
            float vitesseNorme = (float) Math.sqrt(vitesse[0]*vitesse[0] + vitesse[1]*vitesse[1]);
            float forceNorme = (float) Math.sqrt(temp[1][0]*temp[1][0] + temp[1][1]*temp[1][1]);
            temp[1][0] = temp[1][0] / forceNorme;
            temp[1][1] = temp[1][1] / forceNorme;
            forcesExt[1][0] = temp[1][0] - (vitesse[0]/vitesseNorme);
            forcesExt[1][1] = temp[1][1] - (vitesse[1]/vitesseNorme);

            // separation
            forcesExt[2][0] = -temp[2][0];
            forcesExt[2][1] = -temp[2][1];
        }
    }

    /**
     * calcul de la vitesse et position au prochain pas en tenant compte du calcul des forces
     */
    public void computeNext() {
        nextPosition = new float[2];
        nextVitesse = new float[2];
        computeForces();
        forceResultante = resultante();
        for (int i = 0; i < 2; i++) {
            nextVitesse[i] = vitesse[i] + forceResultante[i];
            nextPosition[i] = (position[i] + nextVitesse[i]) % virtualWindow[i];
            if (nextPosition[i] < 0) {
                nextPosition[i] = virtualWindow[i] - nextPosition[i];
            }
        }
    }

    /**
     * calcul de la vitesse et position au prochain pas sans tenir compte des forces
     */
    public void computeNextWithoutForce() {
        nextPosition = new float[2];
        nextVitesse = new float[2];
        for (int i = 0; i < 2; i++) {
            nextVitesse[i] = vitesse[i] + forceResultante[i];
            nextPosition[i] = (position[i] + nextVitesse[i]) % virtualWindow[i];
                if (nextPosition[i] < 0) {
                    nextPosition[i] = virtualWindow[i] - nextPosition[i];
                }
        }
    }

    /**
     * les valeurs de postion et vitesse futures deviennent les valeurs courantes
     */
    public void applyNext() {
        position = nextPosition;
        vitesse = nextVitesse;
        nextPosition = null;
        nextVitesse = null;
        forceResultante[0] = 0;
        forceResultante[1] = 0;
    }

    /**
     * calcule le vecteur directeur de la vitesse
     */
    private float[] computeDirectorVector() {
        float vitesseNorme = (float) Math.sqrt(vitesse[0]*vitesse[0] + vitesse[1]*vitesse[1]);
        float[] temp = {vitesse[0] / vitesseNorme, vitesse[1] / vitesseNorme};
        return temp;
    }

    /**
     * calcule la direction du boid
     * @return
     */
    protected float computeDirection() {
        float[] temp = computeDirectorVector();
        float alpha = (float) Math.acos(temp[0]);
        if (Math.asin(-temp[1]) < 0) {
            alpha = -alpha;
        }
        return (float) alpha;
    }

    /**
     * affiche de boid
     * @param fenetre
     */
    public void afficherSimulateurBoid(GUISimulator fenetre) {
        int[] printedPosition = {Math.round(position[0]*fenetre.getPanelHeight()/virtualWindow[0])%fenetre.getPanelHeight(),
                                 Math.round(position[1]*fenetre.getPanelWidth()/virtualWindow[1])%fenetre.getPanelWidth()};
        float direction = (float) computeDirection() - (float) (Math.PI/2); // + Math.PI;
        fenetre.addGraphicalElement(new Triangle(printedPosition[0], printedPosition[1], direction, Color.GRAY, Color.WHITE, 15));
    }

    // ensemble de getters
    float[] getPosition() {
        return position;
    }

    float[] getVitesse() {
        return vitesse;
    }

    float getDistanceVision() {
        return distance;
    }

    float getAngleVision() {
        return angle;
    }

    float[][] getForces() {
        return forcesExt;
    }
}
