package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.dwputuyudhiardiana.cecimpedan.adapter.AdapterKamus;
import com.dwputuyudhiardiana.cecimpedan.database.model.model_tb_kamus;
import com.dwputuyudhiardiana.cecimpedan.database.proto_DBHelper_Tabel_Cecimpedan;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class KamusFragment extends Fragment {
    private RecyclerView rv_kamus;
    private RelativeLayout box_pencariantidakada;
    private LottieAnimationView icon_pencarian;
    private proto_DBHelper_Tabel_Cecimpedan kamus;
    private AdapterKamus adapter;
    private final ArrayList<model_tb_kamus> model_kamus = new ArrayList<>();
    private String kategoriKamus = "Biasa";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity())
                .setPosisiTeks(getResources().getString(R.string.navigationbottom_kamus));
        kamus = new proto_DBHelper_Tabel_Cecimpedan(getActivity());
        adapter = new AdapterKamus(getActivity());
        View root = inflater.inflate(R.layout.fragment_kamus, container, false);
        TabLayout tabLayout = root.findViewById(R.id.kamus_tab);
        rv_kamus = root.findViewById(R.id.rv_kamus);
        SearchView kotak_carikamus = root.findViewById(R.id.kotak_carikamus);
        box_pencariantidakada = root.findViewById(R.id.box_pencariantidakada);
        icon_pencarian = root.findViewById(R.id.icon_pencariantidakada);
        rv_kamus.setLayoutManager(new LinearLayoutManager(getActivity()));
        tabLayout.setTabRippleColor(null);
        kotak_carikamus.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                jenis_kamus("",query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()==0){
                    jenis_kamus(kategoriKamus,"");
                }
                return true;
            }
        });

        jenis_kamus(kategoriKamus,"");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0) {
                    kategoriKamus = "Alit-Alit";
                    jenis_kamus("Alit-Alit", "");
                }else{
                    kategoriKamus = "Biasa";
                    jenis_kamus("Biasa","");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }

    private void jenis_kamus(String kategori,String pencarian){
        model_kamus.clear();
        List<model_tb_kamus> kam = kamus.dapatkanSemuaKamus(kategori, pencarian);
        if(kam.size()==0){
            icon_pencarian.setRepeatCount(5);
            rv_kamus.setVisibility(View.GONE);
            box_pencariantidakada.setVisibility(View.VISIBLE);
        }else{
            icon_pencarian.setRepeatCount(0);
            rv_kamus.setVisibility(View.VISIBLE);
            box_pencariantidakada.setVisibility(View.GONE);
            model_kamus.addAll(kam);
            adapter.setListContent(model_kamus);
            rv_kamus.setAdapter(adapter);
        }
    }

}