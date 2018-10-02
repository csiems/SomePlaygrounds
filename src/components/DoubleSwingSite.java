package components;

import com.google.common.collect.Multimap;
import utils.Kid;
import utils.Visit;

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
        return kidsOnSite.size() * 100.0 / getCapacity();
    }

    @Override
    public double getUtilizationSnapShot(long start, long end) {
        Multimap<Long, Kid> historicalVisitors = getVisitors(start, end);
        List<Kid> visitorsOnsite = new ArrayList<>();

        for (Map.Entry<Long, Kid> entry : historicalVisitors.entries()) {
            if (entry.getValue().getCurrentVisit() != null
                    && entry.getValue().getCurrentVisit().getStatus() == Visit.Status.ONSITE) {
                visitorsOnsite.add(entry.getValue());
            }
        }

        return visitorsOnsite.size() * 100.0 / getCapacity();
    }
}
