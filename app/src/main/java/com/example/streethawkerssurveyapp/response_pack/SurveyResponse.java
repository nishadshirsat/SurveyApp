package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.SerializedName;

public class SurveyResponse{

	@SerializedName("uri_number")
	private String uriNumber;

	@SerializedName("status")
	private boolean status;

	public void setUriNumber(String uriNumber){
		this.uriNumber = uriNumber;
	}

	public String getUriNumber(){
		return uriNumber;
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
			"SurveyResponse{" + 
			"uri_number = '" + uriNumber + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}