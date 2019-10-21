package com.dwputuyudhiardiana.cecimpedan.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_detail_jawaban;

import java.util.ArrayList;

public class AdapterDetailHasil extends RecyclerView.Adapter<AdapterDetailHasil.ViewHolder> {


    private ArrayList<model_tb_detail_jawaban> Hasil_Lists;

    private final LayoutInflater inflater;
    private final Context context;

    public AdapterDetailHasil(Context context, ArrayList<model_tb_detail_jawaban> Hasil_Lists){
        this.context = context;
        this.Hasil_Lists = Hasil_Lists;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterDetailHasil.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.item_hasil, parent, false);

        return new AdapterDetailHasil.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDetailHasil.ViewHolder holder, final int position) {
        model_tb_detail_jawaban ListHasil = Hasil_Lists.get(position);
        holder.no_detail_hasil.setText(String.valueOf(position+1));
        holder.soal_detail_hasil.setText(ListHasil.getsoalDetail());
        holder.jawaban_detail_hasil.setText(ListHasil.getjawabanDetail());
        holder.jawaban_detail_hasil_pemain.setText(ListHasil.getjawabanpemainDetail());
        int background;
            if(ListHasil.getjawabanstatusDetail().equals("Benar")){
                background = context.getResources().getIdentifier("ic_benar", "drawable", context.getPackageName());
                ImageViewCompat.setImageTintList( holder.icon_detail_hasil, ColorStateList.valueOf(ContextCompat.getColor(context, R.color.warnaHijau)));
                holder.layout_jawaban_pemain.setVisibility(View.INVISIBLE);
            }else{
                background = context.getResources().getIdentifier("ic_silang", "drawable", context.getPackageName());
                ImageViewCompat.setImageTintList( holder.icon_detail_hasil, ColorStateList.valueOf(ContextCompat.getColor(context, R.color.warnaMerah)));
                holder.layout_jawaban_pemain.setVisibility(View.VISIBLE);
                if(ListHasil.getjawabanpemainDetail().equals("")){
                    holder.jawaban_detail_hasil_pemain.setText("Tidak Terisi");
                }
            }
        holder.icon_detail_hasil.setImageResource(background);

    }

    @Override
    public int getItemCount() {
        return Hasil_Lists.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView no_detail_hasil;
        final TextView soal_detail_hasil;
        final TextView jawaban_detail_hasil;
        final TextView jawaban_detail_hasil_pemain;
        final LinearLayout layout_jawaban_pemain;
        final ImageView icon_detail_hasil;

        ViewHolder(View itemView) {
            super(itemView);
            no_detail_hasil        = itemView.findViewById(R.id.nomor_detail_hasil);
            soal_detail_hasil      = itemView.findViewById(R.id.soal_detail_hasil);
            jawaban_detail_hasil   = itemView.findViewById(R.id.jawaban_detail_hasil);
            jawaban_detail_hasil_pemain   = itemView.findViewById(R.id.jawaban_detail_hasil_pemain);
            layout_jawaban_pemain = itemView.findViewById(R.id.layout_jawaban_pemain);
            icon_detail_hasil       = itemView.findViewById(R.id.icon_detail_hasil);

        }
    }

}
