package ch.theowinter;

import ch.theowinter.producers.DirectoryFileProducer;
import ch.theowinter.producers.GoogleCalendarProducer;
import ch.theowinter.producers.SimpleCalendarProducer;
import ch.theowinter.publishers.HTMLPublisher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<ChainEvent> chainOfEvents = new ArrayList<ChainEvent>();

        try{
            //Example for SimpleCalendarProducer:
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleCalendarProducer birthdays = new SimpleCalendarProducer("Birthday", format.parse("15/09/1991"), null);
            chainOfEvents.addAll(birthdays.compileChain());

            //Example for DirectoryFileProducer
            DirectoryFileProducer pictures = new DirectoryFileProducer("/Users/theowinter/Pictures","jpg");
            chainOfEvents.addAll(pictures.compileChain());

            //Example for GoogleCalendarProducer
            GoogleCalendarProducer googleCalendarProducer = new GoogleCalendarProducer();
            chainOfEvents.addAll(googleCalendarProducer.compileChain());

            //Example for HTMLPublisher
            HTMLPublisher publisher = new HTMLPublisher(chainOfEvents);
            publisher.publish();

            System.out.println("Gracefully finished execution ..");
        }catch (Exception e){
            System.out.println("Error"+e);
        }
    }
}
