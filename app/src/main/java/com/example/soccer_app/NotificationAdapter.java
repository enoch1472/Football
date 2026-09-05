package com.example.soccer_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private List<String[]> items;

    public NotificationAdapter(Context context, List<String[]> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] item = items.get(position);
        holder.tvTitle.setText(item[0]);
        holder.tvDescription.setText(item[1]);

        if (item.length > 2 && item[2] != null) {
            holder.tvAction.setVisibility(View.VISIBLE);
            holder.tvAction.setText(item[2]);
            holder.tvAction.setOnClickListener(v -> Toast.makeText(context, "Firestore not connected", Toast.LENGTH_SHORT).show());
        } else {
            holder.tvAction.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvAction;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_notif_title);
            tvDescription = itemView.findViewById(R.id.text_notif_description);
            tvAction = itemView.findViewById(R.id.text_notif_action);
        }
    }
}
