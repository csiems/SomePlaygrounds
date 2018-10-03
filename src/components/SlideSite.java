package components;

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
        return getVisitors(start, end).size() * 100.0 / getCapacity();
    }
}
