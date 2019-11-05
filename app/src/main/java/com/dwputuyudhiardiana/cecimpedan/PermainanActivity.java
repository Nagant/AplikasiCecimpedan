package com.dwputuyudhiardiana.cecimpedan;


import android.content.Intent;
import android.os.CountDownTimer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.dwputuyudhiardiana.cecimpedan.prototipe.proto_permainan;
import com.google.android.flexbox.FlexboxLayout;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.prototipe.proto_animation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PermainanActivity extends AppCompatActivity implements View.OnClickListener {


    private char[] jawabanArrayAcak;
    private CountDownTimer waktususun_huruf;
    List<Integer> soalacak = new ArrayList<>();
    private int posisisoal;
    private int totalsoal;
    private int totalskor;
    private int totaljawabanbenar;
    private int idsoal;
    private TextView susun_huruf_pertanyaan,susun_huruf_skor,susun_huruf_waktu,susun_huruf_level,susun_huruf_posisi_soal;
    private boolean waktu_habis;
    private final ArrayList<model_tb_cecimpedan> model_susun_huruf = new ArrayList<>();
    private long sisaWaktu;
    private AlertDialog.Builder builderDialog;
    private ProgressBar pb_posisi;
    private FlexboxLayout susun_huruf_kata;
    private FlexboxLayout susun_huruf_kotakjawaban;
    private String teks_jawaban = "";
    private proto_DBHelper_Tabel_Cecimpedan susun_huruf;
    private proto_permainan proto_permainan;
    private boolean pause;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permainan);

        Toolbar toolbar = findViewById(R.id.toolbar_permainan);
        if (toolbar == null) return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_silang);
        user = FirebaseAuth.getInstance().getCurrentUser();
        proto_permainan = new proto_permainan(getApplicationContext());
        susun_huruf = new proto_DBHelper_Tabel_Cecimpedan(getApplicationContext());

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


        String level = getIntent().getStringExtra("level");
        if(level.equals("mudah")){
            totalsoal = 10;
        }else if(level.equals("sedang")){
            totalsoal = 20;
        }else if(level.equals("susah")){
            totalsoal = 30;
        }
        Log.d("tag",level);
        int[] soal = proto_permainan.AcakSoal(totalsoal);
        for(int nomorsoal:soal){
            soalacak.add(nomorsoal);
        }

        jawaban();
        pause = false;
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        waktususun_huruf.cancel();
    }

    @Override
    protected void onPause () {
        super.onPause();
        waktususun_huruf.cancel();
        pause = true;
    }

    @Override
    protected void onResume () {
        super.onResume();
        if(pause){
            permainanterhenti();
            pause = false;
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            berhenti();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        berhenti();
        return true;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_cek:
                if(teks_jawaban.equals(String.valueOf(model_susun_huruf.get(soalacak.get(posisisoal)).getJawabanKuis()))){
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

    private void permainanterhenti(){
        final View modalTerhenti = LayoutInflater.from(this).inflate(R.layout.modal_terhenti, null);
        Button btn_lanjut = modalTerhenti.findViewById(R.id.btn_lanjut_msterhenti);
        Button btn_kembali = modalTerhenti.findViewById(R.id.btn_kembali_msterhenti);
        builderDialog.setView(modalTerhenti);
        final AlertDialog DialogTerhenti = builderDialog.create();
        DialogTerhenti.setCancelable(false);
        btn_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanjutwaktususun_huruf();
                DialogTerhenti.cancel();
            }
        });

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waktususun_huruf.cancel();
                DialogTerhenti.cancel();
                finish();
            }
        });
            DialogTerhenti.show();
    }


    private void berhenti(){
        final View modalBerhenti = LayoutInflater.from(this).inflate(R.layout.modal_keluar, null);
        Button btn_ya = modalBerhenti.findViewById(R.id.btn_ya_msberhenti);
        Button btn_tidak = modalBerhenti.findViewById(R.id.btn_tidak_msberhenti);
        builderDialog.setView(modalBerhenti);
        final AlertDialog DialogBerhenti = builderDialog.create();
        DialogBerhenti.setCancelable(false);
        btn_tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanjutwaktususun_huruf();
                DialogBerhenti.cancel();
            }
        });

        btn_ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waktususun_huruf.cancel();
                DialogBerhenti.cancel();
                finish();
            }
        });
        DialogBerhenti.show();
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
        susun_huruf_posisi_soal.setText((posisisoal + 1) + "/" + totalsoal);
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
                teks_statusjawaban = getResources().getString(R.string.modal_jawaban_benar);
                teks_desstatusjawaban = getResources().getString(R.string.modal_jawaban_benar_deskripsi);
                teks_tombol_lanjut = getResources().getString(R.string.tombol_modal_lanjut);
                icon_statusjawaban.setAnimation(R.raw.modal_jawabanbenar);

                break;
            case "salah":
                teks_statusjawaban = getResources().getString(R.string.modal_jawaban_salah);
                teks_desstatusjawaban = getResources().getString(R.string.modal_jawaban_salah_deskripsi);
                teks_tombol_lanjut = getResources().getString(R.string.tombol_modal_ulang);
                icon_statusjawaban.setAnimation(R.raw.modal_jawabansalah);
                break;
            case "waktuhabis":
                teks_statusjawaban = getResources().getString(R.string.modal_jawaban_waktuhabis);
                teks_desstatusjawaban = getResources().getString(R.string.modal_jawaban_waktuhabis_deskripsi);
                teks_tombol_lanjut = getResources().getString(R.string.tombol_modal_lanjut);
                icon_statusjawaban.setAnimation(R.raw.modal_jawabansalah);

                break;
            case "selesai":
                teks_statusjawaban = getResources().getString(R.string.modal_jawaban_selesai);
                teks_desstatusjawaban = getResources().getString(R.string.modal_jawaban_selesai_deskripsi);
                teks_tombol_lanjut = getResources().getString(R.string.tombol_modal_baik);
                icon_statusjawaban.setAnimation(R.raw.modal_jawabanbenar);
                break;
            default:
                teks_statusjawaban = getResources().getString(R.string.modal_jawaban_kosong);
                teks_desstatusjawaban = getResources().getString(R.string.modal_jawaban_kosong_deskripsi);
                teks_tombol_lanjut = getResources().getString(R.string.tombol_modal_baik);
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
                        proto_permainan.jawabanPengguna(idsoal,"Benar",teks_jawaban);
                        totaljawabanbenar = totaljawabanbenar+1;
                        totalskor = totalskor + 10;
                        teks_jawaban = "";
                        clearKotakJawaban();
                        jawaban();
                        Dialog.cancel();
                        break;
                    case "waktuhabis":
                        posisisoal++;
                        proto_permainan.jawabanPengguna(idsoal,"Salah",teks_jawaban);
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
        View modalHasil = LayoutInflater.from(this).inflate(R.layout.modal_hasil_permainan, null);
        String status_nilai = null;
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

        int skoring = totaljawabanbenar * 100 / totalsoal;
        if(skoring>=0 && skoring <= 10){
            gagal_hasil.setVisibility(View.VISIBLE);
            bintang_hasil.setVisibility(View.GONE);
            status_nilai = getResources().getString(R.string.modal_status_gagal);
        }else if(skoring>=20 && skoring <= 30){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
           status_nilai = getResources().getString(R.string.modal_status_nilai_rendah);
        }else if(skoring>=40 && skoring <= 80){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
            bintang_kedua_hasil.playAnimation();
           status_nilai = getResources().getString(R.string.modal_status_nilai_menengah);
        }else if(totalskor>=90 && totalskor <= 100){
            gagal_hasil.setVisibility(View.GONE);
            bintang_pertama_hasil.playAnimation();
            bintang_kedua_hasil.playAnimation();
            bintang_ketiga_hasil.playAnimation();
           status_nilai = getResources().getString(R.string.modal_status_nilai_tinggi);
        }
        status_hasil.setText(status_nilai);
        builderDialog.setView(modalHasil);
        final AlertDialog DialogHasil = builderDialog.create();
        DialogHasil.show();
        btn_detail_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHasil.cancel();
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


        proto_permainan.simpanHasil(user.getDisplayName(),String.valueOf(totalskor),String.valueOf(totaljawabanbenar),String.valueOf(totalsoal));
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
            model_susun_huruf.addAll(susun_huruf.dapatkanSoal());
            waktu_habis = false;
            kotakkatajawaban();
            waktususun_huruf(30000);
        }
    }

    private void clearKotakJawaban(){
        susun_huruf_kotakjawaban.removeAllViews();
    }

    private void kotakkatajawaban(){
        susun_huruf_pertanyaan.setText(String.valueOf(model_susun_huruf.get(soalacak.get(posisisoal)).getSoalKuis()));
        idsoal = model_susun_huruf.get(soalacak.get(posisisoal)).getIdKuis();
        susun_huruf_kata.removeAllViews();
        clearKotakJawaban();
        jawabanArrayAcak = proto_permainan.acakHuruf(String.valueOf(model_susun_huruf.get(soalacak.get(posisisoal)).getJawabanKuis())).toCharArray();

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


}

