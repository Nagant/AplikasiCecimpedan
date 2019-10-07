package com.dwputuyudhiardiana.cecimpedan;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.adapter.AdapterDetailHasil;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_detail_jawaban;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Hasil_User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailHasilActivity extends AppCompatActivity {
    private final proto_DBHelper_Tabel_Hasil_User hasiluser = new proto_DBHelper_Tabel_Hasil_User();
    private final proto_DBHelper_Tabel_Cecimpedan susun_huruf = new proto_DBHelper_Tabel_Cecimpedan();
    private final ArrayList<model_tb_detail_jawaban> model_detail = new ArrayList<>();
    private final ArrayList<model_tb_cecimpedan> model_susun_huruf = new ArrayList<>();
    private final ArrayList<model_tb_jawaban_user> model_jawaban_user = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_hasil);
        if (toolbar == null) return;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_silang);

        List<model_tb_jawaban_user> hasil = hasiluser.dapatkanJawabanUser(getApplicationContext());
        model_jawaban_user.addAll(hasil);


        model_susun_huruf.addAll(susun_huruf.dapatkanSoalSemua(getApplicationContext()));
        String dataHasil = model_jawaban_user.get(0).getdetailjawabanUser(); //Karena SQLHelper di set Descending untuk mendapatkan hasil terakhir
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(dataHasil);
            JSONArray jsonArraySoal,jsonArrayJawaban,jsonArrayJawabanPemain;
            jsonArraySoal = jsonObj.getJSONArray("soalJawaban");
            jsonArrayJawaban = jsonObj.getJSONArray("statusJawaban");
            jsonArrayJawabanPemain = jsonObj.getJSONArray("statusJawabanPemain");
            for (int i = 0; i < jsonArraySoal.length(); i++) {
                int soalJawaban = Integer.valueOf(jsonArraySoal.getString(i));
                String statusJawaban = jsonArrayJawaban.getString(i);
                String soal = String.valueOf(model_susun_huruf.get(soalJawaban-1).getSoalKuis());
                String jawaban = String.valueOf(model_susun_huruf.get(soalJawaban-1).getJawabanKuis());
                String jawabanpemain = jsonArrayJawabanPemain.getString(i);
                model_detail.add(new model_tb_detail_jawaban(soal,jawaban,statusJawaban,jawabanpemain));
                Log.d("JSON", soalJawaban +""+soal+""+statusJawaban);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AdapterDetailHasil adapter = new AdapterDetailHasil(this, model_detail);
        RecyclerView rv_detailhasil = findViewById(R.id.rv_detailhasil);
        rv_detailhasil.setLayoutManager(new LinearLayoutManager(this));
        rv_detailhasil.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
