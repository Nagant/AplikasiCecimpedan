package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.R;

public class TentangFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity())
                .setPosisiTeks(getResources().getString(R.string.navigationbottom_tentang));
        return inflater.inflate(R.layout.fragment_tentang, container, false);
    }
}