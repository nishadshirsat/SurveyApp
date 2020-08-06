package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CriminalCaseDetailsItem implements Serializable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("criminal_case_number")
	private String criminalCaseNumber;

	@SerializedName("criminal_case_fir_number")
	private String criminalCaseFirNumber;

	@SerializedName("criminal_case_name_of_police")
	private String criminalCaseNameOfPolice;

	@SerializedName("criminal_case_date")
	private String criminalCaseDate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("criminal_case_status")
	private String criminalCaseStatus;


	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public CriminalCaseDetailsItem(String criminalCaseNumber,
								   String criminalCaseFirNumber,
								   String criminalCaseNameOfPolice,
								   String criminalCaseDate,
								   String criminalCaseStatus) {
		this.criminalCaseNumber = criminalCaseNumber;
		this.criminalCaseFirNumber = criminalCaseFirNumber;
		this.criminalCaseNameOfPolice = criminalCaseNameOfPolice;
		this.criminalCaseDate = criminalCaseDate;
		this.criminalCaseStatus = criminalCaseStatus;
	}

	public void setCriminalCaseNumber(String criminalCaseNumber){
		this.criminalCaseNumber = criminalCaseNumber;
	}

	public String getCriminalCaseNumber(){
		return criminalCaseNumber;
	}

	public void setCriminalCaseFirNumber(String criminalCaseFirNumber){
		this.criminalCaseFirNumber = criminalCaseFirNumber;
	}

	public String getCriminalCaseFirNumber(){
		return criminalCaseFirNumber;
	}

	public void setCriminalCaseNameOfPolice(String criminalCaseNameOfPolice){
		this.criminalCaseNameOfPolice = criminalCaseNameOfPolice;
	}

	public String getCriminalCaseNameOfPolice(){
		return criminalCaseNameOfPolice;
	}

	public void setCriminalCaseDate(String criminalCaseDate){
		this.criminalCaseDate = criminalCaseDate;
	}

	public String getCriminalCaseDate(){
		return criminalCaseDate;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCriminalCaseStatus(String criminalCaseStatus){
		this.criminalCaseStatus = criminalCaseStatus;
	}

	public String getCriminalCaseStatus(){
		return criminalCaseStatus;
	}

	@Override
 	public String toString(){
		return 
			"CriminalCaseDetailsItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",criminal_case_number = '" + criminalCaseNumber + '\'' + 
			",criminal_case_fir_number = '" + criminalCaseFirNumber + '\'' + 
			",criminal_case_name_of_police = '" + criminalCaseNameOfPolice + '\'' + 
			",criminal_case_date = '" + criminalCaseDate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",criminal_case_status = '" + criminalCaseStatus + '\'' + 
			"}";
		}
}