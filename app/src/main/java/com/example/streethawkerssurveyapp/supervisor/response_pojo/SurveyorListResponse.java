package com.example.streethawkerssurveyapp.supervisor.response_pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurveyorListResponse{

	@SerializedName("data")
	private List<SurveyorListData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<SurveyorListData> data){
		this.data = data;
	}

	public List<SurveyorListData> getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"SurveyorListResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}