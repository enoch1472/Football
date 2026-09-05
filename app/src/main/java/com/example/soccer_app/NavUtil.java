package com.example.soccer_app;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavUtil {

    public static void bind(Activity activity, BottomNavigationView nav) {
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_profile) {
                if (!(activity instanceof ProfileActivity)) {
                    activity.startActivity(new Intent(activity, ProfileActivity.class));
                }
                return true;
            } else if (id == R.id.nav_host_game) {
                if (!(activity instanceof HostGameActivity)) {
                    activity.startActivity(new Intent(activity, HostGameActivity.class));
                }
                return true;
            } else if (id == R.id.nav_notifications) {
                if (!(activity instanceof NotificationsActivity)) {
                    activity.startActivity(new Intent(activity, NotificationsActivity.class));
                }
                return true;
            } else if (id == R.id.nav_log_out) {
                Toast.makeText(activity, "Firestore not connected", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}
