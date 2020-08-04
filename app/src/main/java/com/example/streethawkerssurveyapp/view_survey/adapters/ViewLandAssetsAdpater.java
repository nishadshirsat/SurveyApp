package com.example.streethawkerssurveyapp.view_survey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.pojo_class.LandAssets;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.LandFixedAssetsItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewLandAssetsAdpater extends RecyclerView.Adapter<ViewLandAssetsAdpater.MyPassengerHolder> {

    private Context context;
    private List<LandFixedAssetsItem> landassetesList;

    public ViewLandAssetsAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyPassengerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_land_assets,parent,false);

        return new MyPassengerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPassengerHolder holder, int position) {

        holder.lText_HouseSize.setText("House size: "+landassetesList.get(position).getHouseSize());
        holder.lText_plot.setText("Plot: "+landassetesList.get(position).getPlot());
        holder.lText_Area.setText("Area: "+landassetesList.get(position).getArea());
        holder.lText_kuccha.setText("Kuccha: "+landassetesList.get(position).getKucchha());
        holder.lText_Income.setText("Rs: "+landassetesList.get(position).getRentalIncome());

    }

    @Override
    public int getItemCount() {
        return landassetesList.size();
    }

    public void setDetails(List<LandFixedAssetsItem> landassetesList) {
        this.landassetesList = landassetesList;
        notifyDataSetChanged();
    }

    public class MyPassengerHolder extends RecyclerView.ViewHolder {

        private TextView lText_HouseSize;
        private TextView lText_plot;
        private TextView lText_remove;
        private TextView lText_Area;
        private TextView lText_kuccha;
        private TextView lText_Income;

        public MyPassengerHolder(@NonNull View itemView) {
            super(itemView);

            lText_HouseSize = (TextView) itemView.findViewById(R.id.text_HouseSize);
            lText_plot = (TextView) itemView.findViewById(R.id.text_plot);
            lText_remove = (TextView) itemView.findViewById(R.id.text_remove);
            lText_Area = (TextView) itemView.findViewById(R.id.text_Area);
            lText_kuccha = (TextView) itemView.findViewById(R.id.text_kuccha);
            lText_Income = (TextView) itemView.findViewById(R.id.text_Income);



        }
    }
}
