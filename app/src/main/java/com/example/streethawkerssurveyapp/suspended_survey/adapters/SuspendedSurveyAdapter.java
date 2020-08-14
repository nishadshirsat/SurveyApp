package com.example.streethawkerssurveyapp.suspended_survey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuspendedSurveyAdapter extends RecyclerView.Adapter<SuspendedSurveyAdapter.MyViewHolder> {
    private Context context;
    private List<ViewSurveyData> allSurveyList = new ArrayList<>();

    public SuspendedSurveyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_suspended_survey,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ViewSurveyData SurveyData = allSurveyList.get(position);

        holder.mTextUriNo.setText("URI No -"+SurveyData.getUriNumber());
        holder.mTextDate.setText("Date : "+SurveyData.getSurveyDate());
        holder.mTextName.setText("Surveyor Name : "+SurveyData.getSurveyorName());
        holder.mTextVendorName.setText("Vendor Name  : "+SurveyData.getNameOfTheStreetVendor());
        holder.mTextCorportaion.setText("Corportation  : "+SurveyData.getCorporation());
        holder.mTextSex.setText("Sex : "+SurveyData.getSex());
        holder.mTextWard.setText("Ward : "+SurveyData.getWard());
        holder.mTextAge.setText("Age : "+SurveyData.getAge());
        holder.mTextZone.setText("Zone : "+SurveyData.getZone());
        holder.mTextCategory.setText("Category : "+SurveyData.getCategory());
        holder.mTextArea.setText("Area : "+SurveyData.getArea());
        holder.TextRemark.setText("Remark : "+SurveyData.getComments());

        holder.mCardSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ViewSurveyDetailsActivity.class);
//                intent.putExtra("URI",SurveyData.getUriNumber());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allSurveyList.size();
    }

    public void setList(List<ViewSurveyData> allSurveyList) {
        this.allSurveyList = allSurveyList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private androidx.cardview.widget.CardView mCardSurvey;
        private TextView mTextUriNo;
        private TextView mTextDate;
        private TextView mTextName;
        private TextView mTextVendorName;
        private TextView mTextCorportaion;
        private TextView mTextSex;
        private TextView mTextWard;
        private TextView mTextAge;
        private TextView mTextZone;
        private TextView mTextCategory;
        private TextView mTextArea;
        private TextView mTextStatus;
        private TextView TextRemark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mCardSurvey = (androidx.cardview.widget.CardView) itemView.findViewById(R.id.CardSurvey);
            mTextUriNo = (TextView) itemView.findViewById(R.id.TextUriNo);
            mTextDate = (TextView) itemView.findViewById(R.id.TextDate);
            mTextName = (TextView) itemView.findViewById(R.id.TextName);
            mTextVendorName = (TextView) itemView.findViewById(R.id.TextVendorName);
            mTextCorportaion = (TextView) itemView.findViewById(R.id.TextCorportaion);
            mTextSex = (TextView) itemView.findViewById(R.id.TextSex);
            mTextWard = (TextView) itemView.findViewById(R.id.TextWard);
            mTextAge = (TextView) itemView.findViewById(R.id.TextAge);
            mTextZone = (TextView) itemView.findViewById(R.id.TextZone);
            mTextCategory = (TextView) itemView.findViewById(R.id.TextCategory);
            mTextArea = (TextView) itemView.findViewById(R.id.TextArea);
            mTextStatus = (TextView) itemView.findViewById(R.id.TextStatus);
            TextRemark = (TextView) itemView.findViewById(R.id.TextRemark);

        }
    }
}
