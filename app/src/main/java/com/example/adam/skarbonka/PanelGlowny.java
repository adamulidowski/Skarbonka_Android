package com.example.adam.skarbonka;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class PanelGlowny extends AppCompatActivity {

    private Button addMoney;
    private Button deleteMoney;
    private Button history;
    private Button ustawienia;
    private Button chart;
    private TextView stanKonta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_panel_glowny);
        setTitle("Skarbonka");


        addMoney = (Button)findViewById(R.id.addMoney);
        deleteMoney = (Button)findViewById(R.id.deleteMoney);
        history = (Button)findViewById(R.id.history);
        ustawienia = (Button)findViewById(R.id.ustawienia);
        chart = (Button)findViewById(R.id.chart);
        stanKonta = (TextView) findViewById(R.id.stanKonta);


        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PanelGlowny.this, AddMoney.class);
                PanelGlowny.this.startActivity(myIntent);
            }
        });

        deleteMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PanelGlowny.this, DeleteMoney.class);
                PanelGlowny.this.startActivity(myIntent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PanelGlowny.this, History.class);
                PanelGlowny.this.startActivity(myIntent);
            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PanelGlowny.this, Chart.class);
                PanelGlowny.this.startActivity(myIntent);
            }
        });

        ustawienia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PanelGlowny.this, Settings.class);
                PanelGlowny.this.startActivity(myIntent);
            }
        });

        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
        baza.execSQL("CREATE TABLE IF NOT EXISTS Wydatkii (Kwota VARCHAR, Opis VARCHAR, AktualnyStan VARCHAR, Data VARCHAR, czyPlus VARCHAR);");
       // baza.execSQL("INSERT INTO Wydatkii VALUES('0', 'Stan Początkowy', '0', '2017-04-26 10:00:00','+');");
            Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
            cursor.moveToLast();
            String stan = cursor.getString(cursor.getColumnIndex("AktualnyStan"));
            String stanZl = stan + " zł";

            stanKonta.setText(stanZl);

        baza.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
        Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
        cursor.moveToLast();
        String stan = cursor.getString(cursor.getColumnIndex("AktualnyStan"));
        String stanZl = stan+ " zł";

        stanKonta.setText(stanZl);

        baza.close();
    }
}
