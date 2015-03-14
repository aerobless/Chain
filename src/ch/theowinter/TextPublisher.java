package ch.theowinter;

import java.util.List;

public class TextPublisher {
    public List<Event> eventList;

    public TextPublisher(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void publish(){
        for(Event event : eventList){
            System.out.println(event.occurance+" "+event.name);
        }
    }
}
