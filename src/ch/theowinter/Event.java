package ch.theowinter;

import java.util.Date;

public class Event implements Comparable<Event>{
    public String name;
    public Date occurance;

    public Event(String name, Date occurance) {
        this.name = name;
        this.occurance = occurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!name.equals(event.name)) return false;
        if (!occurance.equals(event.occurance)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + occurance.hashCode();
        return result;
    }

    @Override
    public int compareTo(Event otherEvent) {
        if(this.occurance == otherEvent.occurance){
            return 0;
        } else if(this.occurance.compareTo(otherEvent.occurance)>0){
            return 1;
        } else {
            return -1;
        }
    }
}
