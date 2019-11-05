package com.dwputuyudhiardiana.cecimpedan.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.dwputuyudhiardiana.cecimpedan.R;

public class bottomsheet_kamus extends BottomSheetDialogFragment implements View.OnClickListener {

    private String soalCecimpedan,artiCecimpedan;
    private TextView bottomsheet_soal_kamus,bottomsheet_arti_kamus;
    private ImageView bottomsheet_tutup_kamus;
    private static bottomsheet_kamus bottomsheet_kamus;

    public static bottomsheet_kamus newInstance(String soal,String arti) {
        bottomsheet_kamus = new bottomsheet_kamus();
        Bundle args = new Bundle();
        args.putString("soalCecimpedan", soal);
        args.putString("artiCecimpedan", arti);
        bottomsheet_kamus.setArguments(args);
        return bottomsheet_kamus;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        soalCecimpedan = getArguments().getString("soalCecimpedan", "");
        artiCecimpedan = getArguments().getString("artiCecimpedan", "");

        View root = inflater.inflate(R.layout.bottomsheet_kamus, container,
                false);
        bottomsheet_soal_kamus = root.findViewById(R.id.kamus_soal);
        bottomsheet_arti_kamus = root.findViewById(R.id.kamus_arti);

        bottomsheet_tutup_kamus = root.findViewById(R.id.bottomsheet_kamus_tutup);

        bottomsheet_tutup_kamus.setOnClickListener(this);
        bottomsheet_soal_kamus.setText(soalCecimpedan);
        bottomsheet_arti_kamus.setText(artiCecimpedan);
        return root;

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bottomsheet_kamus_tutup:
                bottomsheet_kamus.dismiss();
                break;
        }
    }
}
