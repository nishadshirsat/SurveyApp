package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class AadharVerifyResponse{

	@SerializedName("PidData")
	private PidData pidData;

	public void setPidData(PidData pidData){
		this.pidData = pidData;
	}

	public PidData getPidData(){
		return pidData;
	}

	@Override
 	public String toString(){
		return 
			"AadharVerifyResponse{" + 
			"pidData = '" + pidData + '\'' + 
			"}";
		}
}