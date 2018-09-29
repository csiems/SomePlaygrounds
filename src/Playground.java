import java.util.*;

public class Playground {
    private ArrayList<PlaySite> playSites;
    private int capacity;

    public Playground() {
        playSites = new ArrayList<>();
        capacity = 0;
    }

    public void addPlaySite(PlaySite playSite) {
        if (!playSites.contains(playSite)) {
            playSites.add(playSite);
            capacity += playSite.getCapacity();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Iterates through the historical visitor list of every site in
     * a playground and adds Kid to a map sorted by time of entry.
     * @return Map of visitors sorted by time of entry
     */
    public Map<Long, Kid> getAllHistoricalVisitors() {
        Map<Long, Kid> sortedMap = new TreeMap<>();

        for (PlaySite site : playSites) {
            Map<Long, Kid> visitors = site.getHistoricalVisitors();
            for (Map.Entry<Long, Kid> entry : visitors.entrySet()) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
        return sortedMap;
    }


    public Map<Long, Kid> getHistoricalVisitors(long start, long end) {
        Map<Long, Kid> sortedMap = new TreeMap<>();

        for (PlaySite site : playSites) {
            Map<Long, Kid> visitors = site.getHistoricalVisitors();
            for (Map.Entry<Long, Kid> entry : visitors.entrySet()) {
                for (Visit visit : entry.getValue().getVisits()) {
                    if ((visit.getTimeEntered().getTime() >= start && visit.getTimeEntered().getTime() <= end) ||
                        (visit.getTimeExited().getTime() >= start && visit.getTimeExited().getTime() <= end)) {
                        sortedMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return sortedMap;
    }

    /**
     * Iterates through a playground's play sites, grabs their current
     * visitors and adds them to a map sorted by entry time.
     * @return A map of current visitors to a playground sorted by
     *         entry time.
     */
    public Map<Long, Kid> getCurrentVisitors() {
        Map<Long, Kid> sortedMap = new TreeMap<>();

        for (PlaySite site : playSites) {
            Iterator<Kid> currentOnSite = site.getKidsOnSite().iterator();
            while (currentOnSite.hasNext()) {
                Kid value = currentOnSite.next();
                Long key = value.getCurrentVisit().getTimeEntered().getTime();
                if (sortedMap.get(key) == null) {
                    sortedMap.put(key, value);
                } else {
                    sortedMap.put(key + 1, value);
                }
            }
            Iterator<Kid> currentInQueue = site.getKidsOnQueue().iterator();
            while (currentInQueue.hasNext()) {
                Kid value = currentInQueue.next();
                Long key = value.getCurrentVisit().getTimeEntered().getTime();
                if (sortedMap.get(key) == null) {
                    sortedMap.put(key, value);
                } else {
                    sortedMap.put(key + 1, value);
                }
            }
        }
        return sortedMap;
    }

}
