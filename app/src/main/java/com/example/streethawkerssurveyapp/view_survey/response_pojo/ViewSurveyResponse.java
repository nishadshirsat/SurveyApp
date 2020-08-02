package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

public class ViewSurveyResponse{

	@SerializedName("response")
	private ViewSurveyBody response;

	@SerializedName("status")
	private boolean status;

	public void setResponse(ViewSurveyBody response){
		this.response = response;
	}

	public ViewSurveyBody getResponse(){
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
			"ViewSurveyResponse{" + 
			"response = '" + response + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}