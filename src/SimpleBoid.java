public class SimpleBoid extends Boid {
    private float[] poids = new float[5];
    private float m;

    // cette classe de boid est génrique, les poids sont passés en paramêtre par le simulateur

    SimpleBoid(float[] position, float[] vitesse, float distanceVision, float angleVision, float[] poids, float m) {
        super(position, vitesse, distanceVision, angleVision);
        for (int i = 0; i < 5; i++) {
            this.poids[i] = poids[i];
        }
        this.m = m;
    }

    @Override
    public float[] resultante() {
        float[] resultat =  {0, 0};
        for (int i = 0; i < 2; i++) {
            resultat[i] += this.position[i]*poids[0];
            resultat[i] += this.vitesse[i]*poids[1];
            resultat[i] += this.forcesExt[0][i]*poids[2];
            resultat[i] += this.forcesExt[1][i]*poids[3];
            resultat[i] += this.forcesExt[2][i]*poids[4];
        }
        resultat[0] = resultat[0] / m;
        resultat[1] = resultat[1] / m;
        return resultat;
    }
}
