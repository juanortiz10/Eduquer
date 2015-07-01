package algorithms;

import android.content.Context;
import java.util.Calendar;
import java.util.HashMap;
import helper.DataBaseHelper;
import models.Item;

/**
 * Created by jjortiz on 30/06/15.
 */
public class Low {
    Item item= new Item();
   // HashMap<Integer,Integer> mapValues=new HashMap<>();

    public Low(Context context){
     //   fillHash();
        init(context);
    }

    private void init(Context context) {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
        for(Item i: dataBaseHelper.getALl()){
            long newDate=check(System.currentTimeMillis(),i.getDate());
            dataBaseHelper.setNewDate(dataBaseHelper,newDate,i.getName());

            //setNewScore nextActivity
        }
    }

    private long check(long max,long min){
        long res=max-min;
        Calendar c=Calendar.getInstance();
        c.setTimeInMillis(res);
        checkProgress(c.get(Calendar.HOUR));
        return res;
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
/*
    private void fillHash(){
        int value=2;
        for(int i=1; i<=6; i++){
            mapValues.put(value,i);
            value+=2;
        }
    }
*/
}


/*Algoritmo de reduccion de puntaje

Verificara la ultima fecha de modificación, la comparara con la fecha
actual en la que se esta verificando, los puntos que se descontaran seran los
siguientes
2 dia- 1
4 dias- 2
6 dias- 3
8 dias- 4
10 dias- 5
12 dias- 6

la clase se correra una vez por dia
 */