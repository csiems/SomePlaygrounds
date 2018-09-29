import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VisitTest {

    @Test
    void getPlaySite_CorrectlyDeterminesVisitSite_BallPitVisitReturnsBallPit() {
        BallPitSite ballPit = new BallPitSite(1);
        Visit visit = new Visit(ballPit);
        assertEquals(ballPit, visit.getPlaySite());
    }

    @Test
    void getVisitLength_CorrectlyDeterminesVisitLength_ReturnsLengthOfVisit()
            throws InterruptedException {
        Kid kid = new Kid(true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit);
        Thread.sleep(1000);
        kid.exitSite();
        assert(kid.visits.get(0).getVisitLength() > 0);
    }

    @Test
    void getVisitLength_DeterminesIfVisitIsStillOngoing_ReturnsNegativeOneWhenVisitIsActive() {
        Kid kid = new Kid(true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit);
        assertEquals(-1, kid.visits.get(0).getVisitLength());
    }
}