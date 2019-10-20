package com.dwputuyudhiardiana.cecimpedan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_kamus;

import java.util.ArrayList;


public class AdapterKamus extends RecyclerView.Adapter<AdapterKamus.ViewHolder> {

    private ArrayList<model_tb_kamus> Kamus_Lists = new ArrayList<>();

    private final LayoutInflater inflater;
    private final Context context;

    public AdapterKamus(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.item_kamus, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        model_tb_kamus ListKamus = Kamus_Lists.get(position);
        holder.kam_no.setText(String.valueOf(position+1));
        holder.kam_soal.setText(ListKamus.getSoalKamus());
        holder.kam_des.setText(ListKamus.getKatKamus());
        holder.card_view_kamus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity.class);
                int posisi = position+1;
                i.putExtra("soal_kamus", String.valueOf(Kamus_Lists.get(position).getSoalKamus()));
                i.putExtra("no_kamus", "Soal "+posisi);
                i.putExtra("kategori_kamus",String.valueOf(Kamus_Lists.get(position).getKatKamus()));
                i.putExtra("arti_kamus", String.valueOf(Kamus_Lists.get(position).getJawabanKamus()));
                context.startActivity(i);
            }
        });
    }

    public void setListContent(ArrayList<model_tb_kamus> listKamus){
        this.Kamus_Lists = listKamus;
        notifyItemRangeChanged(0, listKamus.size());
    }

    @Override
    public int getItemCount() {
        return Kamus_Lists.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView kam_soal;
        final TextView kam_des;
        final TextView kam_no;
        final CardView card_view_kamus;

        ViewHolder(View itemView) {
            super(itemView);
            kam_soal     = itemView.findViewById(R.id.kamus_soal);
            kam_des      = itemView.findViewById(R.id.kamus_kategori);
            kam_no       = itemView.findViewById(R.id.kamus_nomor);
            card_view_kamus = itemView.findViewById(R.id.card_view_kamus);

        }
    }
}
