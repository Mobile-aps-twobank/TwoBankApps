package com.kelompok8.twobank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditProfileActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Temukan tombol "Back" ImageView
        ImageView backButton = findViewById(R.id.backButton);

        // Menambahkan OnClickListener pada ImageView
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kembali ke MainActivity
                Intent intent = new Intent(EditProfileActivity.this, ProfilActivity.class);
                startActivity(intent);
                finish(); // Untuk menutup LoginActivity
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the initial selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_item_5);

        // Handle item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_item_1) {
                    Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_2) {
                    Intent intent = new Intent(EditProfileActivity.this, SaldoActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_3) {
                    Intent intent = new Intent(EditProfileActivity.this, ScanActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_4) {
                    Intent intent = new Intent(EditProfileActivity.this, NotifikasiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_5) {
                }

                return true;
            }
        });
    }

}