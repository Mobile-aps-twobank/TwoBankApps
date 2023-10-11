package com.kelompok8.twobank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditProfileActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, passwordEditText;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        databaseHelper = new DatabaseHelper(this);

        // Temukan tombol "Back" ImageView
        ImageView backButton = findViewById(R.id.backButton);

        // Menambahkan OnClickListener pada ImageView
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kembali ke MainActivity
                Intent intent = new Intent(EditProfileActivity.this, ProfilActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_5);

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

        // EditTexts
        usernameEditText = findViewById(R.id.editText_username);
        emailEditText = findViewById(R.id.editText_email);
        passwordEditText = findViewById(R.id.editText_password);

        user = databaseHelper.getUser();

        // Isi EditText dengan data pengguna
        if (user != null) {
            usernameEditText.setText(user.getUsername());
            emailEditText.setText(user.getEmail());
            passwordEditText.setText(user.getPassword());
        }

        // Temukan tombol "Update Profile"
        Button updateButton = findViewById(R.id.edit_profileButton);

        // Tambahkan OnClickListener pada tombol "Update Profile"
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newUsername = usernameEditText.getText().toString();
                String newEmail = emailEditText.getText().toString();
                String newPassword = passwordEditText.getText().toString();

                user.setUsername(newUsername);
                user.setEmail(newEmail);
                user.setPassword(newPassword);

                boolean updateSuccess = databaseHelper.updateUser(user);

                if (updateSuccess) {
                    Toast.makeText(EditProfileActivity.this, "Profil berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, ProfilActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Gagal memperbarui profil", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
