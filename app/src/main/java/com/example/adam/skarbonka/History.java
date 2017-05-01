package com.example.adam.skarbonka;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle("Historia");

        ListView listView = (ListView) findViewById(R.id.lista);

        ArrayAdapter <AllHistory> adapter = new ArrayAdapter<AllHistory>(this, android.R.layout.simple_list_item_1, getAllHistory());
        listView.setAdapter(adapter);
    }

    public List<AllHistory> getAllHistory() {
        List<AllHistory> allHistoryList = new ArrayList<>();
        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
        Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {
                AllHistory ah = new AllHistory();
                ah.setOpis(cursor.getString(cursor.getColumnIndex("Opis")));
                ah.setAktualnyStan(cursor.getString(cursor.getColumnIndex("AktualnyStan")));
                ah.setKwota(cursor.getString(cursor.getColumnIndex("Kwota")));
                ah.setData(cursor.getString(cursor.getColumnIndex("Data")));
                if(cursor.getString(cursor.getColumnIndex("czyPlus")).equals("+")){
                    ah.setCzyPlus(true);
                } else{
                    ah.setCzyPlus(false);
                }
                allHistoryList.add(ah);
            } while (cursor.moveToPrevious());
        }
        return allHistoryList;
    }
}

