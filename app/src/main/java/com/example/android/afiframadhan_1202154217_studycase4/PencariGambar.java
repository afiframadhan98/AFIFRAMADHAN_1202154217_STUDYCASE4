package com.example.android.afiframadhan_1202154217_studycase4;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;

public class PencariGambar extends AppCompatActivity {

    EditText url;           // Inisialisasi variabel EditText
    Button cariGambar;          // Insialisasi variabel Button
    ImageView image;       // Inisialisasi variabel ImageView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencari_gambar);

        url = findViewById(R.id.linkgambar);
        cariGambar = findViewById(R.id.btnmencari);
        image = findViewById(R.id.gambar);

    }

    public void cariGambar(View view) {
        String URLGambar = url.getText().toString();        // Mengubah text menjadi String
        new DownloadImage().execute(URLGambar);     // Membuat object Image yang didownload di Internet
    }




    // --------------------------------------- Inner Class untuk memproses AsyncTask ---------------------------------------------

    class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... voids) {
            String imageURL = voids[0];

            Bitmap bitmap = null;       // Menginisalisasikan object bitmap dengan null
            try {
                InputStream input = new java.net.URL(imageURL).openStream();        // Mendownload gambar dari URL
                bitmap = BitmapFactory.decodeStream(input);         // Menjadikan input dari URL ke Bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);       // Melakukan set Bitmap ke dalam ImageView yang sudah disediakan
        }
    }
}
