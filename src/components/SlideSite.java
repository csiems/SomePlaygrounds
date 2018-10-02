package components;

import com.google.common.collect.Multimap;
import utils.Kid;
import utils.Visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlideSite extends PlaySite {

    public SlideSite(int slides) {
        this.capacity = slides;
    }

    public void addSlide() {
        capacity++;
    }

    public void addSlides(int slide) {
        capacity += slide;
    }

    public void removeSlide() {
        if (capacity > 0) {
            capacity--;
        } else {
            capacity = 0;
        }
    }

    public void removeSlides(int slide) {
        if (capacity >= slide) {
            capacity -= slide;
        } else {
            capacity = 0;
        }
    }

    public int getSlideCount() {
        return capacity;
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
