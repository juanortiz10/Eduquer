package algorithms;

import android.content.Context;
import android.widget.Toast;

import java.util.Calendar;
import helper.DataBaseHelper;
import models.Item;

/**
 * Created by jjortiz on 30/06/15.
 */
public class Low {

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
            System.out.println(hoursMax);
            System.out.println(hoursMin);
        return checkProgress(hoursMax-hoursMin);
    }

    private int checkProgress(int hours){
        int value=0,h1=0,h2=12;
        for(int i=0; i<5; i++){
            if(hours>0 && hours>h1 && hours<=h2){
                value=i*2;
                break;
            }else{
                h1+=12;
                h2+=12;
            }
        }
            return value;
    }
}