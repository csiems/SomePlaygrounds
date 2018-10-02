package base;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import components.PlaySite;
import utils.Kid;

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
     * a playground and adds utils.Kid to a map sorted by time of entry.
     * @return Map of visitors sorted by time of entry
     */
    public Multimap<Long, Kid> getVisitors() {
        Multimap<Long, Kid> combinedMap = Multimaps.newMultimap(
                new TreeMap<Long, Collection<Kid>>(),
                (Supplier<List<Kid>>) Lists::newArrayList
        );

        for (PlaySite site : playSites) {
            Multimap<Long, Kid> siteMap = site.getVisitors();
            combinedMap.putAll(siteMap);
        }

        return combinedMap;
    }

    public Multimap<Long, Kid> getVisitors(long start, long end) {
        Multimap<Long, Kid> combinedMap = Multimaps.newMultimap(
                new TreeMap<Long, Collection<Kid>>(),
                (Supplier<List<Kid>>) Lists::newArrayList
        );

        for (PlaySite site : playSites) {
            Multimap<Long, Kid> siteMap = site.getVisitors(start, end);
            combinedMap.putAll(siteMap);
        }

        return combinedMap;
    }

    public List<Kid> getVisitorsAsList() {
        List<Kid> visitorsList = new ArrayList<>();
        for (Map.Entry<Long, Kid> entry : getVisitors().entries()) {
            visitorsList.add(entry.getValue());
        }
        return visitorsList;
    }

    public List<Kid> getVisitorsAsList(long start, long end) {
        List<Kid> visitorsList = new ArrayList<>();
        for (Map.Entry<Long, Kid> entry : getVisitors(start, end).entries()) {
            visitorsList.add(entry.getValue());
        }
        return visitorsList;
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

    /**
     * Calculates the current utilization percentage of a playground.
     * @return A double representing the percentage utilized
     */
    public double getCurrentUtilizationStat() {
        return getCurrentVisitors().size() * 100.0 / capacity;
    }

    public double getUtilizationSnapShot(long start, long end) {
        return getVisitorsAsList(start, end).size() * 100.0 / capacity;
    }

}
