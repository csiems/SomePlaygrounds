import java.util.ArrayList;

public class Kid {
    private String name;
    private int age;
    private long ticketNumber;
    private String ticketType;
    private boolean acceptsQueue;
    ArrayList<Visit> visits;
    private Visit currentVisit = null;

    public Kid(boolean acceptsQueue) {
        this.acceptsQueue = acceptsQueue;
        visits = new ArrayList<>();
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

    public void addSiteVisit(PlaySite site) throws UnsupportedOperationException {
        if (currentVisit == null) {
            currentVisit = new Visit(site);
            visits.add(currentVisit);
        } else {
            throw new UnsupportedOperationException("A child cannot visit two sites at the same time.");
        }
    }

    public void exitSite() throws UnsupportedOperationException {
        if (currentVisit != null) {
            currentVisit.isExited();
        } else {
            throw new UnsupportedOperationException("This child is not currently visiting a site");
        }
    }

}
