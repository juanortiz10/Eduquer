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
        int sec1=0,sec2=60;
        int value=0;
        for(int i=0; i<15; i++){
            if(seconds>0 && seconds>=sec1 && seconds <sec2) {
                value = sec2;
                break;
            }else {
                sec1 += 60;
                sec2 += 60;
            }
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
