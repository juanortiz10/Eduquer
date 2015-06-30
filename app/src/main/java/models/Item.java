package models;

import java.util.Date;

/**
 * Created by juan on 10/06/15.
 */
public class Item {
    private String name;
    private int id;
    private long date;

    public Item(String name, int id){
        this.name=name;
        this.id=id;
    }


    public Item(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
