import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KidTest {

    @Test
    void acceptsQueue_CorrectlyReturnsIfKidWillAcceptQueueing_QueueRejecterReturnsFalse() {
        Kid queueRejecter = new Kid(false);
        assertEquals(false, queueRejecter.acceptsQueue());
    }

    @Test
    void addSiteVisit_CorrectlyStartsASiteVisit_VisitListSizeWillBeOne() {
        Kid kid = new Kid(true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit);
        assertEquals(1, kid.visits.size());
    }

    @Test
    void addSiteVisit_ThrowsExceptionIfChildIsAddedToVisitWhenAnotherIsActive_ThrowsUnsupporteOpException() {
        Kid kid = new Kid(true);
        BallPitSite ballPit = new BallPitSite(1);
        DoubleSwingSite swings = new DoubleSwingSite(1);
        kid.addSiteVisit(ballPit);
        assertThrows(UnsupportedOperationException.class,
                () -> {
                    kid.addSiteVisit(swings);
                });
    }

    @Test
    void exitSite_AddsExitTimeToSiteVisit_VisitExitTimeIsNotNull() {
        Kid kid = new Kid(true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit);
        kid.exitSite();
        assertNotNull(kid.visits.get(0).getTimeExited());
    }

    @Test
    void exitSite_ThrowsExceptionIfNoSiteIsActive_ThrowsUnsupportedOpException() {
        Kid kid = new Kid(true);
        assertThrows(UnsupportedOperationException.class,
                ()->{
                    kid.exitSite();
                });
    }
}