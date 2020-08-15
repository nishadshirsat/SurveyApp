package com.example.streethawkerssurveyapp.supervisor.response_pojo;

import com.google.gson.annotations.SerializedName;

public class SupervisorViewSurveyData {

	@SerializedName("area")
	private String area;

	@SerializedName("comments")
	private String comments;

	@SerializedName("sex")
	private String sex;

	@SerializedName("corporation")
	private String corporation;

	@SerializedName("name_of_the_street_vendor")
	private String nameOfTheStreetVendor;

	@SerializedName("mod_comment")
	private String modComment;

	@SerializedName("ward")
	private String ward;

	@SerializedName("uri_number")
	private String uriNumber;

	@SerializedName("zone")
	private String zone;

	@SerializedName("survey_date")
	private String surveyDate;

	@SerializedName("is_approved")
	private String isApproved;

	@SerializedName("id")
	private String id;

	@SerializedName("surveyor_name")
	private String surveyorName;

	@SerializedName("category")
	private String category;

	@SerializedName("survey_status")
	private String surveyStatus;

	@SerializedName("age")
	private String age;

	@SerializedName("suveyor_id")
	private String suveyorId;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return sex;
	}

	public void setCorporation(String corporation){
		this.corporation = corporation;
	}

	public String getCorporation(){
		return corporation;
	}

	public void setNameOfTheStreetVendor(String nameOfTheStreetVendor){
		this.nameOfTheStreetVendor = nameOfTheStreetVendor;
	}

	public String getNameOfTheStreetVendor(){
		return nameOfTheStreetVendor;
	}

	public void setModComment(String modComment){
		this.modComment = modComment;
	}

	public String getModComment(){
		return modComment;
	}

	public void setWard(String ward){
		this.ward = ward;
	}

	public String getWard(){
		return ward;
	}

	public void setUriNumber(String uriNumber){
		this.uriNumber = uriNumber;
	}

	public String getUriNumber(){
		return uriNumber;
	}

	public void setZone(String zone){
		this.zone = zone;
	}

	public String getZone(){
		return zone;
	}

	public void setSurveyDate(String surveyDate){
		this.surveyDate = surveyDate;
	}

	public String getSurveyDate(){
		return surveyDate;
	}

	public void setIsApproved(String isApproved){
		this.isApproved = isApproved;
	}

	public String getIsApproved(){
		return isApproved;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSurveyorName(String surveyorName){
		this.surveyorName = surveyorName;
	}

	public String getSurveyorName(){
		return surveyorName;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setSurveyStatus(String surveyStatus){
		this.surveyStatus = surveyStatus;
	}

	public String getSurveyStatus(){
		return surveyStatus;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	public void setSuveyorId(String suveyorId){
		this.suveyorId = suveyorId;
	}

	public String getSuveyorId(){
		return suveyorId;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"area = '" + area + '\'' + 
			",comments = '" + comments + '\'' + 
			",sex = '" + sex + '\'' + 
			",corporation = '" + corporation + '\'' + 
			",name_of_the_street_vendor = '" + nameOfTheStreetVendor + '\'' + 
			",mod_comment = '" + modComment + '\'' + 
			",ward = '" + ward + '\'' + 
			",uri_number = '" + uriNumber + '\'' + 
			",zone = '" + zone + '\'' + 
			",survey_date = '" + surveyDate + '\'' + 
			",is_approved = '" + isApproved + '\'' + 
			",id = '" + id + '\'' + 
			",surveyor_name = '" + surveyorName + '\'' + 
			",category = '" + category + '\'' + 
			",survey_status = '" + surveyStatus + '\'' + 
			",age = '" + age + '\'' + 
			",suveyor_id = '" + suveyorId + '\'' + 
			"}";
		}
}