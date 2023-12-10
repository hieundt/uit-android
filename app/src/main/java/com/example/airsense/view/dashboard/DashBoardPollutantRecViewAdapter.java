package com.example.airsense.view.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsense.R;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashBoardPollutantRecViewAdapter extends RecyclerView.Adapter<DashBoardPollutantRecViewAdapter.DetailPollutantViewHolder>{
    private Context context;
    private List<WeatherAsset.Attributes.Pollutant> pollutants = new ArrayList<>();
    public DashBoardPollutantRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DetailPollutantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail_pollutant,parent,false);
        return new DashBoardPollutantRecViewAdapter.DetailPollutantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailPollutantViewHolder holder, int position) {
        WeatherAsset.Attributes.Pollutant pollutant = pollutants.get(position);
        holder.name.setText(pollutant.name);
        holder.value.setText(pollutant.value.toString());
        int attributeResourceId = context.getResources()
                .getIdentifier(pollutant.name, "string", context.getPackageName());
        if (attributeResourceId != 0) {
            holder.unit.setText(context.getString(attributeResourceId));
        }

    }

    @Override
    public int getItemCount() {
        return pollutants.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPollutants(List<WeatherAsset.Attributes.Pollutant> pollutants) {
        this.pollutants = pollutants.stream()
                .filter(measurement -> measurement.value != null)
                .collect(Collectors.toList());
        notifyDataSetChanged();
    }

    public class DetailPollutantViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private LinearLayout statusContainer;
        private TextView name, value, unit;
        public DetailPollutantViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.detail_pollutant_card);
            statusContainer = itemView.findViewById(R.id.container_pollutant_status);
            name = itemView.findViewById(R.id.txt_pollutant_name) ;
            value = itemView.findViewById(R.id.txt_pollutant_value);
            unit = itemView.findViewById(R.id.pollutant_unit);
        }
    }
}
