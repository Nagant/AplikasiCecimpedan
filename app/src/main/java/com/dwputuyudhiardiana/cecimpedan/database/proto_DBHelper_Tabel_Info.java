package com.dwputuyudhiardiana.cecimpedan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_info_tahukah;

public class proto_DBHelper_Tabel_Info {
    private proto_SQL dBHelper;
    public static final String TABEL_INFO   = "tb_tahukah_info";
    public static final String ID_INFO      = "id_info";
    public static final String ISI_INFO     = "isi_info";
    private Context context;

    public proto_DBHelper_Tabel_Info(Context context){
        this.context = context;
    }


    public void tambahInfo(SQLiteDatabase db, model_tb_info_tahukah info) {
        ContentValues values = new ContentValues();
        values.put(ISI_INFO, info.getIsiInfo());
        db.insert(TABEL_INFO, null, values);
        Log.d("DATABASE", "Tambah Infomarsi "+info.getIsiInfo());
    }

    public String dapatkanInfoRandomize() {
        dBHelper = new proto_SQL(context);
        SQLiteDatabase db = dBHelper.getReadableDatabase();
        Cursor cursor = db.query(TABEL_INFO, new String[]{ID_INFO,
                ISI_INFO}, null, null, null, null, "RANDOM()", "1");
        if (cursor.moveToFirst()) {
            String db_informasi = cursor.getString(cursor.getColumnIndex(ISI_INFO));
            cursor.close();
            dBHelper.close();
            return db_informasi;
        } else {
            cursor.close();
            dBHelper.close();
            return "";
        }

    }

}
