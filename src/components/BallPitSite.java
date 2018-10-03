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

    @Override
    public double getCurrentUtilizationStat() {
        return kidsOnSite.size() * 100.0 / getCapacity();
    }

    @Override
    public double getUtilizationSnapShot(long start, long end) {
        return getVisitors(start, end).size() * 100.0 / getCapacity();
    }
}
