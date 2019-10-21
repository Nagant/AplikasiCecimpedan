package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.adapter.AdapterDaftarNilai;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_jawaban_user;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Hasil_User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class DaftarNilaiDashboard extends Fragment {
    private RecyclerView rv_nilai;
    private proto_DBHelper_Tabel_Hasil_User hasilUser;
    private AdapterDaftarNilai adapter;
    private final ArrayList<model_tb_jawaban_user> model_jawaban_user = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        hasilUser = new proto_DBHelper_Tabel_Hasil_User(getActivity());
        adapter = new AdapterDaftarNilai(getActivity());
        View root =  inflater.inflate(R.layout.fragment_daftar_nilai, container, false);
        rv_nilai = root.findViewById(R.id.rv_nilai);
        rv_nilai.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<model_tb_jawaban_user> hasiluser = hasilUser.dapatkanJawabanUser(user.getDisplayName());
        model_jawaban_user.addAll(hasiluser);
        adapter.setListContent(model_jawaban_user);
        rv_nilai.setAdapter(adapter);
        return root;
    }
}
