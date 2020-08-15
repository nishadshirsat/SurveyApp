package com.example.streethawkerssurveyapp.supervisor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.pending_survey.adapters.PendingSurveyAdapter;
import com.example.streethawkerssurveyapp.supervisor.activities.SurveyorListActivity;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListData;

import java.util.ArrayList;
import java.util.List;

public class SurveyorListAdapter extends RecyclerView.Adapter<SurveyorListAdapter.MyViewHolder>{

    private Context context;
    private List<SurveyorListData> allSurveyorList = new ArrayList<>();

    public SurveyorListAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public SurveyorListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layour_surveyor_list_data,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyorListAdapter.MyViewHolder holder, int position) {

        SurveyorListData SurveyorData = allSurveyorList.get(position);

        holder.mTextSurveyorName.setText(SurveyorData.getName());
        holder.mTextPhone.setText(SurveyorData.getPhone());
        holder.mTextPhone.setText("Mobile: "+SurveyorData.getPhone());
        holder.mTextCorporation.setText("Corporation: "+SurveyorData.getAllotment().getCorporationName());
        holder.mTextZone.setText("Zone: "+SurveyorData.getAllotment().getZoneName());
        holder.mTextArea.setText("Area: "+SurveyorData.getAllotment().getAreaName());
        holder.mTextTVC.setText("TVC: "+SurveyorData.getAllotment().getTvcName());
        holder.mTextWard.setText("Ward: "+SurveyorData.getAllotment().getWardName());


//        holder.mImgRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(holder.mLinearExpand.getVisibility()==View.GONE) {
//                    holder.mImgRight.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
//                    holder.mLinearExpand.setVisibility(View.VISIBLE);
//                }else{
//                    holder.mImgRight.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
//                    holder.mLinearExpand.setVisibility(View.GONE);
//                }
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return allSurveyorList.size();
    }

    public void setList(List<SurveyorListData> allSurveyorList) {
        this.allSurveyorList = allSurveyorList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextWard;
        private TextView mTextSurveyorName;
        private TextView mTextCorporation;
        private TextView mTextZone;
        private ImageView mImgRight;
        private TextView mTextArea;
        private TextView mTextTVC;
        private TextView mTextPhone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextWard = (TextView) itemView.findViewById(R.id.TextWard);
            mTextSurveyorName = (TextView) itemView.findViewById(R.id.TextSurveyorName);
            mTextCorporation = (TextView) itemView.findViewById(R.id.TextCorporation);
            mTextZone = (TextView) itemView.findViewById(R.id.TextZone);
            mImgRight = (ImageView) itemView.findViewById(R.id.ImgRight);
            mTextArea = (TextView) itemView.findViewById(R.id.TextArea);
            mTextTVC = (TextView) itemView.findViewById(R.id.TextTVC);
            mTextPhone = (TextView) itemView.findViewById(R.id.TextPhone);

        }
    }
}
