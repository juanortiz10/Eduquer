package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import models.Item;

/**
 * Created by juan on 9/06/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final int database_version=1;
    public String sqlQuery="CREATE TABLE IF NOT EXISTS " +MyTable.TableInfo.table_name+"( "+ MyTable.TableInfo.id_word+" INTEGER PRIMARY KEY, " +
            MyTable.TableInfo.name_word+" TEXT," +MyTable.TableInfo.word_level+" INTEGER," +MyTable.TableInfo.date+" LONG);";


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

    public void putInformation(DataBaseHelper dop,String name){
        SQLiteDatabase sq=dop.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MyTable.TableInfo.name_word,name);
        cv.put(MyTable.TableInfo.date,System.currentTimeMillis());
        cv.put(MyTable.TableInfo.word_level,0);
        long k=sq.insert(MyTable.TableInfo.table_name,null,cv);
        dop.close();
    }

    public ArrayList<Item> getALl(){
        ArrayList<Item> all= new ArrayList<>();
        String query= "SELECT * from "+MyTable.TableInfo.table_name;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cr= db.rawQuery(query, null);

        if(cr.moveToFirst()){
            do{
                Item item=new Item();
                item.setName(cr.getString(cr.getColumnIndex(MyTable.TableInfo.name_word)));
                item.setDate(Long.parseLong(cr.getString(cr.getColumnIndex(MyTable.TableInfo.date))));
                item.setProgress(Integer.parseInt(cr.getString(cr.getColumnIndex(MyTable.TableInfo.word_level))));
                all.add(item);
            }while(cr.moveToNext());
        }
        return all;
    }

    public void deleteWord(DataBaseHelper dop, String word){
        String[]args=new String[]{word};
        SQLiteDatabase sq= dop.getReadableDatabase();
        sq.execSQL("DELETE FROM " + MyTable.TableInfo.table_name + " WHERE " + MyTable.TableInfo.name_word + "=?", args);
    }

    public int getProgress(DataBaseHelper dop, String word){
        SQLiteDatabase db= dop.getReadableDatabase();
        int progress=0;
        Cursor cursor= db.query(MyTable.TableInfo.table_name, new String[]{MyTable.TableInfo.word_level},
                MyTable.TableInfo.name_word+ "=?", new String[]{word},null,null,null,null);
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                progress=Integer.parseInt(cursor.getString(0));
            }
        }catch (Exception ex){}
        return progress;
    }

    public int setProgress(DataBaseHelper dop, int newValue, String word){
        SQLiteDatabase db= dop.getReadableDatabase();

        ContentValues values= new ContentValues();
        values.put(MyTable.TableInfo.word_level, newValue);

        return db.update(MyTable.TableInfo.table_name,values, MyTable.TableInfo.name_word + "=?",
                new String[]{word});
    }

    public int setNewDate(DataBaseHelper dop, long newValue, String word){
        SQLiteDatabase db= dop.getReadableDatabase();

        ContentValues values= new ContentValues();
        values.put(MyTable.TableInfo.date, newValue);

        return db.update(MyTable.TableInfo.table_name,values, MyTable.TableInfo.name_word + "=?",
                new String[]{word});
    }

    public String getTime(DataBaseHelper dop, String word){
        SQLiteDatabase db=dop.getReadableDatabase();
        Cursor cursor= db.query(MyTable.TableInfo.table_name, new String[]{MyTable.TableInfo.date},
                MyTable.TableInfo.name_word + "=?", new String[]{word}, null, null, null, null);
        String date=null;
        try {
            if (cursor != null) {
                cursor.moveToFirst();
                date=cursor.getString(0);
            }
        }catch (Exception ex){}
        return date;
    }
}
