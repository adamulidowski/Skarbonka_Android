package com.example.adam.skarbonka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class PanelGlowny extends AppCompatActivity {

    private Button addMoney;
    private Button deleteMoney;
    private Button history;
    private TextView stanKonta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_glowny);



        addMoney = (Button)findViewById(R.id.addMoney);
        deleteMoney = (Button)findViewById(R.id.deleteMoney);
        history = (Button)findViewById(R.id.history);
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

        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
       // baza.execSQL("DROP TABLE Wydatkii;");
        baza.execSQL("CREATE TABLE IF NOT EXISTS Wydatkii (Kwota VARCHAR, Opis VARCHAR, AktualnyStan VARCHAR, Data VARCHAR, czyPlus VARCHAR);");
        //Date data = new Date();
        //baza.execSQL("INSERT INTO Wydatkii VALUES('0', 'Stan Początkowy', '0', '2017-04-26 10:00:00','+');");
        Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
        cursor.moveToLast();
        String stan = cursor.getString(cursor.getColumnIndex("AktualnyStan"));
        String stanZl = stan+ " zł";

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
