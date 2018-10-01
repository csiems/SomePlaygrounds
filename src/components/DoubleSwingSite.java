package components;

import utils.Kid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    }

    public void addSwing(int swings) {
        numberOfSwings += swings;
    }

    public void removeSwing() {
        if (numberOfSwings > 1) {
            numberOfSwings -= 1;
        } else {
            numberOfSwings = 0;
        }
    }

    public void removeSwing(int swings) {
        if (numberOfSwings >= swings) {
            numberOfSwings -= swings;
        } else {
            numberOfSwings = 0;
        }
    }

    @Override
    public int getCapacity() {
        // Each double swing in the site can hold two children
        return numberOfSwings * 2;
    }

    @Override
    public double getCurrentUtilizationStat() {
        return kidsOnSite.size() * 100.0 / capacity;
    }

    @Override
    public double getUtilizationSnapShot(long start, long end) {
        List<Kid> tempList = new ArrayList<>();
        for (Map.Entry<Long, List<Kid>> entry : getVisitors(start, end).entrySet()) {
            tempList.addAll(entry.getValue());
        }
        return tempList.size() * 100.0 / capacity;
    }
}
