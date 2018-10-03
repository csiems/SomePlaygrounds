package components;

public class CarouselSite extends PlaySite {

    public CarouselSite(int numberOfAnimals) {
        // Assumes carousel animals can hold only one kid
        this.capacity = numberOfAnimals;
    }

    public void addAnimals(int additionalAnimals) {
        capacity += additionalAnimals;
    }

    public void removeAnimals(int removedAnimals) {
        if (capacity >= removedAnimals) {
            capacity -= removedAnimals;
        } else {
            capacity = 0;
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
