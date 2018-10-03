import base.Playground;
import components.BallPitSite;
import components.DoubleSwingSite;
import org.junit.jupiter.api.Test;
import models.Kid;
import models.Ticket;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
    void addKid_FirstVIPAddedToFrontOfQueue_VIPIsFirstInQueue() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidA = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidB = new Kid("Hanna", 4,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidC = new Kid("Helgi", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidD = new Kid("Kaspar", 3,
                new Ticket(Ticket.Type.VIP, 100000000L), true);
        ballpit.addKid(kidA);
        ballpit.addKid(kidB);
        ballpit.addKid(kidC);
        ballpit.addKid(kidD);
        assertEquals(kidD, ballpit.getKidsOnQueue().getFirst());
    }

    @Test
    void addKid_SecondVIPAddedToFrontOfQueue_VIPIsLastInQueue() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidA = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidB = new Kid("Hanna", 4,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidC = new Kid("Helgi", 3,
                new Ticket(Ticket.Type.VIP, 100000000L), true);
        Kid kidD = new Kid("Kaspar", 3,
                new Ticket(Ticket.Type.VIP, 100000000L), true);
        ballpit.addKid(kidA);
        ballpit.addKid(kidB);
        ballpit.addKid(kidC);
        ballpit.addKid(kidD);
        assertEquals(kidD, ballpit.getKidsOnQueue().getLast());
    }

    @Test
    void addKid_VIPCounterProperlyResets_SecondVIPIsFirstInQueue() {
        BallPitSite ballpit = new BallPitSite(1);
        Kid kidA = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidB = new Kid("Hanna", 4,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidC = new Kid("Helgi", 3,
                new Ticket(Ticket.Type.VIP, 100000000L), true);
        Kid kidD = new Kid("Kaspar", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidE = new Kid("Artjom", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.VIP, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        ballpit.addKid(kidA);
        ballpit.addKid(kidB);
        ballpit.addKid(kidC);
        ballpit.addKid(kidD);
        ballpit.addKid(kidE);
        ballpit.addKid(kidF);
        ballpit.addKid(kidG);
        ballpit.addKid(kidH);

        assertEquals(kidG, ballpit.getKidsOnQueue().getFirst());
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
        BallPitSite ballpit = new BallPitSite(10);
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

        Long start = kidA.getCurrentVisit().getTimeEntered()
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Long end = kidF.getCurrentVisit().getTimeEntered()
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        int result = ballpit.getVisitors(start, end).entries().size();

        assertEquals(6, result);
    }

    @Test
    void getVisitorsAsList_ProperlySortsListAccordingToEntryTime_KidsAreInListAccordingToTimeAdded()
            throws InterruptedException{
        int pause = 50;
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(5);
        BallPitSite ballpit = new BallPitSite(20);
        playground.addPlaySite(swings);
        playground.addPlaySite(ballpit);
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

        swings.addKid(kidA);
        Thread.sleep(pause);
        ballpit.addKid(kidB);
        Thread.sleep(pause);
        swings.addKid(kidC);
        Thread.sleep(pause);
        ballpit.addKid(kidD);
        Thread.sleep(pause);
        swings.addKid(kidE);
        Thread.sleep(pause);

        List<Kid> expected = new ArrayList<>();
        expected.add(kidA);
        expected.add(kidB);
        expected.add(kidC);
        expected.add(kidD);
        expected.add(kidE);

        List<Kid> result = playground.getVisitorsAsList();

        assertEquals(expected, result);
    }

}
