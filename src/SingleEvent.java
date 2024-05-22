/**
 * classe d'event très simple pour les simulations n'ayant qu'un seul type d'évênement 
 */
public class SingleEvent extends Event {
    private SingleEventSimulator simulator;    

    public SingleEvent(long date, SingleEventSimulator simulator) {
        super(date) ;
        this.simulator = simulator;
    }
    
    public void execute () {
        simulator.eventHandler();
    }
}
