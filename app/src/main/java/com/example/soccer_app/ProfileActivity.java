package com.example.soccer_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageButton btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        ImageView imgAvatar = findViewById(R.id.img_profile_avatar);
        if (imgAvatar != null) {
            imgAvatar.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        Button btnEdit = findViewById(R.id.btn_edit_profile);
        if (btnEdit != null) {
            btnEdit.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        Button btnGames = findViewById(R.id.btn_my_games);
        if (btnGames != null) {
            btnGames.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        Button btnHistory = findViewById(R.id.btn_game_history);
        if (btnHistory != null) {
            btnHistory.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        Button btnSettings = findViewById(R.id.btn_account_settings);
        if (btnSettings != null) {
            btnSettings.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            nav.setSelectedItemId(R.id.nav_profile);
            NavUtil.bind(this, nav);
        }
    }
}
