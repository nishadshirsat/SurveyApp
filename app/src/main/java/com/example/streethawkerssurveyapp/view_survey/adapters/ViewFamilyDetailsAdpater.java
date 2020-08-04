package com.example.streethawkerssurveyapp.view_survey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.pojo_class.FamilyMembers;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewFamilyDetailsAdpater extends RecyclerView.Adapter<ViewFamilyDetailsAdpater.MyPassengerHolder> {

    private Context context;
    private List<FamilyMembersItem> familyMembersList;

    public ViewFamilyDetailsAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyPassengerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_family_details,parent,false);

        return new MyPassengerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPassengerHolder holder, int position) {

        holder.fText_Name.setText(familyMembersList.get(position).getFamilyMemberName());
        holder.fText_relatonship.setText("Relatio: "+familyMembersList.get(position).getFamilyMemberRelationship());
        holder.fText_Age.setText("Age: "+familyMembersList.get(position).getFamilyMemberAge());
        holder.fText_aadharno.setText("Aadhar: "+familyMembersList.get(position).getFamilyMemberAdhaar());

    }

    @Override
    public int getItemCount() {
        return familyMembersList.size();
    }

    public void setDetails(List<FamilyMembersItem> familyMembersList) {
        this.familyMembersList = familyMembersList;
        notifyDataSetChanged();
    }

    public class MyPassengerHolder extends RecyclerView.ViewHolder {

        private TextView fText_Name;
        private TextView fText_relatonship;
        private TextView fText_edit;
        private TextView fText_remove;
        private TextView fText_Age;
        private TextView fText_aadharno;
        public MyPassengerHolder(@NonNull View itemView) {
            super(itemView);

            fText_Name = (TextView) itemView.findViewById(R.id.text_Name);
            fText_relatonship = (TextView) itemView.findViewById(R.id.text_relatonship);
            fText_edit = (TextView) itemView.findViewById(R.id.text_edit);
            fText_remove = (TextView) itemView.findViewById(R.id.text_remove);
            fText_Age = (TextView) itemView.findViewById(R.id.text_Age);
            fText_aadharno = (TextView) itemView.findViewById(R.id.text_aadharno);


        }
    }
}
