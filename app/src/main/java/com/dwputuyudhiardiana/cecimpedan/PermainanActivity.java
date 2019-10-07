package com.dwputuyudhiardiana.cecimpedan;


import android.content.Intent;
import android.os.CountDownTimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.flexbox.FlexboxLayout;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Hasil_User;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.prototipe.proto_animation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PermainanActivity extends AppCompatActivity implements View.OnClickListener {

    private final proto_DBHelper_Tabel_Hasil_User DBHelper_HASILUSER = new proto_DBHelper_Tabel_Hasil_User();
    private char[] jawabanArrayAcak;
    private CountDownTimer waktususun_huruf;
    private int posisisoal;
    private int totalsoal;
    private int totalskor;
    private int totaljawabanbenar;
    private TextView susun_huruf_pertanyaan,susun_huruf_skor,susun_huruf_waktu,susun_huruf_level,susun_huruf_posisi_soal;
    private boolean waktu_habis;
    private final ArrayList<model_tb_cecimpedan> model_susun_huruf = new ArrayList<>();
    private long sisaWaktu;
    private AlertDialog.Builder builderDialog;
    private ProgressBar pb_posisi;
    private FlexboxLayout susun_huruf_kata;
    private FlexboxLayout susun_huruf_kotakjawaban;
    private String teks_jawaban = "";
    private final proto_DBHelper_Tabel_Cecimpedan susun_huruf = new proto_DBHelper_Tabel_Cecimpedan();
    JSONObject hasil_jawaban;
    JSONArray hasil_soal_array,hasil_jawaban_array,hasil_jawaban_pemain_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permainan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_permainan);
        if (toolbar == null) return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_silang);

        builderDialog              = new AlertDialog.Builder(this,R.style.KotakDialogRounded);
        susun_huruf_level          = findViewById(R.id.susun_huruf_level);
        susun_huruf_skor           = findViewById(R.id.susun_huruf_skor);
        susun_huruf_kata           = findViewById(R.id.susun_huruf_kata);
        susun_huruf_kotakjawaban   = findViewById(R.id.susun_huruf_kotakjawaban);
        susun_huruf_pertanyaan     = findViewById(R.id.susun_huruf_pertanyaan);
        susun_huruf_waktu          = findViewById(R.id.susun_huruf_waktu);
        susun_huruf_posisi_soal    = findViewById(R.id.susun_huruf_posisi_soal);

        Button btn_bersihkan = findViewById(R.id.btn_bersihkan);
        Button btn_acak      = findViewById(R.id.btn_acak);
        Button btn_cek       = findViewById(R.id.btn_cek);

        pb_posisi            = findViewById(R.id.susun_huruf_pb);

        btn_acak.setOnClickListener(this);
        btn_bersihkan.setOnClickListener(this);
        btn_cek.setOnClickListener(this);

        hasil_jawaban       = new JSONObject();
        hasil_soal_array    = new JSONArray();
        hasil_jawaban_array = new JSONArray();
        hasil_jawaban_pemain_array = new JSONArray();

        totalsoal = 10;
        jawaban();

    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        waktususun_huruf.cancel();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        waktususun_huruf.cancel();
        return true;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_cek:
                if(teks_jawaban.equals(String.valueOf(model_susun_huruf.get(posisisoal).getJawabanKuis()))){
                    dialogStatusJawaban("benar");
                }else if(teks_jawaban.equals("")){
                    dialogStatusJawaban("kosong");
                }else if(waktu_habis){
                    dialogStatusJawaban("waktuhabis");
                }else{
                    dialogStatusJawaban("salah");
                }
                break;
            case R.id.btn_acak:
                teks_jawaban = "";
                kotakkatajawaban();
                break;
            case R.id.btn_bersihkan:
                teks_jawaban = "";
                resetJawaban();
                break;
        }
    }

    private void waktususun_huruf(long waktu){
        waktususun_huruf = new CountDownTimer(waktu,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                sisaWaktu = millisUntilFinished;
                String waktususun_huruf = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                susun_huruf_waktu.setText(waktususun_huruf);
            }

            @Override
            public void onFinish() {
                dialogStatusJawaban("waktuhabis");
                waktususun_huruf.cancel();
                waktu_habis = true;
            }
        };

        waktususun_huruf.start();
    }

    private void lanjutwaktususun_huruf(){
        waktususun_huruf(sisaWaktu);
    }

    private void updateSkor(){
        int from = posisisoal * 100 / totalsoal;
        proto_animation anim = new proto_animation(pb_posisi, from, from);
        anim.setDuration(1000);
        pb_posisi.startAnimation(anim);
        susun_huruf_level.setText("Soal : " + (posisisoal + 1));
        susun_huruf_skor.setText("Skor : " + totalskor);
        susun_huruf_posisi_soal.setText((posisisoal + 1) + "/10");
    }


    private void jawabanPengguna(String statusJawaban){
        try {
            //Save Soal Acak Yang Akan disimpan pada JSON
            hasil_jawaban.put("soalJawaban", hasil_soal_array.put(String.valueOf(model_susun_huruf.get(posisisoal).getIdKuis())));

            //Save statusJawaban berdasarkan Soal Yang Akan disimpan pada JSON
            hasil_jawaban.put("statusJawaban", hasil_jawaban_array.put(statusJawaban));
            hasil_jawaban.put("statusJawabanPemain", hasil_jawaban_pemain_array.put(teks_jawaban));
            String arrayList = hasil_jawaban.toString();
            Log.d("DATABASE", arrayList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void dialogStatusJawaban(final String statusJawaban){
        waktususun_huruf.cancel();

        final View dialogViewStatusJawaban      = LayoutInflater.from(this).inflate(R.layout.modal_status_jawaban, null);
        Button btn_lanjut                       = dialogViewStatusJawaban.findViewById(R.id.btn_detail_hasil);
        TextView txt_statusjawaban              = dialogViewStatusJawaban.findViewById(R.id.mshasil_hasil);
        TextView txt_desstatusjawaban           = dialogViewStatusJawaban.findViewById(R.id.msjawaban_des);
        LottieAnimationView icon_statusjawaban  = dialogViewStatusJawaban.findViewById(R.id.bintang_kedua_hasil);

        builderDialog.setView(dialogViewStatusJawaban);

        final AlertDialog Dialog = builderDialog.create();
        Dialog.setCancelable(false);

        String teks_tombol_lanjut;
        String teks_desstatusjawaban;
        String teks_statusjawaban;
        switch (statusJawaban) {
            case "benar":
                teks_statusjawaban = "Jawaban Benar";
                teks_desstatusjawaban = "Jawaban Benar Lanjut Ke Soal Berikut.";
                teks_tombol_lanjut = "Lanjut";
                icon_statusjawaban.setAnimation(R.raw.modal_jawabanbenar);
                jawabanPengguna("Benar");
                break;
            case "salah":
                teks_statusjawaban = "Maaf, Jawaban Salah";
                teks_desstatusjawaban = "Wah.. Sepertinya Jawaban Kamu Salah, Ulangi?";
                teks_tombol_lanjut = "Ulangi";
                icon_statusjawaban.setAnimation(R.raw.modal_jawabansalah);
                break;
            case "waktuhabis":
                teks_statusjawaban = "Maaf, Waktu Habis";
                teks_desstatusjawaban = "Wah.. Sepertinya Waktu Untuk Menjawab Habis, Lanjut Ke Soal Berikut";
                teks_tombol_lanjut = "Lanjut";
                icon_statusjawaban.setAnimation(R.raw.modal_jawabansalah);
                jawabanPengguna("Salah");
                break;
            case "selesai":
                teks_statusjawaban = "Finish";
                teks_desstatusjawaban = "Wah.. Sepertinya Kamu Sudah Menjawab Semua Pertanyaan";
                teks_tombol_lanjut = "Baik";
                icon_statusjawaban.setAnimation(R.raw.modal_jawabanbenar);
                break;
            default:
                teks_statusjawaban = "Kosong?";
                teks_desstatusjawaban = "Sepertinya Kamu Belum Memasukan Jawaban, Masukan Jawaban Kamu Ya!";
                teks_tombol_lanjut = "Baik";
                icon_statusjawaban.setAnimation(R.raw.modal_jawabankosong);
                break;
        }

        txt_statusjawaban.setText(teks_statusjawaban);
        txt_desstatusjawaban.setText(teks_desstatusjawaban);
        btn_lanjut.setText(teks_tombol_lanjut);

        Dialog.show();

        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (statusJawaban) {
                    case "benar":
                        posisisoal++;
                        totaljawabanbenar = totaljawabanbenar+1;
                        totalskor = totalskor + 10;
                        teks_jawaban = "";
                        clearKotakJawaban();
                        jawaban();
                        Dialog.cancel();
                        break;
                    case "waktuhabis":
                        posisisoal++;
                        teks_jawaban = "";
                        clearKotakJawaban();
                        jawaban();
                        Dialog.cancel();
                        break;
                    case "salah":
                        lanjutwaktususun_huruf(); //Lanjutkan Waktu

                        resetJawaban();
                        Dialog.cancel();
                        break;
                    case "selesai":
                        if (Dialog.isShowing())
                            Dialog.dismiss();
                        hasil();
                        break;
                    default:
                        Dialog.cancel();
                        break;
                }
            }
        });
    }

    private void hasil() {
        builderDialog.setCancelable(false);
        View modalHasil = LayoutInflater.from(this).inflate(R.layout.modal_hasil_permainan, null);
        Button btn_detail_hasil = modalHasil.findViewById(R.id.btn_detail_hasil);
        Button btn_kembali_hasil = modalHasil.findViewById(R.id.btn_kembali_hasil);
        TextView skor_hasil = modalHasil.findViewById(R.id.skor_hasil);
        TextView totaljawaban_benar = modalHasil.findViewById(R.id.totaljawabanbenar_hasil);
        TextView totaljawaban_salah = modalHasil.findViewById(R.id.totaljawabansalah_hasil);
        TextView status_hasil = modalHasil.findViewById(R.id.status_hasil);
        ImageView gagal_hasil = modalHasil.findViewById(R.id.gagal_hasil);
        LinearLayout bintang_hasil = modalHasil.findViewById(R.id.bintang_hasil);

        skor_hasil.setText(String.valueOf(totalskor));
        totaljawaban_benar.setText(totaljawabanbenar + "/" + totalsoal);
        totaljawaban_salah.setText((totalsoal - totaljawabanbenar) + "/" + totalsoal);

        LottieAnimationView bintang_pertama_hasil = modalHasil.findViewById(R.id.bintang_pertama_hasil);
        LottieAnimationView bintang_kedua_hasil = modalHasil.findViewById(R.id.bintang_kedua_hasil);
        LottieAnimationView bintang_ketiga_hasil = modalHasil.findViewById(R.id.bintang_ketiga_hasil);


        if(totalskor>=0 && totalskor <= 10){
            gagal_hasil.setVisibility(View.VISIBLE);
            bintang_hasil.setVisibility(View.GONE);
            status_hasil.setText("Belajar Lagi Ya :D");
        }else if(totalskor>=20 && totalskor <= 30){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
            status_hasil.setText("Lumayan");
        }else if(totalskor>=40 && totalskor <= 80){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
            bintang_kedua_hasil.playAnimation();
            status_hasil.setText("Bagus");
        }else if(totalskor>=90 && totalskor <= 100){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
            bintang_kedua_hasil.playAnimation();
            bintang_ketiga_hasil.playAnimation();
            status_hasil.setText("Luar Biasa");
        }

        builderDialog.setView(modalHasil);
        btn_detail_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahDetail = new Intent(PermainanActivity.this, DetailHasilActivity.class);
                startActivity(pindahDetail);
                finish();
            }
        });

        btn_kembali_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        builderDialog.create();
        builderDialog.show();

        //Save Ke Database
        Date now = new Date();
        SimpleDateFormat dapatkantgl = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String arrayList = hasil_jawaban.toString();
        DBHelper_HASILUSER.tambahHasilUser(getApplicationContext(),new model_tb_jawaban_user(dapatkantgl.format(now),"Susun Huruf",String.valueOf(totalskor),String.valueOf(totaljawabanbenar),arrayList));
    }


    private void resetJawaban(){
        for( int i = 0; i < jawabanArrayAcak.length; i++ )
        {
            final Button resettombol = findViewById(i);
            resettombol.setEnabled(true);
        }
        clearKotakJawaban();
        teks_jawaban = "";
    }

    private void jawaban(){
        if(posisisoal == totalsoal){
            dialogStatusJawaban("selesai");
            waktususun_huruf.cancel();
        }else{
            updateSkor();
            model_susun_huruf.addAll(susun_huruf.dapatkanSoal(getApplicationContext()));
            waktu_habis = false;
            kotakkatajawaban();
            waktususun_huruf(30000);
        }
    }

    private void clearKotakJawaban(){
        susun_huruf_kotakjawaban.removeAllViews();
    }

    private void kotakkatajawaban(){
        susun_huruf_pertanyaan.setText(String.valueOf(model_susun_huruf.get(posisisoal).getSoalKuis()));
        Log.d("DATABASE", String.valueOf(model_susun_huruf.get(posisisoal).getSoalKuis()));
        susun_huruf_kata.removeAllViews();
        clearKotakJawaban();
        jawabanArrayAcak = acakHuruf(String.valueOf(model_susun_huruf.get(posisisoal).getJawabanKuis())).toCharArray();

        for(int i = 0; i < jawabanArrayAcak.length; i++ )
        {
            final int jumlah = i;
            final Button btn_huruf_jawab = new Button(new ContextThemeWrapper(getApplicationContext(), R.style.tombolSusunHuruf));
            btn_huruf_jawab.setId(i);
            btn_huruf_jawab.setStateListAnimator(null);
            btn_huruf_jawab.setText(String.valueOf(jawabanArrayAcak[i]));
            btn_huruf_jawab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_huruf_jawab.setEnabled(false);
                    final TextView txt_jawaban = new TextView(new ContextThemeWrapper(getApplicationContext(), R.style.teksKotakJawaban),null,0);
                    txt_jawaban.setText(String.valueOf(jawabanArrayAcak[jumlah]).toUpperCase());
                    TableRow.LayoutParams txt_jawaban_margin = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f);
                    txt_jawaban_margin.setMargins(2,2,2,2);
                    txt_jawaban.setLayoutParams(txt_jawaban_margin);
                    susun_huruf_kotakjawaban.addView(txt_jawaban);
                    teks_jawaban = teks_jawaban +""+ jawabanArrayAcak[jumlah];
                }
            });
            susun_huruf_kata.addView(btn_huruf_jawab);
        }
    }

    private String acakHuruf(String kataacak){
        List<Character> karakter = new ArrayList<>();
        for(char c:kataacak.toCharArray()){
            karakter.add(c);
        }
        StringBuilder hasil = new StringBuilder(kataacak.length());
        while(karakter.size()!=0){
            int acak = (int)(Math.random()*karakter.size());
            hasil.append(karakter.remove(acak));
        }
        return hasil.toString();
    }
}

