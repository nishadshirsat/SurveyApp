package com.example.streethawkerssurveyapp.view_survey.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.DocumentsData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersBeenSurveyedItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewOtherDocDetailsAdpater extends RecyclerView.Adapter<ViewOtherDocDetailsAdpater.MyPassengerHolder> {

    private Context context;
    private List<DocumentsData> documentsDataList;

    public ViewOtherDocDetailsAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyPassengerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_document_details,parent,false);

        return new MyPassengerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPassengerHolder holder, int position) {

        holder.dTextDocName.setText(documentsDataList.get(position).getDocument_type());

        Glide.with(context).load(documentsDataList.get(position).getDocument())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.dImgOtherDocument);
    }

    @Override
    public int getItemCount() {
        return documentsDataList.size();
    }

    public void setDetails(List<DocumentsData> documentsDataList) {
        this.documentsDataList = documentsDataList;
        notifyDataSetChanged();
    }

    public class MyPassengerHolder extends RecyclerView.ViewHolder {

        private ImageView dImgOtherDocument;
        private TextView dTextDocName;

        public MyPassengerHolder(@NonNull View itemView) {
            super(itemView);

            dImgOtherDocument = (ImageView) itemView.findViewById(R.id.ImgOtherDocument);
            dTextDocName = (TextView) itemView.findViewById(R.id.TextDocName);

        }
    }
}
