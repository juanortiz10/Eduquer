package algorithms;

import android.content.Context;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import helper.DataBaseHelper;
import models.Item;

/**
 * Created by jjortiz on 30/06/15.
 */
public class Low {
    int hours=0;


    public Low(Context context){
        init(context);
    }

    private void init(Context context) {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
        for(Item i: dataBaseHelper.getALl()){
            int progress=dataBaseHelper.getProgress(dataBaseHelper,i.getName());
            int newDate=check(i.getDate());
            dataBaseHelper.setNewDate(dataBaseHelper, System.currentTimeMillis(), i.getName());
            if(progress>0) {
                dataBaseHelper.setProgress(dataBaseHelper, progress - newDate, i.getName());
            }
        }
        dataBaseHelper.close();
    }

    private int check(long min){
          long millis=System.currentTimeMillis();
          Calendar c=Calendar.getInstance();
          Calendar c2=Calendar.getInstance();
          c.setTimeInMillis(millis);
          c2.setTimeInMillis(min);

          int hoursMax=c.get(Calendar.HOUR);
          int hoursMin=c.get(Calendar.HOUR);
        return checkProgress(hoursMax-hoursMin);
    }

    private int checkProgress(int hours){
        int value=0;
        if(hours>0 && hours <48) value=2;
        if(hours>=48 && hours <96) value=4;
        if(hours>=96 && hours <144) value=8;
        if(hours>=144 && hours <192) value=10;
        if(hours>=192 && hours <240) value=12;
            return value;
    }
}