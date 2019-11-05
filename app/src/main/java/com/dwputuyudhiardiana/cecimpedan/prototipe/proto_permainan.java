package com.dwputuyudhiardiana.cecimpedan.prototipe;

import android.content.Context;
import android.util.Log;

import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Hasil_User;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class proto_permainan {
    private final proto_DBHelper_Tabel_Hasil_User DBHelper_HASILUSER;

    private JSONArray hasil_soal_array,hasil_jawaban_array,hasil_jawaban_pemain_array;
    private JSONObject hasil_jawaban;

    public proto_permainan(Context context){
        DBHelper_HASILUSER  = new proto_DBHelper_Tabel_Hasil_User(context);
        hasil_jawaban       = new JSONObject();
        hasil_soal_array    = new JSONArray();
        hasil_jawaban_array = new JSONArray();
        hasil_jawaban_pemain_array = new JSONArray();
    }

    public String acakHuruf(String kataacak){
        Random acak = new Random();
        int[] hasil = new int[kataacak.length()];
        int z0 = acak.nextInt(kataacak.length()+1)+1; //Bilangan Acak Menggunakan Kata Yang Akan Di Acak [Mengambil Bilangan Acak dimulai dari 1 hingga Panjang Kata]
        int a = acak.nextInt(kataacak.length()+1)+2;  // Faktor Pengali [Mengambil Bilangan Acak dimulai dari 1 hingga Panjang Kata]
        int c = acak.nextInt(kataacak.length()+1)+2;  // Penambah [Mengambil Bilangan Acak dimulai dari 1 hingga Panjang Kata]
        int m = kataacak.length(); //Modulus Menggunakan Panjang Kata Yang Akan Di Acak
        int z = z0;
        char[] kataArray = kataacak.toCharArray(); //Kata yang di Konversi per Huruf
        for(int i=0; i<hasil.length; i++){ //Perulangan dari 0 sampai Panjang Kata
            z=(a*z+c)%m; //Model LCG
            hasil[i]=z;//Array
            char karakterAcak = kataArray[i]; //Pemposisian Huruf berdasarkan Perluangan
            kataArray[i] = kataArray[z]; //Pemposisian Huruf perluangan dengan kataArray yang sudah Di acak
            kataArray[z] = karakterAcak;//Penetapan Huruf pada Array yang diacak mengunakan LCG
        }
        Log.d("-", "-");
        Log.d("z0", String.valueOf(z0));
        Log.d("a", String.valueOf(a));
        Log.d("c", String.valueOf(c));
        return new String(kataArray);
    }

    public int[] AcakSoal(int totalsoal){
        Random acak = new Random();
        int[] hasil = new int[totalsoal];
        int z0 = acak.nextInt(36+1); //Bilangan Acak Menggunakan Kata Yang Akan Di Acak [Mengambil Bilangan Acak dimulai dari 1 hingga Jumlah Soal(Soal di database 40)]
        int a = 1; // Faktor Pengali
        int c = 7; // Penambah
        int m=36; //Modulus Menggunakan Jumlah Total Soal
        int z = z0;
        for(int i=0; i<totalsoal; i++){ //Perulangan dari 0 sampai 10, akan menampilkan 10 angka acak dari 1 sampai 40
            z=(a*z+c)%m; //Model LCG
            hasil[i]=z;//Array
        }
        //Debug
        Log.d("-", "-");
        Log.d("z0", String.valueOf(z0));
        Log.d("a", String.valueOf(a));
        Log.d("c", String.valueOf(c));
        return hasil;
    }

    public void jawabanPengguna(Integer posisisoal,String statusJawaban, String teks_jawaban){
        try {
            //Save Soal Acak Yang Akan disimpan pada JSON
            hasil_jawaban.put("soalJawaban", hasil_soal_array.put(String.valueOf(posisisoal)));

            //Save statusJawaban berdasarkan Soal Yang Akan disimpan pada JSON
            hasil_jawaban.put("statusJawaban", hasil_jawaban_array.put(statusJawaban));
            hasil_jawaban.put("statusJawabanPemain", hasil_jawaban_pemain_array.put(teks_jawaban));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void simpanHasil(String permainanNama,String permainanTotalSkor, String permainantotaljawabanbenar, String totalskor) {
        Date now = new Date();
        SimpleDateFormat dapatkantgl = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DBHelper_HASILUSER.tambahHasilUser(new model_tb_jawaban_user(dapatkantgl.format(now), permainanNama, permainanTotalSkor, permainantotaljawabanbenar, hasil_jawaban.toString(), totalskor));
    }
}
