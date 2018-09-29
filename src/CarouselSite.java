public class CarouselSite extends PlaySite {

    public CarouselSite(int numberOfAnimals) {
        // Assumes carousel animals can hold only one kid
        // TODO: May want to add carousel animals, each with
        // TODO: their own capacity that would then be summed
        // TODO: for carousel capacity.
        this.capacity = numberOfAnimals;
    }
}
