package ch.theowinter;

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
            CalendarChain birthdays = new CalendarChain("Birthday", date, null);
            List<Event> eventList = birthdays.compileChain();

            Event someEvent = new Event("Some Event", format.parse("12/08/1995"));
            eventList.add(someEvent);

            TextPublisher publisher = new TextPublisher(eventList);
            publisher.publish();

        }catch (ParseException e){
            System.out.println("error");
        }

        GoogleCalendarChain googleCalendarChain = new GoogleCalendarChain();
        try{
            googleCalendarChain.setUp();
        } catch (Exception e){

        }
    }
}
