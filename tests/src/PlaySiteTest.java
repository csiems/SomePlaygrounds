import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaySiteTest {

    @Test
    void addKid_CorrectlyAddsKidIfThereIsCapacity_ReturnsZero() {
        DoubleSwingSite swings = new DoubleSwingSite(1);
        Kid kid = new Kid(true);
        int result = swings.addKid(kid);
        assertEquals(0, result);
    }

    @Test
    void addKid_CorrectlyHandlesKidsWhoRejectQueueWhenCapacityReached_ReturnsNegativeOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid(false);
        int result = swings.addKid(kid);
        assertEquals(-1, result);
    }

    @Test
    void addKid_WillNotAddKidToTheSiteASecondTime_ReturnsZero() {
        DoubleSwingSite swings = new DoubleSwingSite(1);
        Kid kid = new Kid(true);
        int result = swings.addKid(kid);
        swings.addKid(kid);
        assertEquals(0, result);
    }

    @Test
    void addKid_WillNotAddKidToTheQueueASecondTime_ReturnsOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid(true);
        int result = swings.addKid(kid);
        swings.addKid(kid);
        assertEquals(1, result);
    }

    @Test
    void addKid_CorrectlyAddsKidToQueueWhenCapacityReached_ReturnsOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid(true);
        int result = swings.addKid(kid);
        assertEquals(1, result);
    }



}
