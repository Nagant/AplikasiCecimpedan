package com.dwputuyudhiardiana.cecimpedan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;

import java.util.ArrayList;
import java.util.List;

public class proto_DBHelper_Tabel_Hasil_User {
    private proto_SQL dBHelper;
    public static final String TABEL_HASIL_USER                 = "tb_hasil_user";
    public static final String ID_HASIL_USER                    = "id_hasiluser";
    public static final String TANGGAL_BERMAIN_HASIL_USER       = "tanggal_bermain_hasiluser";
    public static final String NAMA_PERMAINAN_HASIL_USER        = "nama_pemain_hasiluser";
    public static final String NILAI_SKOR_HASIL_USER            = "nilai_hasiluser";
    public static final String TOTAL_JAWABAN_BENAR_HASIL_USER   = "totaljawaban_hasiluser";
    public static final String DETAIL_JAWABAN_HASIL_USER        = "detailjawaban_hasiluser";
    private Context context;

    public proto_DBHelper_Tabel_Hasil_User(Context context){
        this.context = context;
    }

    public void tambahHasilUser(model_tb_jawaban_user hasiluser) {
        dBHelper = new proto_SQL(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TANGGAL_BERMAIN_HASIL_USER, hasiluser.gettanggalbermainUser());
        values.put(NAMA_PERMAINAN_HASIL_USER, hasiluser.getnamapemainanUser());
        values.put(NILAI_SKOR_HASIL_USER, hasiluser.getnilaiUserUser());
        values.put(TOTAL_JAWABAN_BENAR_HASIL_USER, hasiluser.gettotaljawabanbenarUser());
        values.put(DETAIL_JAWABAN_HASIL_USER, hasiluser.getdetailjawabanUser());
        db.insert(TABEL_HASIL_USER, null, values);
        Log.d("DATABASE", "Tambah Hasil User "+hasiluser.gettanggalbermainUser()+" "+hasiluser.getdetailjawabanUser());
    }

    public List<model_tb_jawaban_user> dapatkanJawabanUser() {
        dBHelper = new proto_SQL(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        List<model_tb_jawaban_user> daftarJawabanUser = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABEL_HASIL_USER + " ORDER BY " + TANGGAL_BERMAIN_HASIL_USER + " DESC";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                model_tb_jawaban_user dataJawabanUser = new model_tb_jawaban_user();
                dataJawabanUser.settanggalbermainUser(cursor.getString(1));
                dataJawabanUser.setnamajenispermainanUser(cursor.getString(2));
                dataJawabanUser.setnilaiUserUser(cursor.getString(3));
                dataJawabanUser.settotaljawabanbenarUser(cursor.getString(4));
                dataJawabanUser.setdetailjawabanUser(cursor.getString(5));
                daftarJawabanUser.add(dataJawabanUser);
            } while (cursor.moveToNext());
        }
        cursor.close();
        dBHelper.close();
        return daftarJawabanUser;
    }

}
