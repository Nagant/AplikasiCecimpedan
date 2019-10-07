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

    public static final String TABEL_KAMUS          = "tb_kamus";
    public static final String ID_KAMUS             = "id_kamus";
    public static final String KATEGORI_KAMUS       = "kat_kamus";
    public static final String SOAL_KAMUS           = "soal_kamus";
    public static final String JAWABAN_KAMUS        = "jawaban_kamus";
    public static final String KUIS_KAMUS           = "kuis_kamus";

    public void tambahKamus(SQLiteDatabase db, model_tb_kamus kamus) {
        ContentValues values = new ContentValues();
        values.put(KATEGORI_KAMUS, kamus.getKatKamus());
        values.put(SOAL_KAMUS, kamus.getSoalKamus());
        values.put(JAWABAN_KAMUS, kamus.getJawabanKamus());
        values.put(KUIS_KAMUS, kamus.getKuisKamus());
        db.insert(TABEL_KAMUS, null, values);
        Log.d("DATABASE", "Tambah Kamus "+kamus.getSoalKamus());

    }
    public List<model_tb_cecimpedan> dapatkanSoal(Context context) {
        List<model_tb_cecimpedan> soalKuis = new ArrayList<>();
        dBHelper = new proto_SQL(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor = db.query(TABEL_KAMUS,new String[]{"*"},"kuis_kamus = ?",new String[]{"Kuis"},"RANDOM()",null,null,"10");
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
        dBHelper.close();
        return soalKuis;
    }

    public List<model_tb_cecimpedan> dapatkanSoalSemua(Context context) {
        List<model_tb_cecimpedan> soalKuis = new ArrayList<>();
        dBHelper = new proto_SQL(context);
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
        dBHelper.close();
        return soalKuis;
    }


}

