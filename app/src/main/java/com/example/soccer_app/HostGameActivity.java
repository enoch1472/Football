package com.example.soccer_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Locale;

public class HostGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_game);

        ImageButton btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        EditText etName = findViewById(R.id.edit_game_name);
        EditText etDate = findViewById(R.id.edit_date);
        EditText etTime = findViewById(R.id.edit_time);
        Spinner spLocation = findViewById(R.id.spinner_location);
        Spinner spType = findViewById(R.id.spinner_game_type);
        Button btnFinish = findViewById(R.id.btn_finish);

        if (etDate != null) {
            etDate.setOnClickListener(v -> {
                Calendar cal = Calendar.getInstance();
                new DatePickerDialog(this, (view, year, month, day) -> {
                    etDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", day, month + 1, year));
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
            });
        }

        if (etTime != null) {
            etTime.setOnClickListener(v -> {
                Calendar cal = Calendar.getInstance();
                new TimePickerDialog(this, (view, hour, minute) -> {
                    String amPm = hour >= 12 ? "pm" : "am";
                    int h = hour % 12;
                    if (h == 0) h = 12;
                    etTime.setText(String.format(Locale.getDefault(), "%d:%02d %s", h, minute, amPm));
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();
            });
        }

        if (spLocation != null) {
            String[] locations = {
                    "Select location",
                    "BMO Soccer Center, Field 1",
                    "BMO Soccer Center, Field 2",
                    "BMO Soccer Center, Field 3",
                    "BMO Soccer Center, Field 4"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, locations);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spLocation.setAdapter(adapter);
        }

        if (spType != null) {
            String[] types = {
                    "Select game type",
                    "5v5",
                    "7v7",
                    "9v9",
                    "11v11"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, types);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spType.setAdapter(adapter);
        }

        if (btnFinish != null) {
            btnFinish.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());
        }

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            nav.setSelectedItemId(R.id.nav_host_game);
            NavUtil.bind(this, nav);
        }
    }
}
