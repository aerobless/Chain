package ch.theowinter;

import ch.theowinter.producers.GoogleCalendarProducer;
import ch.theowinter.producers.SimpleCalendarProducer;
import ch.theowinter.publishers.HTMLPublisher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String birthday = "15/09/1991";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date date = format.parse(birthday);
            SimpleCalendarProducer birthdays = new SimpleCalendarProducer("Birthday", date, null);
            List<ChainEvent> eventList = birthdays.compileChain();

            GoogleCalendarProducer googleCalendarProducer = new GoogleCalendarProducer();
            eventList.addAll(googleCalendarProducer.compileChain());

            HTMLPublisher publisher = new HTMLPublisher(eventList);
            publisher.publish();

        }catch (ParseException e){
            System.out.println("error");
        }


        System.out.println("Gracefully finished execution ..");
    }
}
