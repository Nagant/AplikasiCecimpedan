package com.dwputuyudhiardiana.cecimpedan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.dwputuyudhiardiana.cecimpedan.MainActivity;
import com.dwputuyudhiardiana.cecimpedan.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;
import com.dwputuyudhiardiana.cecimpedan.prototipe.proto_pager;


public class DashboardFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

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

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Statistik"));
        tabLayout.addTab(tabLayout.newTab().setText("Daftar Nilai"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = root.findViewById(R.id.pager);

        //Creating our pager adapter
        proto_pager adapter = new proto_pager(getFragmentManager() , tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);

        return root;
    }

    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        tab.select();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}