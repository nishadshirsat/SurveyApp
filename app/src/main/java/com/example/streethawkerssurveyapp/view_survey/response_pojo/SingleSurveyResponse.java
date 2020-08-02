package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

public class SingleSurveyResponse{

	@SerializedName("response")
	private SingleSurveyDetails response;

	@SerializedName("status")
	private boolean status;

	public void setResponse(SingleSurveyDetails response){
		this.response = response;
	}

	public SingleSurveyDetails getResponse(){
		return response;
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
			"SingleSurveyResponse{" + 
			"response = '" + response + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}