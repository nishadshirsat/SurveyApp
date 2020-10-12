package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class Skey{

	@SerializedName("ci")
	private String ci;

	@SerializedName("content")
	private String content;

	public void setCi(String ci){
		this.ci = ci;
	}

	public String getCi(){
		return ci;
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
			"Skey{" + 
			"ci = '" + ci + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}