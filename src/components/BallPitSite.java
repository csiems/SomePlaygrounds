package components;

public class BallPitSite extends PlaySite {

    public BallPitSite(int capacity) {
        this.capacity = capacity;
    }

    public void addCapacity(int additionalCapacity) {
        capacity += additionalCapacity;
    }

    public void removeCapacity(int removedCapacity) {
        if (capacity >= removedCapacity) {
            capacity -= removedCapacity;
        } else {
            capacity = 0;
        }
    }
}
