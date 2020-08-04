package com.example.streethawkerssurveyapp.view_survey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.pojo_class.CriminalCases;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.CriminalCaseDetailsItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewCriminalCasesAdpater extends RecyclerView.Adapter<ViewCriminalCasesAdpater.MyPassengerHolder> {

    private Context context;
    private List<CriminalCaseDetailsItem> criminalcaseList;

    public ViewCriminalCasesAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyPassengerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_criminal_case,parent,false);

        return new MyPassengerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPassengerHolder holder, int position) {

        holder.cText_case_no.setText("Case no: "+criminalcaseList.get(position).getCriminalCaseNumber());
        holder.cText_case_date.setText("Case date: "+criminalcaseList.get(position).getCriminalCaseDate());
        holder.cText_fir_number.setText("Fir number: "+criminalcaseList.get(position).getCriminalCaseFirNumber());
        holder.cText_name_polica.setText("Police name: "+criminalcaseList.get(position).getCriminalCaseNameOfPolice());
        holder.cText_case_status.setText("Status: "+criminalcaseList.get(position).getCriminalCaseStatus());

    }

    @Override
    public int getItemCount() {
        return criminalcaseList.size();
    }

    public void setDetails(List<CriminalCaseDetailsItem> criminalcaseList) {
        this.criminalcaseList = criminalcaseList;
        notifyDataSetChanged();
    }

    public class MyPassengerHolder extends RecyclerView.ViewHolder {

        private TextView cText_case_no;
        private TextView cText_remove;
        private TextView cText_case_date;
        private TextView cText_fir_number;
        private TextView cText_name_polica;
        private TextView cText_case_status;

        public MyPassengerHolder(@NonNull View itemView) {
            super(itemView);

            cText_case_no = (TextView) itemView.findViewById(R.id.text_case_no);
            cText_remove = (TextView) itemView.findViewById(R.id.text_remove);
            cText_case_date = (TextView) itemView.findViewById(R.id.text_case_date);
            cText_fir_number = (TextView) itemView.findViewById(R.id.text_fir_number);
            cText_name_polica = (TextView) itemView.findViewById(R.id.text_name_polica);
            cText_case_status = (TextView) itemView.findViewById(R.id.text_case_status);




        }
    }
}
