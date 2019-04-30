package amirjaber.practice.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amirjaber.practice.activities.R;
import amirjaber.practice.activities.dtos.ParcelableDto;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<ParcelableDto> parcelableDtoArrayList;
    private OnItemClickListener clickListener;

    public RecyclerViewAdapter(ArrayList<ParcelableDto> exampleList) {
        parcelableDtoArrayList = exampleList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new RecyclerViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ParcelableDto currentItem = parcelableDtoArrayList.get(position);

        holder.ivIcon.setImageResource(currentItem.getImageResource());
        holder.tvUpperLine.setText(currentItem.getText1());
        holder.tvBottomLine.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return parcelableDtoArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon, ivDelete;
        TextView tvUpperLine;
        TextView tvBottomLine;

        RecyclerViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvUpperLine = itemView.findViewById(R.id.tv_upper_line);
            tvBottomLine = itemView.findViewById(R.id.tv_botton_line);
            ivDelete = itemView.findViewById(R.id.iv_delete);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

            ivDelete.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position);
                    }
                }
            });


        }
    }

}
