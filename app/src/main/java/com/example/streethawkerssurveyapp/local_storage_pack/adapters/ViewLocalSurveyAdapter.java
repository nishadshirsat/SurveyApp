package com.example.streethawkerssurveyapp.local_storage_pack.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.database_pack.PersonalDetails;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.view_survey.activities.ViewSurveyDetailsActivity;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewLocalSurveyAdapter extends RecyclerView.Adapter<ViewLocalSurveyAdapter.MyViewHolder> {
    private Context context;
    private List<PersonalDetails> allSurveyList = new ArrayList<>();

    public ViewLocalSurveyAdapter(Context context) {
        this.context = context;
    }

    private UploadAndRefresh uploadAndRefresh;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_view_local_survey,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PersonalDetails SurveyData = allSurveyList.get(position);

        holder.lTextUriNo.setText("Local SurveyId -"+SurveyData.getSurvey_id());
        holder.lTextVendorName.setText("Vendor Name : "+SurveyData.getName_of_vendor());
        holder.lTextAadhar.setText("Aadhar No : "+SurveyData.getAadhar_number());
//        holder.mTextName.setText("Surveyor Name : "+SurveyData.getSurveyorName());

        holder.lBtn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadAndRefresh.uploadData(SurveyData);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allSurveyList.size();
    }

    public void setList(List<PersonalDetails> allSurveyList) {
        this.allSurveyList = allSurveyList;
        notifyDataSetChanged();
    }

    public void setListner(UploadAndRefresh uploadAndRefresh) {
        this.uploadAndRefresh = uploadAndRefresh;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private androidx.cardview.widget.CardView lCardSurvey;
        private TextView lTextUriNo;
        private TextView lTextVendorName;
        private TextView lTextAadhar;
        private Button lBtn_upload;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            lCardSurvey = (androidx.cardview.widget.CardView) itemView.findViewById(R.id.CardSurvey);
            lTextUriNo = (TextView) itemView.findViewById(R.id.TextUriNo);
            lTextVendorName = (TextView) itemView.findViewById(R.id.TextVendorName);
            lTextAadhar = (TextView) itemView.findViewById(R.id.TextAadhar);
            lBtn_upload = (Button) itemView.findViewById(R.id.Btn_upload);


        }
    }

    public interface UploadAndRefresh{

        void uploadData(PersonalDetails surveyData);
    }

}
