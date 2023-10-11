package com.kelompok8.twobank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SaldoActivity extends AppCompatActivity {

    private EditText amountEditText;
    private ImageView eyeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldo);

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
                Intent intent = new Intent(SaldoActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Untuk menutup RegisterActivity
            }
        });

        // Set the initial selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_item_2);

        // Mengambil data nama pengguna dan nomor rekening dari SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String username = sharedPref.getString("USERNAME", "");
        String norek = sharedPref.getString("NOMOR_REKENING", "");

        // Menampilkan nama pengguna dan nomor rekening di TextView
        TextView userText = findViewById(R.id.userText);
        userText.setText(username);

        TextView norekText = findViewById(R.id.norekText);
        norekText.setText(norek);

        // Handle item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_item_1) {
                    Intent intent = new Intent(SaldoActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_2) {
                    // Aktivitas Saldo sedang aktif, tidak perlu tindakan tambahan
                } else if (itemId == R.id.menu_item_3) {
                    Intent intent = new Intent(SaldoActivity.this, ScanActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_4) {
                    Intent intent = new Intent(SaldoActivity.this, NotifikasiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_5) {
                    Intent intent = new Intent(SaldoActivity.this, ProfilActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        amountEditText = findViewById(R.id.amountEditText);
        eyeIcon = findViewById(R.id.eyeIcon);

        eyeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ubah tampilan jumlah nominal saat tombol mata ditekan
                if (amountEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // Jika tampilan saat ini terlihat, sembunyikan
                    amountEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    // Jika tampilan saat ini tersembunyi, tampilkan
                    amountEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }
}