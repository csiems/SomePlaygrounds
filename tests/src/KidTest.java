import components.BallPitSite;
import components.DoubleSwingSite;
import org.junit.jupiter.api.Test;
import models.Kid;
import models.Ticket;
import models.Visit;

import static org.junit.jupiter.api.Assertions.*;

class KidTest {

    @Test
    void acceptsQueue_CorrectlyReturnsIfKidWillAcceptQueueing_QueueRejecterReturnsFalse() {
        Kid queueRejecter = new Kid("Rasmus", 5,
                                new Ticket(Ticket.Type.GENERAL, 100000000L), false);
        assertEquals(false, queueRejecter.acceptsQueue());
    }

    @Test
    void addSiteVisit_CorrectlyStartsASiteVisit_VisitListSizeWillBeOne() {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit, Visit.Status.ONSITE);
        assertEquals(1, kid.getVisits().size());
    }

    @Test
    void addSiteVisit_ThrowsExceptionIfChildIsAddedToVisitWhenAnotherIsActive_ThrowsUnsupporteOpException() {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        BallPitSite ballPit = new BallPitSite(1);
        DoubleSwingSite swings = new DoubleSwingSite(1);
        kid.addSiteVisit(ballPit, Visit.Status.ONSITE);
        assertThrows(UnsupportedOperationException.class,
                () -> {
                    kid.addSiteVisit(swings, Visit.Status.ONSITE);
                });
    }

    @Test
    void exitSite_AddsExitTimeToSiteVisit_VisitExitTimeIsNotNull() {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        BallPitSite ballPit = new BallPitSite(1);
        kid.addSiteVisit(ballPit, Visit.Status.ONSITE);
        kid.exitSite();
        assertNotNull(kid.getVisits().get(0).getTimeExited());
    }

    @Test
    void exitSite_ThrowsExceptionIfNoSiteIsActive_ThrowsUnsupportedOpException() {
        Kid kid = new Kid("Rasmus", 5,
                new Ticket(Ticket.Type.GENERAL, 100000000L), true);
        assertThrows(UnsupportedOperationException.class,
                ()->{
                    kid.exitSite();
                });
    }
}