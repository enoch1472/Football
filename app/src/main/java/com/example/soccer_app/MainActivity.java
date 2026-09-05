package com.example.soccer_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GameAdapter adapter;
    private List<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView profileIcon = findViewById(R.id.profile_icon);
        if (profileIcon != null) {
            profileIcon.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        }

        recyclerView = findViewById(R.id.recycler_games);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        games = new ArrayList<>();
        games.add(new Game("Pickup game name #1", "Fri, Aug 21", "7:00 pm",
                "BMO Soccer Center, Field 1", "Bruno Fernandes", 4, 14, "7v7",
                44.6625, -63.6625));
        games.add(new Game("Pickup game name #2", "Fri, Aug 21", "9:00 pm",
                "BMO Soccer Center, Field 2", "Harry Maguire", 10, 14, "7v7",
                44.6625, -63.6625));
        games.add(new Game("Pickup game name #3", "Sat, Aug 22", "5:00 pm",
                "BMO Soccer Center, Field 3", "Benjamin Sesko", 12, 14, "7v7",
                44.6625, -63.6625));
        games.add(new Game("Pickup game name #4", "Sat, Aug 22", "9:00 pm",
                "BMO Soccer Center, Field 4", "Bryan Mbuemo", 8, 14, "7v7",
                44.6625, -63.6625));

        adapter = new GameAdapter(this, games);
        recyclerView.setAdapter(adapter);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            NavUtil.bind(this, nav);
        }
    }
}