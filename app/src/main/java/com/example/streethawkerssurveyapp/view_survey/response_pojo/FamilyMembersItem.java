package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

public class FamilyMembersItem{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("family_member_relationship")
	private String familyMemberRelationship;

	@SerializedName("id")
	private String id;

	@SerializedName("family_member_name")
	private String familyMemberName;

	@SerializedName("family_member_adhaar")
	private String familyMemberAdhaar;

	@SerializedName("family_member_age")
	private String familyMemberAge;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setFamilyMemberRelationship(String familyMemberRelationship){
		this.familyMemberRelationship = familyMemberRelationship;
	}

	public String getFamilyMemberRelationship(){
		return familyMemberRelationship;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFamilyMemberName(String familyMemberName){
		this.familyMemberName = familyMemberName;
	}

	public String getFamilyMemberName(){
		return familyMemberName;
	}

	public void setFamilyMemberAdhaar(String familyMemberAdhaar){
		this.familyMemberAdhaar = familyMemberAdhaar;
	}

	public String getFamilyMemberAdhaar(){
		return familyMemberAdhaar;
	}

	public void setFamilyMemberAge(String familyMemberAge){
		this.familyMemberAge = familyMemberAge;
	}

	public String getFamilyMemberAge(){
		return familyMemberAge;
	}

	@Override
 	public String toString(){
		return 
			"FamilyMembersItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",family_member_relationship = '" + familyMemberRelationship + '\'' + 
			",id = '" + id + '\'' + 
			",family_member_name = '" + familyMemberName + '\'' + 
			",family_member_adhaar = '" + familyMemberAdhaar + '\'' + 
			",family_member_age = '" + familyMemberAge + '\'' + 
			"}";
		}
}