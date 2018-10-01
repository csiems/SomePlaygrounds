package utils;

import components.PlaySite;

import java.util.Date;

public class Visit {
    private PlaySite site;
    private Status status;
    private Date timeEntered;
    private Date timeExited;

    public enum Status {
        ONSITE,
        ONQUEUE;
    }

    public Visit(PlaySite site, Status status) {
        this.site = site;
        this.status = status;
        this.timeEntered = new Date();
    }

    public PlaySite getPlaySite() {
        return site;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "utils.Visit{" +
                "site=" + site +
                ", status='" + status + '\'' +
                ", timeEntered=" + timeEntered +
                ", timeExited=" + timeExited +
                '}';
    }
}
