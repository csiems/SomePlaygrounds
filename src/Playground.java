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

    public void removePlaySite(PlaySite playSite) {
        if (playSites.contains(playSite)) {
            playSites.remove(playSite);
            capacity -= playSite.getCapacity();
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
    public Map<Long, List<Kid>> getVisitors() {
        Map<Long, List<Kid>> sortedMap = new TreeMap<>();

        for (PlaySite site : playSites) {
            Map<Long, List<Kid>> visitors = site.getHistoricalVisitors();
            for (Map.Entry<Long, List<Kid>> entry : visitors.entrySet()) {
                Long currentKey = entry.getKey();
                if(!sortedMap.containsKey(currentKey)) {
                    sortedMap.put(currentKey, entry.getValue());
                } else {
                    List<Kid> existingValue = sortedMap.get(currentKey);
                    existingValue.addAll(entry.getValue());
                    sortedMap.put(currentKey, existingValue);
                }
            }
        }
        return sortedMap;
    }

    public Map<Long, List<Kid>> getVisitors(long start, long end) {
        Map<Long, List<Kid>> visitors = getVisitors();
        Map<Long, List<Kid>> filteredVisitors = new TreeMap<>();

        for (Map.Entry<Long, List<Kid>> entry : visitors.entrySet()) {
            if (entry.getKey() >= start && entry.getKey() <= end) {
                filteredVisitors.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredVisitors;
    }

    public List<Kid> getVisitorsAsList() {
        List<Kid> listedKids = new ArrayList<>();
        Map<Long, List<Kid>> visitors = getVisitors();

        for (Map.Entry<Long, List<Kid>> entry : visitors.entrySet()) {
            listedKids.addAll(entry.getValue());
        }
        return listedKids;
    }

    public List<Kid> getVisitorsAsList(long start, long end) {
        Map<Long, List<Kid>> visitors = getVisitors();
        List<Kid> filteredVisitorsList = new ArrayList<>();

        for (Map.Entry<Long, List<Kid>> entry : visitors.entrySet()) {
            if (entry.getKey() >= start && entry.getKey() <= end) {
                filteredVisitorsList.addAll(entry.getValue());
            }
        }
        return filteredVisitorsList;
    }

    /**
     * Iterates through a playground's play sites, grabs their current
     * visitors and adds them to a map sorted by entry time.
     * @return A map of current visitors to a playground sorted by
     *         entry time.
     */
    public List<Kid> getCurrentVisitors() {
        List<Kid> historicalVisitors = getVisitorsAsList();
        List<Kid> currentVisitors = new ArrayList<>();

        for (Kid kid : historicalVisitors) {
            if (kid.getCurrentVisit() != null) {
                currentVisitors.add(kid);
            }
        }
        return currentVisitors;
    }

    public double getUsageStats() {
        return getCurrentVisitors().size() * 100.00f / capacity;
    }

    public double getUsageStats(PlaySite site) {
        return site.getUsageStats();
    }

}
