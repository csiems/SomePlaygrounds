package utils;

import java.util.Objects;

public class Ticket {

    private static final int VIP_SKIPS = 3;

    private Type type;
    private long number;
    private int skips;

    public enum Type {
        GENERAL,
        VIP
    }

    public Ticket(Type type, long number) {
        this.type = type;
        this.number = number;
        if (type.equals(Type.VIP)) {
            skips = VIP_SKIPS;
        } else {
            skips = 0;
        }
    }

    public Type getType() {
        return type;
    }

    public long getNumber() {
        return number;
    }

    public int getSkips() {
        return skips;
    }

    /**
     * Decreases the number of skips left on a ticket
     */
    public void decrementSkips() {
        if (skips > 0) {
            skips--;
        } else {
            skips = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return getNumber() == ticket.getNumber() &&
                getType() == ticket.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getNumber());
    }

    @Override
    public String toString() {
        return "utils.Ticket{" +
                "type=" + type +
                ", number=" + number +
                '}';
    }
}
