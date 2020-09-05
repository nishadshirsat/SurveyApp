package com.example.streethawkerssurveyapp.supervisor.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.supervisor.activities.ViewSurveySupervisorActivity;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SupervisorViewSurveyData;
import com.example.streethawkerssurveyapp.view_survey.activities.ViewSurveyDetailsActivity;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewSurveySupervisorAdapter extends RecyclerView.Adapter<ViewSurveySupervisorAdapter.MyViewHolder> {
    private Context context;
    private List<SupervisorViewSurveyData> allSurveyList = new ArrayList<>();
    private RefreshlistListner refreshlistListner;

    public ViewSurveySupervisorAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_view_survey_supervisor,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SupervisorViewSurveyData SurveyData = allSurveyList.get(position);

        holder.mTextUriNo.setText("URI No -"+SurveyData.getUriNumber());
        holder.mTextDate.setText("Date : "+getIndianTIme(SurveyData.getSurveyDate()));
        holder.mTextName.setText("Surveyor Name : "+SurveyData.getSurveyorName());
        holder.mTextVendorName.setText("Vendor Name  : "+SurveyData.getNameOfTheStreetVendor());
        holder.mTextCorportaion.setText("Corportation  : "+SurveyData.getCorporation());
        holder.mTextSex.setText("Sex : "+SurveyData.getSex());
        holder.mTextWard.setText("Ward : "+SurveyData.getWard());
        holder.mTextAge.setText("Age : "+SurveyData.getAge());
        holder.mTextZone.setText("Zone : "+SurveyData.getZone());
        holder.mTextCategory.setText("Category : "+SurveyData.getCategory());
        holder.mTextArea.setText("Area : "+SurveyData.getArea());
        holder.mTextComments.setText("Comments : "+SurveyData.getComments());
        holder.mTextRemarks.setText("Remark : "+SurveyData.getModComment());

        if (SurveyData.getSurveyStatus().trim().equals("-1")){
            holder.mTextStatus.setText("Status : Suspended");
            holder.mTextStatus.setTextColor(context.getResources().getColor(R.color.red));

        }else if (SurveyData.getSurveyStatus().trim().equals("0")){
            holder.mTextStatus.setText("Status : Pending");
            holder.mTextStatus.setTextColor(context.getResources().getColor(R.color.orange));
        }else if (SurveyData.getSurveyStatus().trim().equals("1")){
            holder.mTextStatus.setText("Status : Completed");
            holder.mTextStatus.setTextColor(context.getResources().getColor(R.color.green));
        }


        if (SurveyData.getSupervisor_check().trim().equals("1")){
            holder.mBtn_Check.setVisibility(View.GONE);
            holder.mBtn_Pending.setVisibility(View.GONE);
            holder.mTextStatus.setText("Status : Completed | CHECKED");

        }else {
            holder.mBtn_Check.setVisibility(View.VISIBLE);
            holder.mBtn_Pending.setVisibility(View.VISIBLE);

        }

        holder.mCardSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewSurveyDetailsActivity.class);
                intent.putExtra("URI",SurveyData.getUriNumber());
                context.startActivity(intent);
            }
        });

        holder.mBtn_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshlistListner.refrehListwithAction(SurveyData.getUriNumber());
            }
        });

        holder.mBtn_Pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshlistListner.refrehListwithPending(SurveyData.getUriNumber());
            }
        });

    }

    @Override
    public int getItemCount() {
        return allSurveyList.size();
    }

    public void setList(List<SupervisorViewSurveyData> allSurveyList) {
        this.allSurveyList = allSurveyList;
        notifyDataSetChanged();
    }

    public void setListner(RefreshlistListner refreshlistListner) {
        this.refreshlistListner = refreshlistListner;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private androidx.cardview.widget.CardView mCardSurvey;
        private TextView mTextUriNo;
        private TextView mTextDate;
        private TextView mTextVendorName;
        private TextView mTextName;
        private TextView mTextCorportaion;
        private TextView mTextSex;
        private TextView mTextWard;
        private TextView mTextAge;
        private TextView mTextZone;
        private TextView mTextCategory;
        private TextView mTextArea;
        private TextView mTextStatus;
        private TextView mTextComments;
        private TextView mTextRemarks;
        private Button mBtn_Check;
        private Button mBtn_Pending;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mCardSurvey = (androidx.cardview.widget.CardView) itemView.findViewById(R.id.CardSurvey);
            mTextUriNo = (TextView) itemView.findViewById(R.id.TextUriNo);
            mTextDate = (TextView) itemView.findViewById(R.id.TextDate);
            mTextVendorName = (TextView) itemView.findViewById(R.id.TextVendorName);
            mTextName = (TextView) itemView.findViewById(R.id.TextName);
            mTextCorportaion = (TextView) itemView.findViewById(R.id.TextCorportaion);
            mTextSex = (TextView) itemView.findViewById(R.id.TextSex);
            mTextWard = (TextView) itemView.findViewById(R.id.TextWard);
            mTextAge = (TextView) itemView.findViewById(R.id.TextAge);
            mTextZone = (TextView) itemView.findViewById(R.id.TextZone);
            mTextCategory = (TextView) itemView.findViewById(R.id.TextCategory);
            mTextArea = (TextView) itemView.findViewById(R.id.TextArea);
            mTextStatus = (TextView) itemView.findViewById(R.id.TextStatus);
            mTextComments = (TextView) itemView.findViewById(R.id.TextComments);
            mTextRemarks = (TextView) itemView.findViewById(R.id.TextRemarks);
            mBtn_Check = (Button) itemView.findViewById(R.id.Btn_Check);
            mBtn_Pending = (Button) itemView.findViewById(R.id.Btn_Pending);


        }
    }

    public String getIndianTIme(String utcDate){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(utcDate);

//            String indianDate =  formatDateToString(date, "yyyy-MM-dd HH:mm:ss", "IST");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 5);
            calendar.add(Calendar.MINUTE, 30);


//            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Or whatever IST is supposed to be
            String indianDate =  formatter.format(calendar.getTime());
            return indianDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static String formatDateToString(Date date, String format,
                                            String timeZone) {
        // null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // default system timezone if passed null or empty
        if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
            timeZone = Calendar.getInstance().getTimeZone().getID();
        }
        // set timezone to SimpleDateFormat
//        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

    public interface RefreshlistListner{

        void refrehListwithAction(String URI);

        void refrehListwithPending(String URI);
    }


}
