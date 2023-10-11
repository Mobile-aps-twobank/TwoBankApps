package com.kelompok8.twobank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set the initial selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_item_1);

        // Floating Action Button
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Lakukan tindakan yang sesuai saat FAB diklik
//                // Contoh: Tampilkan pesan Toast
//                Toast.makeText(HomeActivity.this, "FAB Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        // Handle item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_item_1) {
//                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
//                    startActivity(intent);
                } else if (itemId == R.id.menu_item_2) {
                    Intent intent = new Intent(HomeActivity.this, SaldoActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_3) {
                    Intent intent = new Intent(HomeActivity.this, ScanActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_4) {
                    Intent intent = new Intent(HomeActivity.this, NotifikasiActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.menu_item_5) {
                    Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });


    }
}