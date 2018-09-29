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

    @Test
    void removeKid_RemovesKidFromSiteIfPresent_SiteSizeIsZero() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid(false);
        ballpit.removeKid(kidOnSite);
        assertEquals(0, ballpit.getKidsOnSite().size());
    }

    @Test
    void removeKid_RemovesKidFromQueueIfPresent_QueueSizeIsZero() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid(false);
        Kid firstInQueue = new Kid(true);

        ballpit.addKid(kidOnSite);
        ballpit.addKid(firstInQueue);
        ballpit.removeKid(firstInQueue);

        assertEquals(0, ballpit.getKidsOnQueue().size());
    }

    @Test
    void removeKid_IndicatesIfKidWasNotPresent_ReturnsNegativeOne() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidNotPresent = new Kid(false);
        int result = ballpit.removeKid(kidNotPresent);

        assertEquals(-1, result);
    }

    @Test
    void removeKid_ReplacesRemovedKidWithFirstKidInQueue_FirstKidInQueueGetsAddedToSite() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid(false);
        Kid firstInQueue = new Kid(true);
        Kid secondInQueue = new Kid(true);

        ballpit.addKid(kidOnSite);
        ballpit.addKid(firstInQueue);
        ballpit.addKid(secondInQueue);

        ballpit.removeKid(kidOnSite);

        assertEquals(firstInQueue, ballpit.getKidsOnSite().getFirst());

    }

}
