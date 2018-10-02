package components;

import com.google.common.collect.Multimap;
import utils.Kid;
import utils.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarouselSite extends PlaySite {

    public CarouselSite(int numberOfAnimals) {
        // Assumes carousel animals can hold only one kid
        this.capacity = numberOfAnimals;
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
