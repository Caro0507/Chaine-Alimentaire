public class TestEventManager {
    public static void main (String[] args) throws InterruptedException {
	// On crée un simulateur
	EventManager manager = new EventManager() ;

	// Le premier event exécuté est au temps 1
	
	// On poste un événement [PING] tous les pas de temps
	for (int i = 1 ; i <= 10 ; i += 1) {
	    manager.addEvent(new MessageEvent(i, " [PING]"));
	}
	// On poste un événement [PONG] tous les trois pas de temps
	for (int i = 1 ; i <= 9 ; i += 3) {
	    manager.addEvent(new MessageEvent(i, " [PONG]"));
	}

	while (!manager.isFinished()) {
			manager.next();
			Thread.sleep(1000);
		}
    }
}
