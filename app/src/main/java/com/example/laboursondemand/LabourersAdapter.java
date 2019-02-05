package com.example.laboursondemand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class LabourersAdapter extends RecyclerView.Adapter<LabourersAdapter.ViewHolder> {

    private Context context;
    private List<Labourer> labourList;

    public LabourersAdapter(Context context, List<Labourer> labourList) {
        this.context = context;
        this.labourList = labourList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, rating, distance, wage;
        CircleImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.labourer_name);
            rating = itemView.findViewById(R.id.labourer_rating);
            distance = itemView.findViewById(R.id.labourer_distance);
            wage = itemView.findViewById(R.id.labourer_wage);
            profileImage = itemView.findViewById(R.id.labourer_civ);
        }
    }

    @NonNull
    @Override
    public LabourersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_labourer, parent, false);
        ViewHolder vh = new ViewHolder(root);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LabourersAdapter.ViewHolder holder, int position) {
        Labourer labourer = labourList.get(position);
        holder.name.setText(labourer.getName());
        holder.rating.setText(String.valueOf(labourer.getRating()));
        holder.wage.setText(String.valueOf(labourer.getWage()));
        holder.distance.setText(String.valueOf(labourer.getDistance()));
        Glide.with(context).load(labourer.getProfile()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return labourList.size();
    }
}
