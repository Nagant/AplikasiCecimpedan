package com.dwputuyudhiardiana.cecimpedan.adapter;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_detail_jawaban;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;

import java.util.ArrayList;


public class AdapterDaftarNilai extends RecyclerView.Adapter<AdapterDaftarNilai.ViewHolder> {

    private ArrayList<model_tb_jawaban_user> Jawaban_User_Lists = new ArrayList<>();

    private final LayoutInflater inflater;
    private final Context context;

    public AdapterDaftarNilai(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.item_nilai, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        model_tb_jawaban_user ListJawabanUser = Jawaban_User_Lists.get(position);
        holder.nilai_penomoran.setText(String.valueOf(position+1));
        holder.nilai_posisi.setText("Nilai " + "#" + Integer.valueOf(position+1));
        holder.nilai_tanggal.setText(ListJawabanUser.gettanggalbermainUser());
        holder.nilai_totalskor.setText(ListJawabanUser.getnilaiUserUser());
        holder.nilai_jawabanbenar.setText(ListJawabanUser.gettotaljawabanbenarUser());
        int totalskor = Integer.valueOf(ListJawabanUser.getnilaiUserUser());
        if(totalskor>=0 && totalskor <= 10){
            holder.nilai_bintang1.setVisibility(View.GONE);
            holder.nilai_bintang2.setVisibility(View.GONE);
            holder.nilai_bintang3.setVisibility(View.GONE);
        }else if(totalskor>=20 && totalskor <= 30){
            holder.nilai_bintang1.setVisibility(View.VISIBLE);
            holder.nilai_bintang2.setVisibility(View.GONE);
            holder.nilai_bintang3.setVisibility(View.GONE);
        }else if(totalskor>=40 && totalskor <= 80){
            holder.nilai_bintang1.setVisibility(View.VISIBLE);
            holder.nilai_bintang2.setVisibility(View.VISIBLE);
            holder.nilai_bintang3.setVisibility(View.GONE);
        }

        holder.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int background;
                if(holder.body.getVisibility() == View.GONE) {
                    holder.body.setVisibility(View.VISIBLE);
                    background = context.getResources().getIdentifier("ic_tutup_nilai", "drawable", context.getPackageName());
                }else {
                    holder.body.setVisibility(View.GONE);
                    background = context.getResources().getIdentifier("ic_buka_nilai", "drawable", context.getPackageName());
                }

                holder.toggle.setImageResource(background);
            }
        });

    }

    public void setListContent(ArrayList<model_tb_jawaban_user> listJawabanUser){
        this.Jawaban_User_Lists = listJawabanUser;
        notifyItemRangeChanged(0, listJawabanUser.size());
    }

    @Override
    public int getItemCount() {
        return Jawaban_User_Lists.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nilai_jawabanbenar;
        final TextView nilai_totalskor;
        final TextView nilai_posisi;
        final TextView nilai_penomoran;
        final TextView nilai_tanggal;
        final View body;
        final ImageView toggle,nilai_bintang1,nilai_bintang2,nilai_bintang3;

        ViewHolder(View itemView) {
            super(itemView);
            this.body   = itemView.findViewById(R.id.body);
            this.toggle = itemView.findViewById(R.id.toggle);
            this.nilai_jawabanbenar = itemView.findViewById(R.id.nilai_jawabanbenar);
            this.nilai_totalskor = itemView.findViewById(R.id.nilai_totalskor);
            this.nilai_posisi = itemView.findViewById(R.id.nilai_posisi);
            this.nilai_penomoran = itemView.findViewById(R.id.nilai_nomor);
            this.nilai_tanggal = itemView.findViewById(R.id.nilai_tanggal);
            this.nilai_bintang1 = itemView.findViewById(R.id.nilai_bintang1);
            this.nilai_bintang2 = itemView.findViewById(R.id.nilai_bintang2);
            this.nilai_bintang3 = itemView.findViewById(R.id.nilai_bintang3);


            CardView card = itemView.findViewById(R.id.card_view_nilai);
            card.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

            ConstraintLayout contents = itemView.findViewById(R.id.contents);
            contents.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            contents.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);


        }
    }
}

