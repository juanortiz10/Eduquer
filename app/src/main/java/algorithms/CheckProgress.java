package algorithms;

import java.util.HashMap;

/**
 * Created by juan on 21/06/15.
 */
public class CheckProgress {
    private HashMap<Integer,Integer> table=new HashMap();

    public CheckProgress(){
        CreateTable();
    }
    public int checkSeconds(int seconds){
        int val=surroundSeconds(seconds);
        int fin=table.get(val);
        return fin;
    }

    public int  surroundSeconds(int seconds){
        int value=0;
        if(seconds>0 && seconds <60) {
            value = 60;
        }else if(seconds>=60 && seconds <120){
            value = 120;
        }else if(seconds>=120&& seconds <180){
            value=180;
        }else if(seconds>=180 && seconds <240){
            value=240;
        }else if(seconds>=240 && seconds <300){
            value=300;
        }else if(seconds>=300 && seconds <360){
            value=360;
        }else if(seconds>=360 && seconds <420){
            value=420;
        }else if(seconds>=420 && seconds <480){
            value=480;
        }else if(seconds>=480 && seconds <540){
            value=540;
        }else if(seconds>=540 && seconds <600){
            value=600;
        }else if(seconds>=600 && seconds <660){
            value=660;
        }else if(seconds>=660 && seconds <720){
            value=720;
        }else if(seconds>=720 && seconds <780){
            value=780;
        }else if(seconds>=780 && seconds <840){
            value=840;
        }else if(seconds>=840 && seconds <900){
            value=900;
        }
        return value;
    }
    private void CreateTable(){
        int value=60;
        for(int i=0; i<15; i++){
            table.put(value,i);
            value+=60;
        }

    }
}
