package com.example.soccer_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Locale;

public class GameDetailActivity extends AppCompatActivity {

    private MapView mapView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().setUserAgentValue("SoccerApp/1.0 (Android; OpenStreetMap)");

        setContentView(R.layout.activity_game_detail);

        Game game = (Game) getIntent().getSerializableExtra("game");
        if (game == null) {
            game = new Game("Pickup game name #1", "Fri, Aug 21st 2026", "9:00 pm",
                    "BMO Soccer Center, Field 1", "Bruno Fernandes", 4, 14, "7v7",
                    44.6625, -63.6625);
        }

        TextView tvTitle = findViewById(R.id.text_game_title);
        TextView tvHost = findViewById(R.id.text_host);
        TextView tvDate = findViewById(R.id.text_date);
        TextView tvTime = findViewById(R.id.text_time);
        TextView tvType = findViewById(R.id.text_game_type);
        TextView tvPlayers = findViewById(R.id.text_players);
        TextView tvLocation = findViewById(R.id.text_location);
        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnJoin = findViewById(R.id.btn_request_join);

        tvTitle.setText(game.getName());
        tvHost.setText("Host: " + game.getHost());
        tvDate.setText("Date: " + game.getDate());
        tvTime.setText("Time: " + game.getTime());
        tvType.setText("Game type " + game.getGameType());
        tvPlayers.setText(game.getCurrentPlayers() + "/" + game.getMaxPlayers());
        tvLocation.setText("Location: " + game.getLocation());

        btnBack.setOnClickListener(v -> finish());

        btnJoin.setOnClickListener(v -> Toast.makeText(this, "Firestore not connected", Toast.LENGTH_SHORT).show());

        final int current = game.getCurrentPlayers();
        final int max = game.getMaxPlayers();
        tvPlayers.setOnClickListener(v -> {
            Intent intent = new Intent(this, PlayersListActivity.class);
            intent.putExtra("currentPlayers", current);
            intent.putExtra("maxPlayers", max);
            startActivity(intent);
        });

        loadMap(game);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        if (nav != null) {
            NavUtil.bind(this, nav);
        }
    }

    private void loadMap(Game game) {
        double lat = game.getLatitude();
        double lng = game.getLongitude();

        mapView = findViewById(R.id.map_view);
        webView = findViewById(R.id.web_map_view);

        try {
            if (mapView != null) {
                mapView.setTileSource(TileSourceFactory.MAPNIK);
                mapView.setMultiTouchControls(true);
                mapView.setTilesScaledToDpi(true);

                IMapController controller = mapView.getController();
                controller.setZoom(15.5);
                GeoPoint point = new GeoPoint(lat, lng);
                controller.setCenter(point);

                Marker marker = new Marker(mapView);
                marker.setPosition(point);
                marker.setTitle(game.getLocation());
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                mapView.getOverlays().clear();
                mapView.getOverlays().add(marker);
                mapView.invalidate();
            }
        } catch (Exception e) {
            if (mapView != null) mapView.setVisibility(View.GONE);
            if (webView != null) {
                webView.setVisibility(View.VISIBLE);
                loadWebMap(lat, lng, game.getLocation());
            }
        }
    }

    private void loadWebMap(double lat, double lng, String locationName) {
        if (webView == null) return;

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String name = locationName.replace("'", "\\'");
        String html = String.format(Locale.US,
                "<!DOCTYPE html><html><head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' />" +
                "<link rel='stylesheet' href='https://unpkg.com/leaflet@1.9.4/dist/leaflet.css' />" +
                "<script src='https://unpkg.com/leaflet@1.9.4/dist/leaflet.js'></script>" +
                "<style>body,html,#map{margin:0;padding:0;width:100%%;height:100%%;background:#eaeaea;}</style>" +
                "</head><body><div id='map'></div>" +
                "<script>" +
                "var map = L.map('map', {zoomControl:false}).setView([%f, %f], 15);" +
                "L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {maxZoom:19}).addTo(map);" +
                "L.marker([%f, %f]).addTo(map).bindPopup('<b>%s</b>').openPopup();" +
                "</script></body></html>",
                lat, lng, lat, lng, name);

        webView.loadDataWithBaseURL("https://openstreetmap.org", html, "text/html", "UTF-8", null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }
}
