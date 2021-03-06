package components;

public class DoubleSwingSite extends PlaySite {

    private int numberOfSwings;

    public DoubleSwingSite(int numberOfSwings) {
        this.numberOfSwings = numberOfSwings;
        // Each double swing in the site can hold two children
        this.capacity = this.numberOfSwings * 2;
    }

    public int getNumberOfSwings() {
        return numberOfSwings;
    }

    public void addSwing() {
        numberOfSwings += 1;
        capacity = numberOfSwings * 2;
    }

    public void addSwing(int swings) {
        numberOfSwings += swings;
        capacity = numberOfSwings * 2;
    }

    public void removeSwing() {
        if (numberOfSwings > 1) {
            numberOfSwings -= 1;
            capacity = numberOfSwings * 2;
        } else {
            numberOfSwings = 0;
            capacity = numberOfSwings * 2;
        }
    }

    public void removeSwing(int swings) {
        if (numberOfSwings >= swings) {
            numberOfSwings -= swings;
            capacity = numberOfSwings * 2;
        } else {
            numberOfSwings = 0;
            capacity = numberOfSwings * 2;
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
