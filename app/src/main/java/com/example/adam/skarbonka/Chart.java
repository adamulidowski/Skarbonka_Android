package com.example.adam.skarbonka;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        setTitle("Wykres");

        List<AllHistory> history = getAllHistory();
        ArrayList<Entry> entriess = new ArrayList<>();
        for(int i=0; i<history.size(); i++){
            entriess.add(new Entry(Integer.parseInt(history.get(i).getAktualnyStan()), i));
        }

        LineDataSet datasett = new LineDataSet(entriess, "");

        ArrayList<String> labelss = new ArrayList<String>();

        for(int i=0; i<history.size(); i++){
            labelss.add(history.get(i).getOpis());
        }

        LineChart chartt = new LineChart(getBaseContext());
        setContentView(chartt);

        LineData dataa = new LineData(labelss, datasett);

        datasett.setLineWidth(2);
        //datasett.setColors(ColorTemplate.COLORFUL_COLORS);
        datasett.setColor(Color.BLACK);
        datasett.setCircleColor(Color.BLUE);
        chartt.setData(dataa);
        chartt.setScaleXEnabled(true);
        chartt.setClickable(false);
        chartt.setScaleYEnabled(false);
        chartt.setDoubleTapToZoomEnabled(false);
        chartt.setFocusable(false);
        chartt.setFocusableInTouchMode(false);
        chartt.dispatchWindowFocusChanged(false);
        chartt.setPinchZoom(false);
        chartt.setHorizontalScrollBarEnabled(true);
        chartt.setVerticalScrollBarEnabled(true);
        chartt.setDescription("Historia skarbonki");
        chartt.setFocusable(false);
        chartt.setFocusableInTouchMode(false);
    }

    public List<AllHistory> getAllHistory() {
        List<AllHistory> allHistoryList = new ArrayList<>();
        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
        Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
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
            } while (cursor.moveToNext());
        }
        return allHistoryList;
    }
}
