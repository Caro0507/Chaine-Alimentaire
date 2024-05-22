import java.util.Queue;
import java.util.PriorityQueue;

public class EventManager {
    private long currentDate;
    private Queue<Event> evenements;
    private Queue<Event> evenementsExecutes;
     
    public EventManager() {
	this.evenements = new PriorityQueue<Event>();
	this.evenementsExecutes = new PriorityQueue<Event>(); 
	this.currentDate = 0;
    }

    public void addEvent(Event evenement) {
		evenements.add(evenement);
    }

	public long getCurrentDate() {
		return currentDate;
	}

    public void next() {
		this.currentDate += 1;
		/*Event tete = evenements.peek();
		while ((evenements.size() != 0) && (tete.getDate() == currentDate)) {
			tete = evenements.peek();
			tete.execute();
			evenementsExecutes.add(evenements.remove());
			if (evenements.size() != 0) {
				tete = evenements.peek();
			}
		}*/
		while ((!isFinished()) && (evenements.peek().getDate() == currentDate)) {
			Event tete = evenements.peek();
			tete.execute();
			//evenementsExecutes.add(evenements.remove());
			evenements.remove();
		}
    }
    
    public boolean isFinished() {
	if (evenements.size() == 0) {
	    return true;
	}
	return false;
    }

    public void restart() {
		while (evenementsExecutes.size() != 0) {
			evenements.add(evenementsExecutes.remove());
		}
		currentDate = 0;
    }
}
