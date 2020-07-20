package com.example.streethawkerssurveyapp.response_pack;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SurveyDetailsResponse{

	@SerializedName("data")
	private List<SurveyData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<SurveyData> data){
		this.data = data;
	}

	public List<SurveyData> getData(){
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
			"SurveyDetailsResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}