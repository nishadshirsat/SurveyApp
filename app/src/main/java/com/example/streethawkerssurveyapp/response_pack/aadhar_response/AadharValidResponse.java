package com.example.streethawkerssurveyapp.response_pack.aadhar_response;

import com.google.gson.annotations.SerializedName;

public class AadharValidResponse{

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("data")
	private AadharData data;

	@SerializedName("message_code")
	private String messageCode;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public void setData(AadharData data){
		this.data = data;
	}

	public AadharData getData(){
		return data;
	}

	public void setMessageCode(String messageCode){
		this.messageCode = messageCode;
	}

	public String getMessageCode(){
		return messageCode;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"AadharValidResponse{" + 
			"status_code = '" + statusCode + '\'' + 
			",data = '" + data + '\'' + 
			",message_code = '" + messageCode + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}