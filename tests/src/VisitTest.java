import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

    @Test
    void getPlaySite_CorrectlyDeterminesVisitSite_BallPitVisitReturnsBallPit() {
        BallPitSite ballPit = new BallPitSite(1);
        Visit visit = new Visit(ballPit, Visit.Status.ONSITE);
        assertEquals(ballPit, visit.getPlaySite());
    }

    @Test
    void getVisitLength_CorrectlyDeterminesVisitLength_ReturnsLengthOfVisit()
            throws InterruptedException {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit, Visit.Status.ONSITE);
        Thread.sleep(1000);
        kid.exitSite();
        assert(kid.visits.get(0).getVisitLength() > 0);
    }

    @Test
    void getVisitLength_DeterminesIfVisitIsStillOngoing_ReturnsNegativeOneWhenVisitIsActive() {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit, Visit.Status.ONSITE);
        assertEquals(-1, kid.visits.get(0).getVisitLength());
    }
}