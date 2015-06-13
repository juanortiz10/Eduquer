package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Item;

/**
 * Created by juan on 9/06/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final int database_version=1;
    public String sqlQuery="CREATE TABLE IF NOT EXISTS " +MyTable.TableInfo.table_name+"( "+ MyTable.TableInfo.id_word+" INTEGER PRIMARY KEY, " +
            MyTable.TableInfo.name_word+" TEXT," +MyTable.TableInfo.date+" DATE);";


    public DataBaseHelper(Context context) {
        super(context, MyTable.TableInfo.database_name,null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        onCreate(sqLiteDatabase);
    }

    public void putInformation(DataBaseHelper dop,String name, java.util.Date fecha){
        SQLiteDatabase sq=dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MyTable.TableInfo.name_word,name);
        cv.put(MyTable.TableInfo.date,fecha.toString());
        long k=sq.insert(MyTable.TableInfo.table_name,null,cv);
        dop.close();
        Log.e("Put information correctly","Success");
    }

    public ArrayList<Item> getALl(){
        ArrayList<Item> all= new ArrayList<>();
        String query= "SELECT * from "+MyTable.TableInfo.table_name;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cr= db.rawQuery(query,null);

        if(cr.moveToFirst()){
            do{
                Item item=new Item();
                item.setName(cr.getString(cr.getColumnIndex(MyTable.TableInfo.name_word)));
                all.add(item);
            }while(cr.moveToNext());
        }
        return all;
    }

    public int getWordCount(){
        String query= "SELECT * FROM "+MyTable.TableInfo.table_name;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        Log.e("Put information correctly","Success "+cursor.getCount());
        cursor.close();
        return cursor.getCount();
    }

    public Cursor getWord(DataBaseHelper dop,String nombre){
        String[] args = new String[] {nombre.trim()};
        SQLiteDatabase sq=dop.getReadableDatabase();
        Cursor cr=sq.rawQuery("SELECT * FROM allwords WHERE name_word=?",args);
        return cr;
    }

    public void deleteWord(DataBaseHelper dop){
        SQLiteDatabase sq= dop.getReadableDatabase();
        sq.delete(MyTable.TableInfo.table_name, null, null);
    }

}
