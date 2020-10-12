package com.example.streethawkerssurveyapp.aadharverifypack;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AdditionalInfo{

	@SerializedName("Param")
	private List<ParamItem> param;

	public void setParam(List<ParamItem> param){
		this.param = param;
	}

	public List<ParamItem> getParam(){
		return param;
	}

	@Override
 	public String toString(){
		return 
			"AdditionalInfo{" + 
			"param = '" + param + '\'' + 
			"}";
		}
}