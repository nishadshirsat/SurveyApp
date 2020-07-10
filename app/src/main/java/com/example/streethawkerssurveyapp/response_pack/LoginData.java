package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("role")
	private String role;

	@SerializedName("api_key")
	private String apiKey;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setApiKey(String apiKey){
		this.apiKey = apiKey;
	}

	public String getApiKey(){
		return apiKey;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"role = '" + role + '\'' + 
			",api_key = '" + apiKey + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}