import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayGroundTest {

    @Test
    void addSite_IncrementsCapacityCorrectly_PlaygroundCapacityEqualsFour() {
        Playground playground = new Playground();
        DoubleSwingSite swings1 = new DoubleSwingSite(1);
        DoubleSwingSite swings2 = new DoubleSwingSite(1);
        // Each swing holds two kids
        playground.addPlaySite(swings1);
        playground.addPlaySite(swings2);

        assertEquals(4, playground.getCapacity());
    }


    @Test
    void addSite_WillNotAddDuplicateSiteToPlayground_PlaygroundCapacityEqualsFour() {
        Playground playground = new Playground();
        DoubleSwingSite swings1 = new DoubleSwingSite(1);
        DoubleSwingSite swings2 = new DoubleSwingSite(1);
        // Each swing holds two kids
        playground.addPlaySite(swings1);
        playground.addPlaySite(swings2);
        playground.addPlaySite(swings2);

        assertEquals(4, playground.getCapacity());
    }
}