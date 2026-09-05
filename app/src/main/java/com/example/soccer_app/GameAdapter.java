package com.example.soccer_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private List<Game> games;
    private Context context;

    public GameAdapter(Context context, List<Game> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = games.get(position);
        holder.tvName.setText(game.getName());
        holder.tvDate.setText(game.getDate() + " at " + game.getTime());
        holder.tvLocation.setText(game.getLocation());
        holder.tvHost.setText("Host: " + game.getHost());
        holder.tvCount.setText(game.getCurrentPlayers() + "/" + game.getMaxPlayers());

        holder.btnRequest.setOnClickListener(v -> Toast.makeText(context, "Firestore not connected", Toast.LENGTH_SHORT).show());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("game", game);
            context.startActivity(intent);
        });

        if (holder.badge != null) {
            holder.badge.setOnClickListener(v -> {
                Intent intent = new Intent(context, PlayersListActivity.class);
                intent.putExtra("currentPlayers", game.getCurrentPlayers());
                intent.putExtra("maxPlayers", game.getMaxPlayers());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvLocation, tvHost, tvCount;
        View badge;
        Button btnRequest;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_game_name);
            tvDate = itemView.findViewById(R.id.text_game_date);
            tvLocation = itemView.findViewById(R.id.text_game_location);
            tvHost = itemView.findViewById(R.id.text_game_host);
            tvCount = itemView.findViewById(R.id.text_player_count);
            badge = itemView.findViewById(R.id.badge_container);
            btnRequest = itemView.findViewById(R.id.btn_send_request);
        }
    }
}
