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
        for(int i=startyear; i<endyear; i++){
            resultList.add(new ChainEvent(name, calendar.getTime()));
            calendar.add(Calendar.YEAR, 1);
        }
        return resultList;
    }
}
