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
}
