package components;

import utils.Kid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarouselSite extends PlaySite {

    public CarouselSite(int numberOfAnimals) {
        // Assumes carousel animals can hold only one kid
        // TODO: May want to add carousel animals, each with
        // TODO: their own capacity that would then be summed
        // TODO: for carousel capacity.
        this.capacity = numberOfAnimals;
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
