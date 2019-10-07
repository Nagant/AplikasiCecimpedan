package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.PermainanActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Info;

public class PermainanFragment extends Fragment implements View.OnClickListener {
    private final proto_DBHelper_Tabel_Info data_info = new proto_DBHelper_Tabel_Info();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_permainan, container, false);
        ((MainActivity) getActivity())
                .setPosisiTeks(getResources().getString(R.string.navigationbottom_permainan));
        TextView permainan_info = root.findViewById(R.id.teks_tahukah);
        ImageView tombol_mulai_permainan = root.findViewById(R.id.tombol_mulai_permainan);

        tombol_mulai_permainan.setOnClickListener(this);
        permainan_info.setText(String.valueOf(data_info.dapatkanInfoRandomize(getContext())));

        return root;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.tombol_mulai_permainan:
                Intent pindahActivity = new Intent(getActivity(), PermainanActivity.class);
                startActivity(pindahActivity);
                break;
        }
    }
}