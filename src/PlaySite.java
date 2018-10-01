import java.util.*;

public abstract class PlaySite {

    protected int capacity = 0;
    protected Deque<Kid> kidsOnSite = new ArrayDeque<>();
    protected Deque<Kid> kidsOnQueue = new ArrayDeque<>();
    protected Map<Long, List<Kid>> historicalVisitors = new HashMap<>();

    public int getCapacity() {
        return capacity;
    }

    public Deque<Kid> getKidsOnSite() {
        return kidsOnSite;
    }

    public Deque<Kid> getKidsOnQueue() {
        return kidsOnQueue;
    }

    public Map<Long, List<Kid>> getHistoricalVisitors() {
        return historicalVisitors;
    }

    public synchronized int addKid(Kid kid) {
        if (kidsOnSite.size() < capacity && !kidsOnSite.contains(kid) && !kidsOnQueue.contains(kid)) {
            // if space is available add kid to site if kid not already there
            kidsOnSite.addLast(kid);
            // update site visit for kid as well
            kid.addSiteVisit(this, Visit.Status.ONSITE);
        } else if (!kid.acceptsQueue()) {
            // if no space and kid rejects queue return -1
            return -1;
        } else if (kid.acceptsQueue() && !kidsOnQueue.contains(kid)) {
            // if kid accepts queue and is not in queue already, add to queue
            kidsOnQueue.addLast(kid);
            kid.addSiteVisit(this, Visit.Status.ONQUEUE);
        }

        // add this visitor to site historical record
        Long currentKey = kid.getCurrentVisit().getTimeEntered().getTime();
        if (!historicalVisitors.containsKey(currentKey)) {
            List<Kid> newKidList = new ArrayList<>();
            newKidList.add(kid);
            historicalVisitors.put(currentKey, newKidList);
        } else {
            // if key already exists, get existing value and add new kid to end of it
            List<Kid> existingKidList = historicalVisitors.get(kid.getCurrentVisit().getTimeEntered().getTime());
            existingKidList.add(kid);
            historicalVisitors.put(currentKey, existingKidList);
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
            }
        } else if (kidsOnQueue.contains(kid)) {
            kidsOnQueue.remove(kid);
        } else {
            return -1;
        }
        return kidsOnQueue.size();
    }

    public Map<Long, List<Kid>> getVisitors(long start, long end) {

        Map<Long, List<Kid>> filteredVisitors = new TreeMap<>();
        Map<Long, List<Kid>> visitors = getHistoricalVisitors();

        if (start == end) {
            filteredVisitors.put(start, visitors.get(start));
            return filteredVisitors;
        }

        for (Map.Entry<Long, List<Kid>> entry : visitors.entrySet()) {
            if (entry.getKey() >= start && entry.getKey() <= end) {
                filteredVisitors.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredVisitors;
    }

    public double getUsageStats() {
        return kidsOnSite.size() * 100.00f / capacity;
    }

}
