package utils;

import components.PlaySite;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Visit {
    private PlaySite site;
    private Status status;
    private LocalDateTime timeEntered;
    private LocalDateTime timeExited;

    public enum Status {
        ONSITE,
        ONQUEUE;
    }

    public Visit(PlaySite site, Status status) {
        this.site = site;
        this.status = status;
        this.timeEntered = LocalDateTime.now();
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

    public LocalDateTime getTimeEntered() {
        return timeEntered;
    }

    public LocalDateTime getTimeExited() {
        return timeExited;
    }

    public void isExited() {
        timeExited = LocalDateTime.now();
    }

    /**
     * Finds the length of time spent on a particular visit.
     * @return long representing length of visit in milliseconds, or
     *         -1 if the visit has not ended.
     */
    public long getVisitLength() {
        if (timeExited != null) {
            return ChronoUnit.MILLIS.between(timeEntered, timeExited);
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
