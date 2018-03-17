package com.example.android.afiframadhan_1202154217_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;


import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {

    // Membuat variabel array yang berisi nama-nama mahasiswa
    private String[] namaMahasiswa = { "Afif Ramadhan","Anantya Khrisna", "Rizki Nuzuli", "Sanny Putra",
            "Yudanto Anas", "Taufan Fadhilah", "Rangga Ayesha", "Regin Ridhoputra", "Rizki Alongsyah",
            "M. Ilham", "Abdurrahman Rifai", "Reza Effendi","Breda Taftayani", "Ilham Akbar", "Jafar Haritsah"};

    ListView listNama;      // Membuat variabel ListView
    Button mulaiAsyncTask;        // Membuat variabel Button

    // Membuat variabel Parceable
    // Parceable merupakan sebuah kontainer yang menampung objek untuk bisa disimpan dan diambil kembali pada sebuah "Parcel".
    private static Parcelable mListViewScrollPos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        listNama = findViewById(R.id.list_nama);
        mulaiAsyncTask = findViewById(R.id.btn_mulai);

        // Melakukan set adapter array
        listNama.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));

        // Restore the ListView position
        if (mListViewScrollPos != null)
            listNama.onRestoreInstanceState(mListViewScrollPos);
    }

    public void mulaiAsyncTask(View view) {

        // Memanggil class mytask (inner class) dan mengeksekusinya
        new mytask().execute();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the ListView position
        mListViewScrollPos = listNama.onSaveInstanceState();
    }



    //------------------------------------ Inner Class untuk memproses AsyncTask -------------------------------------------------

    class mytask extends AsyncTask<Void,String,String> {

        ArrayAdapter<String> mAdapter;      // Membuat variabel ArrayAdapter
        ProgressDialog progressDialog;      // Membuat ProgressDialog
        int count;      // Membuat variabel untuk menampung perhitungan jumlah mahasiswa nantinya


        @Override
        protected void onPreExecute() {

            mAdapter = (ArrayAdapter<String>)listNama.getAdapter();     // Mengambil Adapter dari Array tersebut

            progressDialog = new ProgressDialog(ListNamaMahasiswa.this);        // Membuat object ProgressDialog

            progressDialog.setTitle("Loading Data");        // Melakukan set judul ProgressDialog
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);       // Melakukan set style ProgressDialog
            progressDialog.setMax(15);      // Melakukan set maksimal jumlah ProgressDialog yang diproses
            progressDialog.setProgress(0);      // Melakukan set intial ProgressDialog sebelum diproses

            progressDialog.show();      // Menampilkan ProgressDialog
            count = 0;      // Memastikan bahwa hitungan sebelum di eksekusi adalah 0

        }

        @Override
        protected String doInBackground(Void... voids) {

            // Membuat perulangan untuk memunculkan nama mahasiswa
            for (String namamhs : namaMahasiswa){
                publishProgress(namamhs);

                try {
                    Thread.sleep(100);      // Thread Sleep = 100 mscnd each progress
                } catch (InterruptedException e) {
                    e.printStackTrace();            // print Error
                }
            }

            return "Proses Selesai";        // Mengembalikan nilai dengan tulisan
        }

        @Override
        protected void onProgressUpdate(String... values) {

            mAdapter.add(values[0]);        // Adapter array memluai dari array 0
            count++;        // count bertambah pada setiap kali ProgressDialog diupdate/dijalankan
            progressDialog.setProgress(count);      // Melakukan set count di dalam ProgressDialog
        }

        @Override
        protected void onPostExecute(String result) {

            // Menampilkan nilai dari return yang ada di method doInBackground
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            progressDialog.hide();      // Menghilangkan ProgressDialog jika proses nya complete
        }

    }

}
