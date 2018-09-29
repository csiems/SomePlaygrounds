import java.util.ArrayList;

public class Playground {
    private ArrayList<PlaySite> playSites;
    private int capacity;

    public Playground() {
        playSites = new ArrayList<>();
        capacity = 0;
    }

    public void addPlaySite(PlaySite playSite) {
        if (!playSites.contains(playSite)) {
            playSites.add(playSite);
            capacity += playSite.getCapacity();
        }
    }

    public int getCapacity() {
        return capacity;
    }

}
