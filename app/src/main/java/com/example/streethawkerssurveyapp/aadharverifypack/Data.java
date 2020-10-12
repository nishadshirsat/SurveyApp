package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("type")
	private String type;

	@SerializedName("content")
	private String content;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"type = '" + type + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}