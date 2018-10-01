import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlaySiteTest {

    @Test
    void addKid_CorrectlyAddsKidIfThereIsCapacity_ReturnsZero() {
        DoubleSwingSite swings = new DoubleSwingSite(1);
        Kid kid = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        int result = swings.addKid(kid);
        assertEquals(0, result);
    }

    @Test
    void addKid_CorrectlyHandlesKidsWhoRejectQueueWhenCapacityReached_ReturnsNegativeOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        int result = swings.addKid(kid);
        assertEquals(-1, result);
    }

    @Test
    void addKid_WillNotAddKidToTheSiteASecondTime_ThrowsUnsupportedOpException() {
        DoubleSwingSite swings = new DoubleSwingSite(1);
        Kid kid = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        int result = swings.addKid(kid);
        assertThrows(UnsupportedOperationException.class,
                () -> {
                    swings.addKid(kid);
                });
    }

    @Test
    void addKid_WillNotAddKidToTheQueueASecondTime_ReturnsOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        int result = swings.addKid(kid);
        swings.addKid(kid);
        assertEquals(1, result);
    }

    @Test
    void addKid_CorrectlyAddsKidToQueueWhenCapacityReached_ReturnsOne() {
        DoubleSwingSite swings = new DoubleSwingSite(0);
        Kid kid = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        int result = swings.addKid(kid);
        assertEquals(1, result);
    }

    @Test
    void removeKid_RemovesKidFromSiteIfPresent_SiteSizeIsZero() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        ballpit.removeKid(kidOnSite);
        assertEquals(0, ballpit.getKidsOnSite().size());
    }

    @Test
    void removeKid_RemovesKidFromQueueIfPresent_QueueSizeIsZero() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        Kid firstInQueue = new Kid("Hanna", 4, new Ticket(Ticket.Type.GENERAL, 100000000L), true);

        ballpit.addKid(kidOnSite);
        ballpit.addKid(firstInQueue);
        ballpit.removeKid(firstInQueue);

        assertEquals(0, ballpit.getKidsOnQueue().size());
    }

    @Test
    void removeKid_IndicatesIfKidWasNotPresent_ReturnsNegativeOne() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidNotPresent = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        int result = ballpit.removeKid(kidNotPresent);

        assertEquals(-1, result);
    }

    @Test
    void removeKid_ReplacesRemovedKidWithFirstKidInQueue_FirstKidInQueueGetsAddedToSite() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidOnSite = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        Kid firstInQueue = new Kid("Hanna", 4, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid secondInQueue = new Kid("Helgi", 3, new Ticket(Ticket.Type.GENERAL, 100000000L), true);

        ballpit.addKid(kidOnSite);
        ballpit.addKid(firstInQueue);
        ballpit.addKid(secondInQueue);

        ballpit.removeKid(kidOnSite);

        assertEquals(firstInQueue, ballpit.getKidsOnSite().getFirst());

    }

    @Test
    void getVisitors_ProperlyFiltersList_ListSizeIsSix() throws InterruptedException {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidA = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidB = new Kid("Hanna", 4,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidC = new Kid("Helgi", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidD = new Kid("Kaspar", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidE = new Kid("Artjom", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        ballpit.addKid(kidA);
        ballpit.addKid(kidB);
        Thread.sleep(100);
        ballpit.addKid(kidC);
        Thread.sleep(100);
        ballpit.addKid(kidD);
        ballpit.addKid(kidE);
        Thread.sleep(100);
        ballpit.addKid(kidF);
        Thread.sleep(100);
        ballpit.addKid(kidG);
        Thread.sleep(100);
        ballpit.addKid(kidH);

        Long start = kidA.getCurrentVisit().getTimeEntered().getTime();
        Long end = kidF.getCurrentVisit().getTimeEntered().getTime();

        int result = 0;
        for (Map.Entry<Long, List<Kid>> entry : ballpit.getVisitors(start, end).entrySet()) {
            result += entry.getValue().size();
        }

        assertEquals(6, result);

    }

}
