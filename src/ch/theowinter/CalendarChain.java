package ch.theowinter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarChain {
    public String name;
    public Date startDate;
    public Date endDate;

    public CalendarChain(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Event> compileChain(){
        if(endDate == null){
            endDate = new Date();
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(endDate);
        int endyear = calendar.get(Calendar.YEAR);

        calendar.setTime(startDate);
        int startyear = calendar.get(Calendar.YEAR);

        List<Event> resultList = new ArrayList<Event>();
        for(int i=startyear; i<endyear; i++){
            calendar.add(Calendar.YEAR, 1);
            resultList.add(new Event(name, calendar.getTime()));
        }
        return resultList;
    }
}
