/**
 * évênement relatif à la simulation Faucons et mulots
 */
public class FauconMulotEvent extends Event {
    private FauconsMulotSimulator simulator;    
    int eventType; // stocke le type d'évênement

    public FauconMulotEvent(long date, int eventType, FauconsMulotSimulator simulator) {
        super(date);
        this.eventType = eventType;
        this.simulator = simulator;
    }
    
    /**
     * exécute l'évênement dépendamment de son type
     */
    public void execute () {
        switch(eventType) {
            case 0: // tout
                simulator.AllEventHandler();
                break;
            case 1: // mulot only
                simulator.MulotEventHandler();
                break;
            case 2: // faucon only
                simulator.FauconEventHandler();
                break;
        }
    }
}
