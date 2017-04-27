package baza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adam on 25.04.2017.
 */

public class AndroidDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Baza";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "kasa";
    public static final String COLUMN_NAME = "ilosc";
    public static final String COLUMN_CLASS = "opis";

    public AndroidDbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE" + TABLE_NAME + "(" + COLUMN_NAME + "TEXT," + COLUMN_CLASS + "TEXT" + ");";
        // TODO Auto-generated method stub
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}