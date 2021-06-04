package com.example.laguku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {

    EditText etJudul,etPenyanyi,etGenre,etTahun,etLirik;
    Button btnSimpan;
    TextView tvJudul, tvPenyanyi;
    private Lagu lagu;
    private AppDatabase database;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setTitle("Detail");


        //Title action bar
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#111111'>Edit</font>"));

        //action bar color
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //arrow action bar
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        etJudul = findViewById(R.id.et_judul);
        btnSimpan = findViewById(R.id.btn_simpan);
        etPenyanyi = findViewById(R.id.et_penyanyi);
        etGenre = findViewById(R.id.et_genre);
        etTahun = findViewById(R.id.et_tahun);
        etLirik = findViewById(R.id.et_lirik);
        tvJudul = findViewById(R.id.tv_judul);
        tvPenyanyi = findViewById(R.id.tv_penyanyi);

        database= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbLagu").build();

        lagu = (Lagu) getIntent().getSerializableExtra("data");
        etJudul.setText(lagu.getJudul());
        etPenyanyi.setText(lagu.getPenyanyi());
        etGenre.setText(lagu.getGenre());
        etTahun.setText(lagu.getTahun_rilis());
        etLirik.setText(lagu.getLirik());
        tvJudul.setText(lagu.getJudul());
        tvPenyanyi.setText(lagu.getPenyanyi());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lagu.setJudul(etJudul.getText().toString());
                lagu.setPenyanyi(etPenyanyi.getText().toString());
                lagu.setGenre(etGenre.getText().toString());
                lagu.setTahun_rilis(etTahun.getText().toString());
                lagu.setLirik(etLirik.getText().toString());

                updateLagu(lagu);
            }
        });

    }


    private void updateLagu(Lagu lagu) {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //Menjalankan proses update data lagu
                return database.laguDAO().updateLagu(lagu);
            }

            @Override
            protected void onPostExecute(Integer status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(EditActivity.this, "Data Lagu Terupdate!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, ReadActivity.class));
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, ReadActivity.class));
        finish();
    }


}