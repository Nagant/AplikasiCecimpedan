package com.dwputuyudhiardiana.cecimpedan.prototipe;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.dwputuyudhiardiana.cecimpedan.fragment.DaftarNilaiDashboard;
import com.dwputuyudhiardiana.cecimpedan.fragment.StatistikFragment;

public class proto_pager extends FragmentStatePagerAdapter {
    int tabCount;
    public proto_pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StatistikFragment statistik = new StatistikFragment();
                return statistik;
            case 1:
                DaftarNilaiDashboard daftarnilai = new DaftarNilaiDashboard();
                return daftarnilai;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
