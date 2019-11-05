package com.dwputuyudhiardiana.cecimpedan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class IntroActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private ViewPager mViewPager;
    private Button mNextBtn;
    private Button mSkipBtn, mFinishBtn;
    private ImageView satu, dua , tiga;
    private ImageView[] indicators;
    ConstraintLayout mCoordinator;
    SectionsPagerAdapter mSectionsPagerAdapter;
    int page = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mNextBtn = findViewById(R.id.intro_btn_next);
        mSkipBtn = findViewById(R.id.intro_btn_skip);
        mFinishBtn = findViewById(R.id.intro_btn_finish);
        sharedPref =  getSharedPreferences("pengaturanaplikasi", MODE_PRIVATE);
        satu = findViewById(R.id.intro_indicator_0);
        dua = findViewById(R.id.intro_indicator_1);
        tiga = findViewById(R.id.intro_indicator_2);
        mCoordinator = findViewById(R.id.main_content);
        indicators = new ImageView[]{satu, dua , tiga};
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(page);
        updateIndicators(page);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateIndicators(position);
            }

            @Override
            public void onPageSelected(int position) {
                updateIndicators(page);
                mNextBtn.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
                mFinishBtn.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
                mSkipBtn.setVisibility(position == 2 ? View.INVISIBLE : View.VISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("tampilkanintro", false);
                editor.apply();
                Intent toMain = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(toMain);
                finish();
            }
        });

        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("tampilkanintro", false);
                editor.apply();
                Intent toMain = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(toMain);
                finish();
            }
        });
    }

    void updateIndicators(int position) {

        for (int i = 0; i < indicators.length; i++) {

            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicator_selected : R.drawable.indicator_unselected
            );
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public static String intro_description,intro_label;
        ImageView section_intro;
        int[] bgs = new int[]{R.drawable.img_bannerlogo, R.drawable.img_tutorial_a, R.drawable.img_bannerlogo};

        public PlaceholderFragment() {

        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
            TextView section_label = rootView.findViewById(R.id.intro_label);
            TextView section_description = rootView.findViewById(R.id.intro_desc);
            section_intro = rootView.findViewById(R.id.intro_gmbr);
            section_intro.setImageResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER)-1]);
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                intro_label = getResources().getString(R.string.label_1);
                intro_description = getResources().getString(R.string.desc_1);
            }else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                intro_label = getResources().getString(R.string.label_2);
                intro_description = getResources().getString(R.string.desc_2);
            }else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                intro_label = getResources().getString(R.string.label_3);
                intro_description = getResources().getString(R.string.desc_3);
            }
            section_label.setText(intro_label);
            section_description.setText(intro_description);

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}

