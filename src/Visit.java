import java.util.Date;

public class Visit {
    private PlaySite site;
    private Date timeEntered;
    private Date timeExited;

    public Visit(PlaySite site) {
        this.site = site;
        this.timeEntered = new Date();
    }

    public PlaySite getPlaySite() {
        return site;
    }

    public Date getTimeEntered() {
        return timeEntered;
    }

    public Date getTimeExited() {
        return timeExited;
    }

    public void isExited() {
        timeExited = new Date();
    }

    /**
     * Finds the length of time spent on a particular visit.
     * @return long representing length of visit in milliseconds, or
     *         -1 if the visit has not ended.
     */
    public long getVisitLength() {
        if (timeExited != null) {
            return timeExited.getTime() - timeEntered.getTime();
        } else {
            return -1;
        }
    }
}
