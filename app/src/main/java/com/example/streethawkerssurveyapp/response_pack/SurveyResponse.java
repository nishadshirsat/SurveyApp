package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.SerializedName;

public class SurveyResponse{

	@SerializedName("uri_number")
	private String uriNumber;

	@SerializedName("status")
	private boolean status;

	@SerializedName("msg")
	private String Message;

	public void setUriNumber(String uriNumber){
		this.uriNumber = uriNumber;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
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