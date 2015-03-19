package ch.theowinter.publishers;

import ch.theowinter.ChainEvent;

import java.util.Collections;
import java.util.List;

public class TextPublisher {
    public List<ChainEvent> eventList;

    public TextPublisher(List<ChainEvent> eventList) {
        Collections.sort(eventList);
        this.eventList = eventList;
    }

    public void publish(){
        for(ChainEvent chainEvent : eventList){
            System.out.println(chainEvent.occurance+" "+ chainEvent.name);
        }
    }
}
