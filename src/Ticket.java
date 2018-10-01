import java.util.Objects;

public class Ticket {

    private Type type;
    private long number;

    public enum Type {
        GENERAL,
        VIP
    }

    public Ticket(Type type, long number) {
        this.type = type;
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public long getNumber() {
        return number;
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
        return "Ticket{" +
                "type=" + type +
                ", number=" + number +
                '}';
    }
}
