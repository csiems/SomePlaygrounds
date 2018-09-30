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

    @Test
    void getAllHistoricalVisitorsAsList_ReturnsCompleteList_HistoricalVisitorsListSizeIsFour() {
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(1);
        playground.addPlaySite(swings);
        Kid kidA = new Kid("Rasmus", 5, 10000000L, "GENERAL", true);
        Kid kidB = new Kid("Hanna", 4, 10000001L, "GENERAL", true);
        Kid kidC = new Kid("Helgi", 3, 10000002L, "GENERAL", true);
        Kid kidD = new Kid("Kaspar", 3, 10000003L, "GENERAL", true);
        swings.addKid(kidA);
        swings.addKid(kidB);
        swings.addKid(kidC);
        swings.removeKid(kidA);
        swings.addKid(kidD);
        swings.addKid(kidA);
        assertEquals(5, playground.getVisitorsAsList().size());

    }
}