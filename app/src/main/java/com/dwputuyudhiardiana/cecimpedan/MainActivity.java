package com.dwputuyudhiardiana.cecimpedan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private TextView teks_posisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        teks_posisi = findViewById(R.id.teks_posisi);
        CircleImageView gambar_profile = findViewById(R.id.gambar_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        FirebaseApp.initializeApp(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_permainan,R.id.navigation_kamus,R.id.navigation_dashboard)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Glide.with(this)
                .load(user.getPhotoUrl())
                .centerInside()
                .into(gambar_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pengaturan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_pengaturan:
                Intent pengaturan = new Intent(MainActivity.this,PengaturanActivity.class);
                startActivity(pengaturan);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setPosisiTeks(String posisi) {
        teks_posisi.setText(posisi);
    }
}
