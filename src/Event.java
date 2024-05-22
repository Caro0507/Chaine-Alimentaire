public abstract class Event implements Comparable<Event> {
    private long date;

    public Event(long date) {
	this.date = date;
    }

    public long getDate() {
	return date;
    }

    public abstract void execute();

    @Override
    public int compareTo(Event evenement) {
	if (this.date > evenement.date) {
	    return 1;
	}
	if (this.date < evenement.date) {
	    return -1;
	}
	return 0;
    }
}
