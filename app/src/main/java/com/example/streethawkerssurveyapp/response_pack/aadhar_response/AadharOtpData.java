package com.example.streethawkerssurveyapp.response_pack.aadhar_response;

import com.google.gson.annotations.SerializedName;

public class AadharOtpData {

	@SerializedName("valid_aadhaar")
	private boolean validAadhaar;

	@SerializedName("otp_sent")
	private boolean otpSent;

	@SerializedName("if_number")
	private boolean ifNumber;

	@SerializedName("client_id")
	private String clientId;

	public void setValidAadhaar(boolean validAadhaar){
		this.validAadhaar = validAadhaar;
	}

	public boolean isValidAadhaar(){
		return validAadhaar;
	}

	public void setOtpSent(boolean otpSent){
		this.otpSent = otpSent;
	}

	public boolean isOtpSent(){
		return otpSent;
	}

	public void setIfNumber(boolean ifNumber){
		this.ifNumber = ifNumber;
	}

	public boolean isIfNumber(){
		return ifNumber;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"valid_aadhaar = '" + validAadhaar + '\'' + 
			",otp_sent = '" + otpSent + '\'' + 
			",if_number = '" + ifNumber + '\'' + 
			",client_id = '" + clientId + '\'' + 
			"}";
		}
}