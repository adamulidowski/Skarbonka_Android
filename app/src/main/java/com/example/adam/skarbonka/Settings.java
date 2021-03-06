package com.example.adam.skarbonka;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Settings extends AppCompatActivity {

    Button reset;
    Toast info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Ustawienia");

        reset=(Button) findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setMessage("Czy na pewno chcesz wyczyścić skarbonkę?");
                builder.setCancelable(true);
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteDatabase baza = openOrCreateDatabase("skarbonka", MODE_PRIVATE, null);
                        baza.execSQL("DELETE FROM Wydatkii;");
                        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Date date = new Date();
                        String data = dt.format(date);
                        baza.execSQL("INSERT INTO Wydatkii VALUES('0', 'Stan Początkowy', '0', '"+data+"','+');");
                        baza.close();
                        info = Toast.makeText(Settings.this, "Skarbonka wyczyszczona!", Toast.LENGTH_SHORT);
                        info.show();
                    }
                });
                builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


}
