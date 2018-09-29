import java.util.ArrayList;

public abstract class PlaySite {

    protected int capacity = 0;
    protected ArrayList<Kid> kidsOnSite = new ArrayList<>();
    protected ArrayList<Kid> kidsOnQueue = new ArrayList<>();

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Kid> getKidsOnSite() {
        return kidsOnSite;
    }

    public ArrayList<Kid> getKidsOnQueue() {
        return kidsOnQueue;
    }

    public int addKid(Kid kid) {
        if (kidsOnSite.size() < capacity) {
            // if space is available add kid to site
            kidsOnSite.add(kid);
        } else if (!kid.acceptsQueue()) {
            // if no space and kid rejects queue return -1
            return -1;
        } else if (kid.acceptsQueue() && !kidsOnQueue.contains(kid)) {
            // if kid accepts queue and is not in queue already, add to queue
            kidsOnQueue.add(kid);
        }
        return kidsOnQueue.size();
    }

    public int removeKid(Kid kid) {
        if (kidsOnSite.contains(kid)) {
            kidsOnSite.remove(kid);
            // lead child in queue gets promoted to site if a kid leaves
            if (kidsOnQueue.size() > 0) {
                Kid firstKidInQueue = kidsOnQueue.get(0);
                kidsOnSite.add(firstKidInQueue);
                kidsOnQueue.remove(firstKidInQueue);
            }
        } else if (kidsOnQueue.contains(kid)) {
            kidsOnQueue.remove(kid);
        } else {
            return -1;
        }
        return kidsOnQueue.size();
    }

}
