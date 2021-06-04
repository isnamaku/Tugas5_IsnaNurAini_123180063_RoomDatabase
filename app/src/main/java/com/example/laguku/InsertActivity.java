package com.example.laguku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText judul,penyanyi,genre,tahun,lirik;
    private AppDatabase database;
    private Button btnTambah, btnListLagu;
    private int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        //Title action bar
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#111111'>Tambah</font>"));

        //action bar color
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //arrow action bar
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        judul = findViewById(R.id.et_judul);
        penyanyi = findViewById(R.id.et_penyanyi);
        genre = findViewById(R.id.et_genre);
        tahun = findViewById(R.id.et_tahun);
        lirik = findViewById(R.id.et_lirik);
        btnTambah = findViewById(R.id.btn_tambah);
        btnListLagu = findViewById(R.id.btn_lihat);

        btnTambah.setOnClickListener(this);
        btnListLagu.setOnClickListener(this);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbLagu") //Nama File Database yang akan disimpan
                .build();
    }

    private void insertLagu(final Lagu lagu){
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                long status = database.laguDAO().insertLagu(lagu);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(InsertActivity.this, "Lagu berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tambah :
                if(judul.getText().toString().isEmpty()){
                    Toast.makeText(InsertActivity.this,"Judul lagu tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }else{
                    Lagu dataLagu = new Lagu();
                    //memasukkan data input ke dalam database
                    dataLagu.setId(id);
                    dataLagu.setJudul(judul.getText().toString());
                    dataLagu.setPenyanyi(penyanyi.getText().toString());
                    dataLagu.setGenre(genre.getText().toString());
                    dataLagu.setTahun_rilis(tahun.getText().toString());
                    dataLagu.setLirik(lirik.getText().toString());
                    insertLagu(dataLagu);


                    //Reset edit text
                    judul.setText("");
                    penyanyi.setText("");
                    genre.setText("");
                    tahun.setText("");
                    lirik.setText("");

                }
                break;

            case R.id.btn_lihat :
                startActivity(new Intent(InsertActivity.this, ReadActivity.class));
                break;
        }

    }
}
