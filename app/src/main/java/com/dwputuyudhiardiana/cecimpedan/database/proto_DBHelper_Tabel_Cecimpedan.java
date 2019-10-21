package com.dwputuyudhiardiana.cecimpedan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_cecimpedan;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_kamus;

import java.util.ArrayList;
import java.util.List;

public class proto_DBHelper_Tabel_Cecimpedan {
    private proto_SQL dBHelper;
    static final String TABEL_KAMUS          = "tb_kamus";
    static final String ID_KAMUS             = "id_kamus";
    static final String KATEGORI_KAMUS       = "kat_kamus";
    static final String SOAL_KAMUS           = "soal_kamus";
    static final String JAWABAN_KAMUS        = "jawaban_kamus";
    static final String KUIS_KAMUS           = "kuis_kamus";
    private Context context;

    public proto_DBHelper_Tabel_Cecimpedan(Context context){
        this.context = context;
    }

    void tambahKamus(SQLiteDatabase db, model_tb_kamus kamus) {
        ContentValues values = new ContentValues();
        values.put(KATEGORI_KAMUS, kamus.getKatKamus());
        values.put(SOAL_KAMUS, kamus.getSoalKamus());
        values.put(JAWABAN_KAMUS, kamus.getJawabanKamus());
        values.put(KUIS_KAMUS, kamus.getKuisKamus());
        db.insert(TABEL_KAMUS, null, values);
        Log.d("DATABASE", "Tambah Kamus "+kamus.getSoalKamus());
    }

    public List<model_tb_kamus> dapatkanSemuaKamus(String kategori,String pencarian) {
        dBHelper = new proto_SQL(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        List<model_tb_kamus> daftarKamus = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABEL_KAMUS;
        Cursor cursor;
        if(pencarian.length()==0){
            cursor = db.rawQuery(selectQuery + " WHERE kat_kamus = ? ", new String[] {kategori});
        }else{
            cursor = db.rawQuery(selectQuery + " WHERE soal_kamus LIKE ? ", new String[] {"%" + pencarian + "%"});
        }

        if (cursor.moveToFirst()) {
            do {
                model_tb_kamus dataKamus = new model_tb_kamus();
                dataKamus.setIdKamus(Integer.parseInt(cursor.getString(0)));
                dataKamus.setKatKamus(cursor.getString(1));
                dataKamus.setSoalKamus(cursor.getString(2));
                dataKamus.setJawabanKamus(cursor.getString(3));
                dataKamus.setKuisKamus(cursor.getString(4));
                daftarKamus.add(dataKamus);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dBHelper.close();
        return daftarKamus;
    }


    public List<model_tb_cecimpedan> dapatkanSoal() {
        dBHelper = new proto_SQL(context);
        List<model_tb_cecimpedan> soalKuis = new ArrayList<>();
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor = db.query(TABEL_KAMUS,new String[]{"*"},"kuis_kamus = ?",new String[]{"Kuis"},null,null,null);
        if (cursor.moveToFirst()) {
            do {
                model_tb_cecimpedan dataKuis = new model_tb_cecimpedan();
                dataKuis.setIdKuis(Integer.parseInt(cursor.getString(0)));
                dataKuis.setKatKuis(cursor.getString(1));
                dataKuis.setSoalKuis(cursor.getString(2));
                dataKuis.setJawabanKuis(cursor.getString(3));
                soalKuis.add(dataKuis);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dBHelper.close();
        return soalKuis;
    }

    public List<model_tb_cecimpedan> dapatkanSoalSemua() {
        dBHelper = new proto_SQL(context);
        List<model_tb_cecimpedan> soalKuis = new ArrayList<>();
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor = db.query(TABEL_KAMUS,new String[]{"*"},null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                model_tb_cecimpedan dataKuis = new model_tb_cecimpedan();
                dataKuis.setIdKuis(Integer.parseInt(cursor.getString(0)));
                dataKuis.setKatKuis(cursor.getString(1));
                dataKuis.setSoalKuis(cursor.getString(2));
                dataKuis.setJawabanKuis(cursor.getString(3));
                soalKuis.add(dataKuis);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dBHelper.close();
        return soalKuis;
    }


}

