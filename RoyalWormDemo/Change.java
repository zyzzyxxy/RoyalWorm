public class Change extends BoardCordinates {
    private GameObject object;
    public Change(int x, int y, GameObject object) {
        super(x, y);
        this.setObject(object);
    }
    
	public GameObject getObject() {
		return object;
	}
	
	public void setObject(GameObject object) {
		this.object = object;
	}
}
