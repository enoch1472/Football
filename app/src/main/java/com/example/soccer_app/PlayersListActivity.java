package com.example.soccer_app;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        int current = getIntent().getIntExtra("currentPlayers", 10);
        int max = getIntent().getIntExtra("maxPlayers", 14);

        TextView tvTitle = findViewById(R.id.text_players_title);
        if (tvTitle != null) {
            tvTitle.setText("Players " + current + "/" + max);
        }

        ImageButton btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        List<String> players = new ArrayList<>(Arrays.asList(
                "Patrick Dorgu",
                "Luke Shaw",
                "Lisandro Martinez",
                "Ayden Heaven",
                "Marcus Rashford",
                "Bruno Fernandes",
                "Bryan Mbuemo",
                "Senne Lammens",
                "Kobbie Mainoo",
                "Diogo Dalot"
        ));

        RecyclerView recyclerView = findViewById(R.id.recycler_players);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new PlayerAdapter(this, players));
        }

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            NavUtil.bind(this, nav);
        }
    }
}
