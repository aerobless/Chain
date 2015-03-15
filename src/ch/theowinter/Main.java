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
            List<ChainEvent> eventList = birthdays.compileChain();

            GoogleCalendarChain googleCalendarChain = new GoogleCalendarChain();
            try{
                eventList.addAll(googleCalendarChain.compileChain());
            } catch (Exception e){
                System.out.println(e);
            }

            HTMLPublisher publisher = new HTMLPublisher(eventList);
            publisher.publish();

        }catch (ParseException e){
            System.out.println("error");
        }
    }
}
