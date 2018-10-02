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
        return getVisitors(start, end).size() * 100.0 / getCapacity();
    }
}
