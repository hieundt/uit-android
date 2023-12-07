package com.example.airsense.view.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsense.R;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;

import java.util.ArrayList;
import java.util.List;

public class DashboardRecViewAdapter extends RecyclerView.Adapter<DashboardRecViewAdapter.BasicWeatherViewHolder> {
    private Context context;
    private List<WeatherAsset> weatherAssets = new ArrayList<>();
    public DashboardRecViewAdapter(Context context) {this.context = context;}

    @NonNull
    @Override
    public BasicWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_basic_weather,parent,false);
        return new BasicWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicWeatherViewHolder holder, int position) {
        WeatherAsset.Attributes assetAttribute = weatherAssets.get(position).attributes;
        if(assetAttribute.place != null) {
            holder.placeText.setText(assetAttribute.place.value.toString());
        }
        if(assetAttribute.pm25 != null) {
            holder.pollutantText.setText(assetAttribute.pm25.value.toString());
        }
        if(assetAttribute.aqi != null) {
            if( 1 < 50 ) {
                holder.qualityText.setText(R.string.good_weather_status);
                holder.qualityText.setBackgroundColor(ContextCompat.getColor(context, R.color.good));
            } else  {
                holder.qualityText.setText(R.string.bad_weather_status);
                holder.qualityText.setBackgroundColor(ContextCompat.getColor(context, R.color.bad));
            }
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,weatherAssets.get(holder.getAdapterPosition()).name,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherAssets.size();
    }

    public void setWeatherAssets(List<WeatherAsset> weatherAssets) {
        this.weatherAssets = weatherAssets;
        notifyDataSetChanged();
    }

    public class BasicWeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView placeText, pollutantText, qualityText;
        private ImageView healthImage;
        private LinearLayout healthImageContainer;
        private LinearLayout parent;

        public BasicWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.basic_weather_card);
            placeText = itemView.findViewById(R.id.txt_place);
            pollutantText = itemView.findViewById(R.id.txt_pollutant_status);
            qualityText = itemView.findViewById(R.id.txt_quality);
            healthImage = itemView.findViewById(R.id.img_health_status);
            healthImageContainer = itemView.findViewById(R.id.container_health_status);
        }
    }
}
