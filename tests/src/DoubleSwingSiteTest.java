import components.DoubleSwingSite;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleSwingSiteTest {

    @Test
    void addSwing_IncrementsSwingByOne_TwoSwingsBecomeThree() {
        DoubleSwingSite swing = new DoubleSwingSite(2);
        swing.addSwing();
        assertEquals(3, swing.getNumberOfSwings());
    }

    @Test
    void addSwing_GroupIncrementsSwingsByNumberGiven_TwoSwingsBecomeFour() {
        DoubleSwingSite swing = new DoubleSwingSite(2);
        swing.addSwing(2);
        assertEquals(4, swing.getNumberOfSwings());
    }

    @Test
    void removeSwing_DecrementsSwingsByOne_TwoSwingsBecomeOne() {
        DoubleSwingSite swing = new DoubleSwingSite(2);
        swing.removeSwing();
        assertEquals(1, swing.getNumberOfSwings());
    }

    @Test
    void removeSwing_WillNotDecrementsSwingsPastZero_OneSwingsBecomeZero() {
        DoubleSwingSite swing = new DoubleSwingSite(1);
        swing.removeSwing();
        assertEquals(0, swing.getNumberOfSwings());
    }

    @Test
    void removeSwing_GroupDecrementsSwingsByNumberGiven_TwoSwingsBecomeZero() {
        DoubleSwingSite swing = new DoubleSwingSite(2);
        swing.removeSwing(2);
        assertEquals(0, swing.getNumberOfSwings());
    }

    @Test
    void removeSwing_WillNotGroupDecrementPastZero_TwoSwingsBecomeZero() {
        DoubleSwingSite swing = new DoubleSwingSite(2);
        swing.removeSwing(3);
        assertEquals(0, swing.getNumberOfSwings());
    }
}