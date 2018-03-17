package com.example.android.afiframadhan_1202154217_studycase4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button listNamaMahasiswa, pencariGambar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNamaMahasiswa = (Button) findViewById(R.id.listmahasiswa);
        pencariGambar = (Button) findViewById(R.id.carigambar);
    }

    public void listNamaMahasiswa(View view) {
        Intent intentListMahasiswa = new Intent(MainActivity.this,ListNamaMahasiswa.class);
        startActivity(intentListMahasiswa);
    }

    public void pencariGambar(View view) {
        Intent intentPencariGambar = new Intent(MainActivity.this,PencariGambar.class);
        startActivity(intentPencariGambar);
    }
}
