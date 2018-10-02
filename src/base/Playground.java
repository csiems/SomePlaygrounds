package base;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import components.PlaySite;
import utils.Kid;
import utils.Visit;

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
     * Returns a map of all visitors to playground's play sites.
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

    /**
     * Returns a map of all visitors whose visit overlaps a given range.
     * @param start Start time in milliseconds
     * @param end End time in milliseconds
     * @return
     */
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

    /**
     * Returns a list of all visitors to a playground's play sites.
     * @return A list of visitors
     */
    public List<Kid> getVisitorsAsList() {
        List<Kid> visitorsList = new ArrayList<>();
        for (Map.Entry<Long, Kid> entry : getVisitors().entries()) {
            visitorsList.add(entry.getValue());
        }
        return visitorsList;
    }

    /**
     * Returns a list of all visitors to a playground's play sites
     * within a given range
     * @param start Start time in milliseconds
     * @param end End time in milliseconds
     * @return
     */
    public List<Kid> getVisitorsAsList(long start, long end) {
        List<Kid> visitorsList = new ArrayList<>();
        for (Map.Entry<Long, Kid> entry : getVisitors(start, end).entries()) {
            visitorsList.add(entry.getValue());
        }
        return visitorsList;
    }

    /**
     * Calculates the utilization rate of current onsite
     * users as a percentage of playground capacity.
     * @return A double representing the percentage utilized
     */
    public double getCurrentUtilizationStat() {
        List<Kid> historicalVisitors = getVisitorsAsList();
        List<Kid> currentVisitors = new ArrayList<>();

        for (Kid kid : historicalVisitors) {
            if (kid.getCurrentVisit() != null) {
                currentVisitors.add(kid);
            }
        }
        return currentVisitors.size() * 100.0 / capacity;
    }

    /**
     * Calculates the utilization rate of onsite users as a
     * percentage of playground capacity within a given range.
     * @param start Start time in milliseconds
     * @param end End time in milliseconds
     * @return A double representing the percentage utilized
     */
    public double getUtilizationSnapShot(long start, long end) {
        return getVisitorsAsList(start, end).size() * 100.0 / capacity;
    }

}
