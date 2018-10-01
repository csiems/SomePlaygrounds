import base.Playground;
import components.BallPitSite;
import components.DoubleSwingSite;
import org.junit.jupiter.api.Test;
import utils.Kid;
import utils.Ticket;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayGroundTest {

    @Test
    void addPlaySite_IncrementsCapacityCorrectly_PlaygroundCapacityEqualsFour() {
        Playground playground = new Playground();
        DoubleSwingSite swings1 = new DoubleSwingSite(1);
        DoubleSwingSite swings2 = new DoubleSwingSite(1);
        // Each swing holds two kids
        playground.addPlaySite(swings1);
        playground.addPlaySite(swings2);

        assertEquals(4, playground.getCapacity());
    }

    @Test
    void addPlaySite_WillNotAddDuplicateSiteToPlayground_PlaygroundCapacityEqualsFour() {
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
    void removePlaySite_DecrementsCapacityCorrectly_PlaygroundCapacityEqualsTwo() {
        Playground playground = new Playground();
        DoubleSwingSite swings1 = new DoubleSwingSite(1);
        DoubleSwingSite swings2 = new DoubleSwingSite(4);
        // Each swing holds two kids
        playground.addPlaySite(swings1);
        playground.addPlaySite(swings2);
        playground.removePlaySite(swings2);

        assertEquals(2, playground.getCapacity());
    }

    @Test
    void removePlaySite_WillNotRemoveUnaddedSiteFromPlayground_PlaygroundCapacityEqualsTwo() {
        Playground playground = new Playground();
        DoubleSwingSite swings1 = new DoubleSwingSite(1);
        DoubleSwingSite swings2 = new DoubleSwingSite(1);
        // Each swing holds two kids
        playground.addPlaySite(swings1);
        playground.removePlaySite(swings2);

        assertEquals(2, playground.getCapacity());
    }

    @Test
    void getVisitorsAsList_ReturnsCompleteList_HistoricalVisitorsListSizeIsFour() {
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(1);
        playground.addPlaySite(swings);
        Kid kidA = new Kid("Rasmus", 5, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidB = new Kid("Hanna", 4, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidC = new Kid("Helgi", 3, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidD = new Kid("Kaspar", 3, new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        swings.addKid(kidA);
        swings.addKid(kidB);
        swings.addKid(kidC);
        swings.removeKid(kidA);
        swings.addKid(kidD);
        swings.addKid(kidA);
        assertEquals(5, playground.getVisitorsAsList().size());
    }

    @Test
    void getVisitorsAsList_ReturnsFilteredList_HistoricalVisitorsListSizeIsSeven() throws InterruptedException{
        int pause = 50;
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(1);
        BallPitSite ballpit = new BallPitSite(2);
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
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        swings.addKid(kidA);
        Thread.sleep(pause);
        ballpit.addKid(kidB);
        swings.addKid(kidC);
        swings.removeKid(kidA);
        ballpit.addKid(kidD);
        swings.addKid(kidA);
        Thread.sleep(pause);
        swings.addKid(kidE);
        Thread.sleep(pause);
        ballpit.addKid(kidF);
        Thread.sleep(pause);
        swings.addKid(kidG);
        Thread.sleep(pause);
        swings.addKid(kidH);

        Long start = kidB.getCurrentVisit().getTimeEntered().getTime();
        Long end = kidG.getCurrentVisit().getTimeEntered().getTime();

        List<Kid> result = playground.getVisitorsAsList(start, end);

        assertEquals(7, result.size());
    }

    @Test
    void getVisitorsAsList_ReturnsAllKidsIfStartAndEndExceedVisitorValues_HistoricalVisitorsListSizeIsSeven()
            throws InterruptedException {
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(1);
        BallPitSite ballpit = new BallPitSite(2);
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
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        swings.addKid(kidA);
        ballpit.addKid(kidB);
        swings.addKid(kidC);
        ballpit.addKid(kidD);
        swings.addKid(kidE);
        ballpit.addKid(kidF);
        swings.addKid(kidG);
        swings.addKid(kidH);

        Long start = kidA.getCurrentVisit().getTimeEntered().getTime() - 10;
        Long end = new Date().getTime();

        List<Kid> result = playground.getVisitorsAsList(start, end);

        assertEquals(8, result.size());
    }

    @Test
    void getCurrentVisitors_ReturnsCurrentVisitors_CurrentVisitorsListSizeIsFour() throws InterruptedException{
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(1);
        BallPitSite ballpit = new BallPitSite(2);
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
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        swings.addKid(kidA);
        ballpit.addKid(kidB);
        swings.addKid(kidC);
        ballpit.addKid(kidD);
        swings.addKid(kidE);
        ballpit.addKid(kidF);
        swings.addKid(kidG);
        swings.addKid(kidH);
        swings.removeKid(kidA);
        ballpit.removeKid(kidB);
        swings.removeKid(kidC);
        ballpit.removeKid(kidD);

        assertEquals(4, playground.getCurrentVisitors().size());
    }

    @Test
    public void getUsageStats_ReturnsStatForAllSites_UtilizationIsThirtyPercent() {
        Playground playground = new Playground();
        DoubleSwingSite swings = new DoubleSwingSite(5);
        BallPitSite ballpit = new BallPitSite(10);
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
        Kid kidF = new Kid("Kirill", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidG = new Kid("Mirtel", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        Kid kidH = new Kid("Liisa", 3,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        swings.addKid(kidA);
        ballpit.addKid(kidB);
        swings.addKid(kidC);
        ballpit.addKid(kidD);
        swings.addKid(kidE);
        ballpit.addKid(kidF);
        assertEquals(30.0, playground.getCurrentUtilizationStat());
    }


}