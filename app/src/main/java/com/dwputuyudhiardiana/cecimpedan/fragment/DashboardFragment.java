package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity())
                .setPosisiTeks(getResources().getString(R.string.navigationbottom_dashboard));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        View root =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        CircleImageView dashboard_gambar = root.findViewById(R.id.dashboard_gambar);
        TabLayout tabLayout = root.findViewById(R.id.dashboard_tablayout);
        TextView dashboard_email = root.findViewById(R.id.dashboard_email);
        TextView dashboard_nama = root.findViewById(R.id.dashboard_nama);
        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(dashboard_gambar);
        tabLayout.setTabRippleColor(null);
        dashboard_nama.setText(user.getDisplayName());
        dashboard_email.setText(user.getEmail());

        return root;
    }
}