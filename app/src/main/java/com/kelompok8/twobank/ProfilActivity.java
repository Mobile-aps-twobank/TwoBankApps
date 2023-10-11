package com.kelompok8.twobank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilActivity extends AppCompatActivity {

    private TextView namaTextView, rekeningTextView;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        databaseHelper = new DatabaseHelper(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the initial selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_item_5);

        // Temukan tombol "Back" ImageView
        ImageView backButton = findViewById(R.id.backButton);

        // Menambahkan OnClickListener pada ImageView
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kembali ke MainActivity
                Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Untuk menutup RegisterActivity
            }
        });

        // Area Profil ketika di klik pindah ke halaman Edit Profile
        LinearLayout profiltab = findViewById(R.id.profiltab);
        profiltab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman Edit Profile
                Intent intent = new Intent(ProfilActivity.this, EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Area Profil ketika di klik pindah ke halaman Edit Profile
        LinearLayout setting_profile = findViewById(R.id.setting);
        setting_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman Edit Profile
                Intent intent = new Intent(ProfilActivity.this, EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // Menampilkan nama pengguna dan nomor rekening di Page Profile
        TextView namaTextView = findViewById(R.id.namaTextView);
        TextView rekeningTextView = findViewById(R.id.rekeningTextView);

        user = databaseHelper.getUser();

        // Tampilkan Data berdasarkan user yang login
        if (user != null){
            namaTextView.setText(user.getUsername());
            rekeningTextView.setText(user.getNorek());
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_item_1) {
                    Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_2) {
                    Intent intent = new Intent(ProfilActivity.this, SaldoActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_3) {
                    Intent intent = new Intent(ProfilActivity.this, ScanActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_4) {
                    Intent intent = new Intent(ProfilActivity.this, NotifikasiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_5) {
//                    Intent intent = new Intent(ProfilActivity.this, ProfilActivity.class);
//                    startActivity(intent);
                }

                return true;
            }
        });
    }
}