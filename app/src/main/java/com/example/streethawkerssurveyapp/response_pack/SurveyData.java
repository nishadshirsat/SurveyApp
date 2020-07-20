package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.SerializedName;

public class SurveyData {

	@SerializedName("survey_id")
	private String surveyId;

	@SerializedName("survey_status")
	private String surveyStatus;

	@SerializedName("survey_name")
	private String surveyName;

	public void setSurveyId(String surveyId){
		this.surveyId = surveyId;
	}

	public String getSurveyId(){
		return surveyId;
	}

	public void setSurveyStatus(String surveyStatus){
		this.surveyStatus = surveyStatus;
	}

	public String getSurveyStatus(){
		return surveyStatus;
	}

	public void setSurveyName(String surveyName){
		this.surveyName = surveyName;
	}

	public String getSurveyName(){
		return surveyName;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"survey_id = '" + surveyId + '\'' + 
			",survey_status = '" + surveyStatus + '\'' + 
			",survey_name = '" + surveyName + '\'' + 
			"}";
		}
}