package com.example.adam.skarbonka;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMoney extends AppCompatActivity {


    EditText pieniadze, opisik;
    Button b1;
    String iloscPieniedzy;
    String Opis;
    int pieniadzeInt;
    Toast info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);

        pieniadze= (EditText) findViewById(R.id.editText1);
        opisik= (EditText) findViewById(R.id.editText2);
        b1=(Button) findViewById(R.id.button1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                iloscPieniedzy = pieniadze.getText().toString();

                Opis = opisik.getText().toString();
                SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
                Cursor cursor = baza.rawQuery("SELECT * FROM Wydatkii", null);
                cursor.moveToLast();

                String aktualnie = cursor.getString(cursor.getColumnIndex("AktualnyStan"));

                try {
                    pieniadzeInt = Integer.parseInt(iloscPieniedzy);
                    int aktualnieInt = Integer.parseInt(aktualnie)+pieniadzeInt;

                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = new Date();
                    String data = dt.format(date);
                    baza.execSQL("INSERT INTO Wydatkii VALUES(" + iloscPieniedzy + ",'" + Opis + "'," + aktualnieInt + ",'"+data+"','+');");
                    baza.close();
                    info = Toast.makeText(AddMoney.this, "Dane zapisane!", Toast.LENGTH_SHORT);
                } catch(Exception ex){
                    baza.close();
                    info = Toast.makeText(AddMoney.this, "Błąd zapisu!", Toast.LENGTH_SHORT);
                }
                info.show();

            }
        });

    }



}
