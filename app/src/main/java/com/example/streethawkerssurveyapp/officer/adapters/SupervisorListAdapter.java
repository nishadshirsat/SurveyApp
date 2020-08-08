package com.example.streethawkerssurveyapp.officer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.supervisor.adapter.SurveyorListAdapter;

public class SupervisorListAdapter extends RecyclerView.Adapter<SupervisorListAdapter.MyViewHolder>{

    Context context;


    @NonNull
    @Override
    public SupervisorListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layour_surveyor_list_data,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupervisorListAdapter.MyViewHolder holder, int position) {

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
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextWard;
        private TextView mTextSurveyorName;
        private TextView mTextCorporation;
        private TextView mTextZone;
        private ImageView mImgRight;
        private TextView mTextTVC;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextWard = (TextView) itemView.findViewById(R.id.TextWard);
            mTextSurveyorName = (TextView) itemView.findViewById(R.id.TextSurveyorName);
            mTextCorporation = (TextView) itemView.findViewById(R.id.TextCorporation);
            mTextZone = (TextView) itemView.findViewById(R.id.TextZone);
            mImgRight = (ImageView) itemView.findViewById(R.id.ImgRight);
            mTextTVC = (TextView) itemView.findViewById(R.id.TextTVC);

        }
    }
}
