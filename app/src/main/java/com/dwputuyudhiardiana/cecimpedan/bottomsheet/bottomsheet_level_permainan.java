package com.dwputuyudhiardiana.cecimpedan.bottomsheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dwputuyudhiardiana.cecimpedan.PermainanActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class bottomsheet_level_permainan extends BottomSheetDialogFragment implements View.OnClickListener {

    private Button level_mudah,level_sedang,level_susah,mulai_permainan;
    private String level_pilih,level_total_soal,level_informasi_text;
    private TextView level_informasi;
    private ImageView bottomsheet_level_tutup;
    private static bottomsheet_level_permainan bottomsheet_level_permainan;

    public static bottomsheet_level_permainan newInstance() {
        bottomsheet_level_permainan = new bottomsheet_level_permainan();
        return bottomsheet_level_permainan;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottomsheet_level_permainan, container,
                false);

        level_mudah = root.findViewById(R.id.level_mudah);
        level_sedang = root.findViewById(R.id.level_sedang);
        level_susah = root.findViewById(R.id.level_susah);
        mulai_permainan = root.findViewById(R.id.level_mulai);
        level_informasi = root.findViewById(R.id.level_informasi);
        bottomsheet_level_tutup = root.findViewById(R.id.bottomsheet_level_tutup);

        bottomsheet_level_tutup.setOnClickListener(this);
        level_mudah.setOnClickListener(this);
        level_sedang.setOnClickListener(this);
        level_susah.setOnClickListener(this);
        mulai_permainan.setOnClickListener(this);
        mulai_permainan.setEnabled(false);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bottomsheet_level_tutup:
                bottomsheet_level_permainan.dismiss();
                break;
            case R.id.level_mudah:
                level("mudah","10");
                break;
            case R.id.level_sedang:
                level("sedang","20");
                break;
            case R.id.level_susah:
                level("susah","30");
                break;
            case R.id.level_mulai:
                Intent mulaiPrermainan = new Intent(getActivity(), PermainanActivity.class);
                mulaiPrermainan.putExtra("level",level_pilih);
                startActivity(mulaiPrermainan);
                bottomsheet_level_permainan.dismiss();
                break;
        }
    }

    private void level(String level, String total_soal){
        level_pilih = level;
        level_total_soal = total_soal;
        mulai_permainan.setEnabled(true);
        level_informasi_text = String.format(getResources().getString(R.string.bottomsheet_level_total_soal_permainan), level_total_soal);
        level_informasi.setText(level_informasi_text);
    }
}
