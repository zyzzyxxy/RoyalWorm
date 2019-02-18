import java.util.HashMap;

public class BoostManager {
	private HashMap<Position, Boost> boosts;

	public BoostManager() {
		boosts = new HashMap<Position, Boost>();
	}
	
	public void spawn(Position key, Boost value) {
		boosts.put(key, value);
	}
	
	public void delete(Position key) {
		boosts.remove(key);
	}
	
	public void clearAllBoosts() {
		boosts.clear();
	}
}