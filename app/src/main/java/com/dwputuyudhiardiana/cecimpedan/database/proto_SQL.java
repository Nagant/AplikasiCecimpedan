package com.dwputuyudhiardiana.cecimpedan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_info_tahukah;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_kamus;

public class proto_SQL extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "db_cecimpedan131";
    private final proto_DBHelper_Tabel_Info DBHelper_INFO = new proto_DBHelper_Tabel_Info();
    private final proto_DBHelper_Tabel_Cecimpedan DBHelper_KAMUS = new proto_DBHelper_Tabel_Cecimpedan();

    public proto_SQL(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BUAT TABEL INFORMASI CECIMPEDAN
        String CREATE_INFO_TABLE = "CREATE TABLE " + proto_DBHelper_Tabel_Info.TABEL_INFO + "("
                + proto_DBHelper_Tabel_Info.ID_INFO + " INTEGER PRIMARY KEY AUTOINCREMENT," + proto_DBHelper_Tabel_Info.ISI_INFO + " TEXT" + ")";
        db.execSQL(CREATE_INFO_TABLE);

        //BUAT TABEL PERMAINAN
        String CREATE_MATERI_KAMUS = "CREATE TABLE " + proto_DBHelper_Tabel_Cecimpedan.TABEL_KAMUS + "("
                + proto_DBHelper_Tabel_Cecimpedan.ID_KAMUS + " INTEGER PRIMARY KEY AUTOINCREMENT," + proto_DBHelper_Tabel_Cecimpedan.KATEGORI_KAMUS + " TEXT," + proto_DBHelper_Tabel_Cecimpedan.SOAL_KAMUS + " TEXT," + proto_DBHelper_Tabel_Cecimpedan.JAWABAN_KAMUS + " TEXT," + proto_DBHelper_Tabel_Cecimpedan.KUIS_KAMUS + " TEXT" + ")";
        db.execSQL(CREATE_MATERI_KAMUS);

        //BUAT TABEL SIMPAN JAWABAN PENGGUNA
        String CREATE_MATERI_HASIL_JAWABAN_USER = "CREATE TABLE " + proto_DBHelper_Tabel_Hasil_User.TABEL_HASIL_USER + "("
                + proto_DBHelper_Tabel_Hasil_User.ID_HASIL_USER + " INTEGER PRIMARY KEY AUTOINCREMENT," + proto_DBHelper_Tabel_Hasil_User.TANGGAL_BERMAIN_HASIL_USER + " TEXT," + proto_DBHelper_Tabel_Hasil_User.NAMA_PERMAINAN_HASIL_USER + " TEXT," + proto_DBHelper_Tabel_Hasil_User.NILAI_SKOR_HASIL_USER + " TEXT," + proto_DBHelper_Tabel_Hasil_User.TOTAL_JAWABAN_BENAR_HASIL_USER + " TEXT," + proto_DBHelper_Tabel_Hasil_User.DETAIL_JAWABAN_HASIL_USER + " TEXT" + ")";
        db.execSQL(CREATE_MATERI_HASIL_JAWABAN_USER);


        //ISI TABEL KAMUS
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak cerik maid cacing ?","jarum","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke mirah asibuh ?","delima","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke bolak-balik empet ?","talenan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke muncukne puun, nanging bongkolne belus ?","roko","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke mara melingeb misi, nanging mara nungkayak puyung ?","songko","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ane nengen nongos, nanging ane kategen majalan ?","pancoran","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke ane tan palima tan pabatis bisa majalan ?","matanai","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke mas mirah metanem ?","kunyit","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak bongkok kereng nyuun ?","sendi","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke di cerikne jegjeg dikelihne nguntul ?","padi","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak bongkok kereng ngurek tanah ?","gangsing","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke jenengne gilik muncukne barak, demenine teken anak luh ?","anci","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak bongkok kereng nyilem ?","batu","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke memene ngutil, panakne nebek ?","lalang","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak cenik matapel ?","blauk","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak cerik maid enceh ?","caratan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ane tegeh dugdugin, ane endep juangin ?","neraca","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa cekuk baongne godot basangne pesu gending ?","rebab","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa kedeng bun nrutdut puwuh ?","gangsing","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa kulkul malu tumbak dori ?","cicing","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa donne amun tuutp buahne amun sirah ?","waluh","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa suah dewa tusing dadih suahang ?","lipan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak bongkok macapil  ?","oong","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke di ngudane kunging diwayahne gadang ?","busung","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ana cenik ngrobok alas ?","kutu","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak cenik ileh-ileh ngaba umah ?","bekicot","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke yening pegatang tusing pegat-pegat ?","yeh","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak endep kuat nyuun ?","jaikan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak anak lengar-lengar megantung ?","wani","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke yening getepin negehang ?","jaler","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke muncukne beten, bongkolne baduur ?","jenggot","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak berag landung ngelah panak liu ?","jan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa anak cerik ngoyong di bucu ?","peceh","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke ane kajepes hidup, ane nyepes mati ?","pagehan","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak satak, makasatak matlusuk ?","iga-iga","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke ia mapayas nanging ia melalung ?","dokar","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa kenyit-kenyit duur gununge ?","dupa","Kuis"));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke anak cerik uyak tai uyak enceh ?","batuh borehan",""));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke yan jemus belus, nanging yan tusing jemuh ia tuh ?","anak sirahne lengar",""));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa ke ane songne gede sing nyidayang, nanging ane songne cenik nyidayang pesu ?","anak makecuh",""));
        DBHelper_KAMUS.tambahKamus(db,new model_tb_kamus("Biasa","Apa sorong jukung tarik bintang ?","mamula padi diuma",""));

        //ISI TABEL INFORMASI CECIMPEDAN
        DBHelper_INFO.tambahInfo(db,new model_tb_info_tahukah("Cecimpedan asal kata dari “cimped” didwipurwakan menjadi cecimped, lalu mendapat akjiran (pengiring) –an menjadi cecimpedan yang artinya terka – terkaan, atau teka – teki yang digunakan untuk mengasah otak."));
        DBHelper_INFO.tambahInfo(db,new model_tb_info_tahukah("“Cecimpedan” atau secara umum diketahui sebagai teka-teki adalah salah satu kesenian tradisional bali yang bisa juga dipakai permainan oleh anak-anak yang sedang berkumpul."));
        DBHelper_INFO.tambahInfo(db,new model_tb_info_tahukah("Jaman dahulu “Cecimpedan” ini dilakukan oleh orang tua kepada anaknya menjelang tidur. Teka-teki ini bisa mengecoh anak-anak dengan jawaban yang sedikit mirip dengan apa yang dimaksud tetapi tetap jawaban tersebut tidak benar. Teka-teki ini menggunakan kata-kata biasa tetapi menyiratkan arti yang sebenarnya."));



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + proto_DBHelper_Tabel_Info.TABEL_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + proto_DBHelper_Tabel_Cecimpedan.TABEL_KAMUS);
        onCreate(db);
    }




}

