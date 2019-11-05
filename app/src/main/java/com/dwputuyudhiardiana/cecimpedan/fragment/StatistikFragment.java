package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Hasil_User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class StatistikFragment extends Fragment {

    private final ArrayList<model_tb_jawaban_user> model_jawaban_user = new ArrayList<>();
    private TextView statistik_tanggal,statistik_jawabanbenar,statistik_totalskor,statistik_totalskorkeseluruhan,statistik_totalpermainan,statistik_total_jawaban_benar;
    private ImageView statistik_bintang1,statistik_bintang2,statistik_bintang3;
    private int totalskor,totaljawabanbenar,totalsoal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        proto_DBHelper_Tabel_Hasil_User hasiluser = new proto_DBHelper_Tabel_Hasil_User(getContext());
        List<model_tb_jawaban_user> hasil = hasiluser.dapatkanJawabanUser(user.getDisplayName());
        for (model_tb_jawaban_user hasillist : hasil) {
            model_jawaban_user.add(hasillist);
            totalskor = Integer.valueOf(hasillist.getnilaiUserUser()) + totalskor;
            totaljawabanbenar = Integer.valueOf(hasillist.gettotaljawabanbenarUser()) + totaljawabanbenar;
            totalsoal = Integer.valueOf(hasillist.getdetailTotalSoal());
        }

        View root = inflater.inflate(R.layout.fragment_statistik, container, false);

        statistik_tanggal = root.findViewById(R.id.statistik_tanggal);
        statistik_jawabanbenar = root.findViewById(R.id.statistik_jawabanbenar);
        statistik_totalskor = root.findViewById(R.id.statistik_totalskor);
        statistik_totalskorkeseluruhan = root.findViewById(R.id.statistik_total_skor);
        statistik_totalpermainan = root.findViewById(R.id.statistik_total_permainan);
        statistik_total_jawaban_benar = root.findViewById(R.id.statistik_total_jawaban_benar);
        statistik_bintang1 = root.findViewById(R.id.statistik_bintang1);
        statistik_bintang2 = root.findViewById(R.id.statistik_bintang2);
        statistik_bintang3 = root.findViewById(R.id.statistik_bintang3);

        if(model_jawaban_user.size() == 0){
            statistik_tanggal.setText("Belum ada permainan");
            statistik_totalskor.setText("");
            statistik_totalskorkeseluruhan.setText(String.valueOf(0));
            statistik_totalpermainan.setText(String.valueOf(0));
            statistik_total_jawaban_benar.setText(String.valueOf(0));
        }else{
            statistik_tanggal.setText(model_jawaban_user.get(0).gettanggalbermainUser());
            statistik_jawabanbenar.setText(model_jawaban_user.get(0).gettotaljawabanbenarUser());
            statistik_totalskor.setText(model_jawaban_user.get(0).getnilaiUserUser());
            statistik_totalskorkeseluruhan.setText(String.valueOf(totalskor));
            statistik_totalpermainan.setText(String.valueOf(model_jawaban_user.size()));
            statistik_total_jawaban_benar.setText(String.valueOf(totaljawabanbenar));
            int skoring = totaljawabanbenar * 100 / totalsoal;
            if(skoring>=0 && skoring <= 10){
                statistik_bintang1.setVisibility(View.GONE);
                statistik_bintang2.setVisibility(View.GONE);
                statistik_bintang3.setVisibility(View.GONE);
            }else if(skoring>=20 && skoring <= 30){
                statistik_bintang1.setVisibility(View.VISIBLE);
                statistik_bintang2.setVisibility(View.GONE);
                statistik_bintang3.setVisibility(View.GONE);
            }else if(skoring>=40 && skoring <= 80){
                statistik_bintang1.setVisibility(View.VISIBLE);
                statistik_bintang2.setVisibility(View.VISIBLE);
                statistik_bintang3.setVisibility(View.GONE);
            }else if(skoring>=90 && skoring <= 100){
                statistik_bintang1.setVisibility(View.VISIBLE);
                statistik_bintang2.setVisibility(View.VISIBLE);
                statistik_bintang3.setVisibility(View.VISIBLE);
            }
        }



        return root;
    }
}
