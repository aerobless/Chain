package ch.theowinter;

import java.util.Date;

public class ChainEvent implements Comparable<ChainEvent>{
    public String name;
    public String type;
    public Date occurrence;

    public ChainEvent(String name, String type, Date occurrence) {
        this.name = name;
        this.type = type;
        this.occurrence = occurrence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChainEvent chainEvent = (ChainEvent) o;

        if (!name.equals(chainEvent.name)) return false;
        if (!occurrence.equals(chainEvent.occurrence)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + occurrence.hashCode();
        return result;
    }

    @Override
    public int compareTo(ChainEvent otherEvent) {
        if(this.occurrence == otherEvent.occurrence){
            return 0;
        } else if(this.occurrence.compareTo(otherEvent.occurrence)>0){
            return 1;
        } else {
            return -1;
        }
    }
}
