package com.dwputuyudhiardiana.cecimpedan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_info_tahukah;

public class proto_DBHelper_Tabel_Info {
    public static final String TABEL_INFO   = "tb_tahukah_info";
    public static final String ID_INFO      = "id_info";
    public static final String ISI_INFO     = "isi_info";



    public void tambahInfo(SQLiteDatabase db, model_tb_info_tahukah info) {
        ContentValues values = new ContentValues();
        values.put(ISI_INFO, info.getIsiInfo());
        db.insert(TABEL_INFO, null, values);
        Log.d("DATABASE", "Tambah Infomarsi "+info.getIsiInfo());
    }

    public String dapatkanInfoRandomize(Context context) {
        proto_SQL dBHelper = new proto_SQL(context);
        SQLiteDatabase db1 = dBHelper.getReadableDatabase();
        Cursor cursor = db1.query(TABEL_INFO, new String[]{ID_INFO,
                ISI_INFO}, null, null, null, null, "RANDOM()", "1");
        if (cursor.moveToFirst()) {
            Log.d("DATABASE", "Tampilkan Infomarsi " + cursor.getString(cursor.getColumnIndex(ISI_INFO)));
            dBHelper.close();
            return cursor.getString(cursor.getColumnIndex(ISI_INFO));


        } else {
            dBHelper.close();
            return "";
        }
    }

}
