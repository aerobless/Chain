package ch.theowinter.producers;

import ch.theowinter.Chain;
import ch.theowinter.ChainEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SimpleCalendarProducer implements Chain {
    public String name;
    public Date startDate;
    public Date endDate;

    public SimpleCalendarProducer(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<ChainEvent> compileChain(){
        if(endDate == null){
            endDate = new Date();
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(endDate);
        int endyear = calendar.get(Calendar.YEAR);

        calendar.setTime(startDate);
        int startyear = calendar.get(Calendar.YEAR);

        List<ChainEvent> resultList = new ArrayList<ChainEvent>();
        int counter = 1;
        for(int i=startyear; i<endyear; i++){
            resultList.add(new ChainEvent(counter+". "+name, name, calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
            counter++;
        }
        return resultList;
    }
}
