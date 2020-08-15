package com.example.streethawkerssurveyapp.supervisor.response_pojo;

import com.google.gson.annotations.SerializedName;

public class SupervisorViewSurveyResponse{

	@SerializedName("response")
	private SupervisorViewSurveyDetails response;

	@SerializedName("status")
	private boolean status;

	public void setResponse(SupervisorViewSurveyDetails response){
		this.response = response;
	}

	public SupervisorViewSurveyDetails getResponse(){
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
			"SupervisorViewSurveyResponse{" + 
			"response = '" + response + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}