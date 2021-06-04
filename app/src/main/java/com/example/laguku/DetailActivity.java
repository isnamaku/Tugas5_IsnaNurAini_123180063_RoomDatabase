package com.example.laguku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {
    private TextView judul,penyanyi,genre,tahun,lirik;
    private AppDatabase database;
    private Lagu lagu;
    ImageButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //Title action bar
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#111111'>Rincian</font>"));

        //action bar color
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_baseline_arrow_back_ios_new_24);
        upArrow.setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        judul = findViewById(R.id.tv_judul);
        penyanyi = findViewById(R.id.tv_penyanyi);
        genre = findViewById(R.id.tv_genre);
        tahun = findViewById(R.id.tv_tahun);
        lirik = findViewById(R.id.tv_lirik);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbLagu").build();

        getDataLagu();

    }

    private void getDataLagu() {
        lagu = (Lagu) getIntent().getSerializableExtra("data");
        judul.setText(lagu.getJudul());
        penyanyi.setText(lagu.getPenyanyi());
        genre.setText(lagu.getGenre());
        tahun.setText(lagu.getTahun_rilis());
        lirik.setText(lagu.getLirik());
    }

    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(DetailActivity.this, ReadActivity.class));
        finish();
    }





}