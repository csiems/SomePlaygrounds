package components;

import utils.Kid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
