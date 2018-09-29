import java.util.*;

public abstract class PlaySite {

    protected int capacity = 0;
    protected Deque<Kid> kidsOnSite = new ArrayDeque<>();
    protected Deque<Kid> kidsOnQueue = new ArrayDeque<>();
    protected Map<Long, Kid> historicalVisitors = new HashMap<>();

    public int getCapacity() {
        return capacity;
    }

    public Deque<Kid> getKidsOnSite() {
        return kidsOnSite;
    }

    public Deque<Kid> getKidsOnQueue() {
        return kidsOnQueue;
    }

    public Map<Long, Kid> getHistoricalVisitors() {
        return historicalVisitors;
    }

    public int addKid(Kid kid) {
        if (kidsOnSite.size() < capacity && !kidsOnSite.contains(kid)) {
            // if space is available add kid to site if kid not already there
            kidsOnSite.addLast(kid);
            // add this visitor to site historical record
            if (historicalVisitors.get(kid.getCurrentVisit().getTimeEntered().getTime()) == null) {
                historicalVisitors.put(kid.getCurrentVisit().getTimeEntered().getTime(), kid);
            } else {
                historicalVisitors.put(kid.getCurrentVisit().getTimeEntered().getTime() + 1, kid);
            }
            // update site visit for kid as well
            kid.addSiteVisit(this);
        } else if (!kid.acceptsQueue()) {
            // if no space and kid rejects queue return -1
            return -1;
        } else if (kid.acceptsQueue() && !kidsOnQueue.contains(kid)) {
            // if kid accepts queue and is not in queue already, add to queue
            kidsOnQueue.addLast(kid);
        }
        return kidsOnQueue.size();
    }

    public int removeKid(Kid kid) {
        if (kidsOnSite.contains(kid)) {
            kidsOnSite.remove(kid);
            kid.exitSite();
            if (kidsOnQueue.size() > 0) {
                // lead child in queue gets promoted to site if a kid leaves
                Kid firstInQueue = kidsOnQueue.removeFirst();
                kidsOnSite.addLast(firstInQueue);
                firstInQueue.addSiteVisit(this);
            }
        } else if (kidsOnQueue.contains(kid)) {
            kidsOnQueue.remove(kid);
        } else {
            return -1;
        }
        return kidsOnQueue.size();
    }

}
