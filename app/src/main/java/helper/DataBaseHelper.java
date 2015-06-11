package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by juan on 9/06/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME= "eduquer";
    public static final String TABLE_NAME= "words";
    public static final String WORDS_ID= "id";
    public static final String WORDS_NAME= "name";
    public static final String WORDS_TIME= "time";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE words (id integer primary key, words text, time TIMESTAMP NOT NULL DEFAULT current_timestamp) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            db.execSQL("DROP TABLE IF EXISTS words");
            onCreate(db);
    }

    public boolean insertWord(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        db.insert("eduquer",null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * * WORDS WHERE id="+id+"",null);
        return res;
    }

    public ArrayList<String> getAllWords(){
        ArrayList<String> arrayList= new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM eduquer", null);
        res.moveToFirst();

        while(res.isAfterLast()== false){
            arrayList.add(res.getString(res.getColumnIndex(WORDS_NAME)));
            res.moveToNext();
        }
        return arrayList;
    }
}
