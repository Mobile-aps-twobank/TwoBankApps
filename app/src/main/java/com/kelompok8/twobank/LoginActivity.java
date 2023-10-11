package com.kelompok8.twobank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private DatabaseHelper databaseHelper;
    private static final String PREFS_NAME = "MyData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Temukan tombol "Back" ImageView
        ImageView backButton = findViewById(R.id.backButton);

        // Menambahkan OnClickListener pada ImageView
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kembali ke MainActivity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Untuk menutup LoginActivity
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Harap isi email dan password", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_USERNAME,
                DatabaseHelper.COLUMN_EMAIL,
                DatabaseHelper.COLUMN_NOREK,
                DatabaseHelper.COLUMN_PASSWORD
        };

        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            // Login berhasil
            String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USERNAME));
            String norek = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOREK));

            // Menyimpan data sesi ke SharedPreferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            char[] id = new char[0];
            editor.putString("id", String.valueOf(id)); // id disimpan sebagai string
            editor.putString("username", username);
            editor.putString("email", email);
            editor.putString("norek", norek);
            editor.putString("password", password);

            // Menyimpan perubahan
            editor.apply();


            Toast.makeText(this, "Login Success, " + username, Toast.LENGTH_SHORT).show();

            // Pindah ke SaldoActivity
            Intent saldoIntent = new Intent(LoginActivity.this, SaldoActivity.class);
//            saldoIntent.putExtra("USERNAME", username); // Mengirim nama pengguna ke SaldoActivity
//            saldoIntent.putExtra("NOMOR_REKENING", norek); // Mengirim nomor rekening ke SaldoActivity
            startActivity(saldoIntent);


        } else {
            // Login gagal
            Toast.makeText(this, "Login Failed. Periksa Email dan Password.", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }
}