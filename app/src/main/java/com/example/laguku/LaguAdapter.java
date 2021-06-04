package com.example.laguku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class LaguAdapter extends RecyclerView.Adapter<LaguAdapter.ViewHolder> {
    //Deklarasi Variable
    private ArrayList<Lagu> daftarLagu;
    private AppDatabase appDatabase;
    private Context context;


    public LaguAdapter(ArrayList<Lagu> daftarLagu, Context context) {

        //Inisialisasi data
        this.daftarLagu = daftarLagu;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbLagu").allowMainThreadQueries().build();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //Deklarasi View yang akan digunakan
        private TextView judul,penyanyi;
        private  int id;
        private CardView item;

        ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul_lagu);
            penyanyi = itemView.findViewById(R.id.penyanyi_lagu);
            item = itemView.findViewById(R.id.cvMain);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.data_rv, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String getJudul = daftarLagu.get(position).getJudul();
        String getPenyanyi = daftarLagu.get(position).getPenyanyi();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.judul.setText(getJudul);
        holder.penyanyi.setText(getPenyanyi);

        holder.itemView.findViewById(R.id.btn_hapus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] pilihanHapus = {"Hapus", "Batal"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Hapus lagu ini?")
                        .setItems(pilihanHapus, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        appDatabase.laguDAO().hapusLagu(daftarLagu.get(position));
                                        daftarLagu.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, daftarLagu.size());
                                        Toast.makeText(context, "Lagu Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                        break;

                                    case 1:
                                        dialog.cancel();
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
            }
        });

        holder.itemView.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarLagu.get(position)));
                ((Activity)context).finish();

            }
        });
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailActivity.class).putExtra("data", daftarLagu.get(position)));
                ((Activity)context).finish();
            }
        });

    }


    @Override
    public int getItemCount() {

        return daftarLagu.size();
    }





}
