package com.example.soccer_app;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageButton btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        List<String[]> list = new ArrayList<>();
        list.add(new String[]{
                "New Request",
                "Luke Shaw requested to join Pickup game #1\n@ BMO Soccer center, 8pm, Mon 12 Aug 2026",
                "View Request"
        });
        list.add(new String[]{
                "Request Accepted",
                "Your request to join Pickup game name #2\n@ BMO Soccer center, 8pm, Mon 12 Aug 2026 has been accepted",
                null
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NotificationAdapter(this, list));

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            nav.setSelectedItemId(R.id.nav_notifications);
            NavUtil.bind(this, nav);
        }
    }
}
