package utils;

import components.PlaySite;

import java.util.ArrayList;
import java.util.Objects;

public class Kid {
    private String name;
    private int age;
    private Ticket ticket;
    private boolean acceptsQueue;
    private ArrayList<Visit> visits;
    private Visit currentVisit = null;

    public Kid(String name, int age, Ticket ticket, boolean acceptsQueue) {
        this.name = name;
        this.age = age;
        this.ticket = ticket;
        this.acceptsQueue = acceptsQueue;
        visits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean acceptsQueue() {
        return acceptsQueue;
    }

    public Visit getCurrentVisit() {
        return currentVisit;
    }

    public ArrayList<Visit> getVisits() {
        return visits;
    }

    /**
     * Creates a visit entry for a
     * @param site The site to which the kid should be added
     * @param status Status of kid (either ONSITE or ONQUEUE)
     * @throws UnsupportedOperationException If kid already has an active site visit
     */
    public void addSiteVisit(PlaySite site, Visit.Status status)
            throws UnsupportedOperationException {
        if (currentVisit == null) {
            currentVisit = new Visit(site, status);
            visits.add(currentVisit);
        } else {
            throw new UnsupportedOperationException("A child cannot visit two sites at the same time.");
        }
    }

    /**
     * Adds exit time to current site visit and sets current visit to null
     * @throws UnsupportedOperationException If a kid does not have an active current visit.
     */
    public void exitSite() throws UnsupportedOperationException {
        if (currentVisit != null) {
            visits.get(visits.lastIndexOf(currentVisit)).isExited();
            currentVisit = null;
        } else {
            throw new UnsupportedOperationException("This child is not currently visiting a site");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kid kid = (Kid) o;
        return getAge() == kid.getAge() &&
                acceptsQueue == kid.acceptsQueue &&
                Objects.equals(getName(), kid.getName()) &&
                Objects.equals(getTicket(), kid.getTicket()) &&
                Objects.equals(getVisits(), kid.getVisits()) &&
                Objects.equals(getCurrentVisit(), kid.getCurrentVisit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getTicket(), acceptsQueue, getVisits(), getCurrentVisit());
    }

    @Override
    public String toString() {
        return "utils.Kid{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ticket=" + ticket +
                ", acceptsQueue=" + acceptsQueue +
                ", visits=" + visits +
                ", currentVisit=" + currentVisit +
                '}';
    }
}
