package ch.theowinter;

import java.util.Date;

public class ChainEvent implements Comparable<ChainEvent>{
    public String name;
    public Date occurance;

    public ChainEvent(String name, Date occurance) {
        this.name = name;
        this.occurance = occurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChainEvent chainEvent = (ChainEvent) o;

        if (!name.equals(chainEvent.name)) return false;
        if (!occurance.equals(chainEvent.occurance)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + occurance.hashCode();
        return result;
    }

    @Override
    public int compareTo(ChainEvent otherEvent) {
        if(this.occurance == otherEvent.occurance){
            return 0;
        } else if(this.occurance.compareTo(otherEvent.occurance)>0){
            return 1;
        } else {
            return -1;
        }
    }
}
