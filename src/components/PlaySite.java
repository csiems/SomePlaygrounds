package components;

import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Range;
import utils.Kid;
import utils.Ticket;
import utils.Visit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;

import com.google.common.collect.Multimap;

public abstract class PlaySite {

    protected int capacity = 0;
    protected Deque<Kid> kidsOnSite = new ArrayDeque<>();
    protected Deque<Kid> kidsOnQueue = new ArrayDeque<>();
    protected int vipCounter = 0;
    protected int generalCounter = 0;
    protected Multimap<Long, Kid> allVisitors = Multimaps.newMultimap(
            new TreeMap<Long, Collection<Kid>>(),
            (Supplier<List<Kid>>) Lists::newArrayList
    );

    public int getCapacity() {
        return capacity;
    }

    public Deque<Kid> getKidsOnSite() {
        return kidsOnSite;
    }

    public Deque<Kid> getKidsOnQueue() {
        return kidsOnQueue;
    }

    public Multimap<Long, Kid> getVisitors() {
        return allVisitors;
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
            if ((kid.getTicket().getType() == Ticket.Type.VIP) && vipCounter == 0 && kid.getTicket().getSkips() > 0) {
                // VIP's get added to front of queue (only decrement skip if someone is skipped)
                if (kidsOnQueue.size() > 0) { kid.getTicket().decrementSkips(); }
                kidsOnQueue.addFirst(kid);
                vipCounter++;
                generalCounter = 0;
            } else {
                // GENERAL admission gets added to back of queue
                kidsOnQueue.addLast(kid);
                generalCounter++;
                if (generalCounter > 2) {
                    vipCounter = 0;
                }
            }
            kid.addSiteVisit(this, Visit.Status.ONQUEUE);
        }

        // add this visitor to site historical record. Visitor Multimap will store
        // duplicate keys as an ArrayList value. Since this one is backed by a
        // TreeMap it will naturally sort by key.
        Long timeEnteredAsKey = kid.getCurrentVisit().getTimeEntered()
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        allVisitors.put(timeEnteredAsKey, kid);

        return kidsOnQueue.size();
    }

    /**
     * Removes a kid from a play site and advances the next kid in the queue
     * if one is available.
     * @param kid Kid to be removed
     * @return Remaining size of the queue.
     */
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

    /**
     * Finds all kids (onsite and onqueue) whose visit overlaps a given range.
     * <b>Warning: If the same kid uses a site multiple times in
     * within the given range they will be counted more than once.</b>
     * @param start The start of the range
     * @param end The end of the range
     * @return Map of kids whose visit overlaps range.
     */
    public Multimap<Long, Kid> getVisitors(long start, long end) {
        // Grab map of all visitors
        Multimap<Long, Kid> visitors = getVisitors();

        // Create map of visitors whose start time is in range
        Range<Long> filter = Range.closed(start, end);
        Multimap<Long, Kid> startInRange = Multimaps.filterKeys(visitors, filter);

        // Create map of visitors whose exit time is in range
        Multimap<Long, Kid> exitInRange = Multimaps.newMultimap(
                new TreeMap<Long, Collection<Kid>>(),
                (Supplier<List<Kid>>) Lists::newArrayList
        );
        for (Map.Entry<Long, Kid> entry : visitors.entries()) {
            List<Visit> visits = entry.getValue().getVisits();

            for (Visit visit : visits) {
                LocalDateTime exitDateTime = visit.getTimeExited();
                Long exitTime = exitDateTime == null ? 0 : exitDateTime
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                if (start <= exitTime && exitTime <= end) {
                    exitInRange.put(entry.getKey(), entry.getValue());
                }
            }
        }

        Multimap<Long, Kid> combinedMap = Multimaps.newMultimap(
                new TreeMap<Long, Collection<Kid>>(),
                (Supplier<List<Kid>>) Lists::newArrayList
        );

        // combine the maps
        combinedMap.putAll(exitInRange);
        combinedMap.putAll(startInRange);

        return combinedMap;
    }

    /**
     * Calculates the utilization rate of current users (i.e. onsite
     * users as a percentage of site capacity).
     * @return A double representing the percentage utilized
     */
    public abstract double getCurrentUtilizationStat();

    /**
     * Calculates the utilization rate (i.e. onsite users as a
     * percentage of site capacity) of a given range.
     * @return A double representing the percentage utilized
     */
    public abstract double getUtilizationSnapShot(long start, long end);

}
