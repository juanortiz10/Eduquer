package helper;


import android.provider.BaseColumns;

public class MyTable {

    public MyTable(){

    }

    public static abstract class TableInfo implements BaseColumns{
        public static final String id_word="id_word";
        public static final String name_word="name_word";
        public static final String word_level="word_level";
        public static final String date="dat";
        public static final String database_name="eduquer";
        public static final String table_name="allwords";
    }
}
