package components;

import com.google.common.collect.Multimap;
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
        return getVisitors(start, end).entries().size() * 100.0 / capacity;
    }
}
