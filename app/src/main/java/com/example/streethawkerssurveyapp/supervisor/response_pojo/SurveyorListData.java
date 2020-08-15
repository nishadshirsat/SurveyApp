package com.example.streethawkerssurveyapp.supervisor.response_pojo;

import com.google.gson.annotations.SerializedName;

public class SurveyorListData {

	@SerializedName("role")
	private String role;

	@SerializedName("phone")
	private String phone;

	@SerializedName("role_id")
	private String roleId;

	@SerializedName("name")
	private String name;

	@SerializedName("allotment")
	private Allotment allotment;

	@SerializedName("id")
	private String id;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAllotment(Allotment allotment){
		this.allotment = allotment;
	}

	public Allotment getAllotment(){
		return allotment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return name;

		}

}