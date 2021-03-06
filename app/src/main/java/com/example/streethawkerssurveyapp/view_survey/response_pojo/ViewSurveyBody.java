package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ViewSurveyBody implements Serializable {

	@SerializedName("first_page_url")
	private String firstPageUrl;

	@SerializedName("path")
	private String path;

	@SerializedName("per_page")
	private String perPage;

	@SerializedName("total")
	private String total;

	@SerializedName("data")
	private List<ViewSurveyData> data;

	@SerializedName("last_page")
	private String lastPage;

	@SerializedName("last_page_url")
	private String lastPageUrl;

	@SerializedName("next_page_url")
	private String nextPageUrl;

	@SerializedName("from")
	private String from;

	@SerializedName("to")
	private String to;

	@SerializedName("prev_page_url")
	private String prevPageUrl;

	@SerializedName("current_page")
	private String currentPage;



	public void setFirstPageUrl(String firstPageUrl){
		this.firstPageUrl = firstPageUrl;
	}

	public String getFirstPageUrl(){
		return firstPageUrl;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setPerPage(String perPage){
		this.perPage = perPage;
	}

	public String getPerPage(){
		return perPage;
	}

	public void setTotal(String total){
		this.total = total;
	}

	public String getTotal(){
		return total;
	}

	public void setData(List<ViewSurveyData> data){
		this.data = data;
	}

	public List<ViewSurveyData> getData(){
		return data;
	}

	public void setLastPage(String lastPage){
		this.lastPage = lastPage;
	}

	public String getLastPage(){
		return lastPage;
	}

	public void setLastPageUrl(String lastPageUrl){
		this.lastPageUrl = lastPageUrl;
	}

	public String getLastPageUrl(){
		return lastPageUrl;
	}

	public void setNextPageUrl(String nextPageUrl){
		this.nextPageUrl = nextPageUrl;
	}

	public String getNextPageUrl(){
		return nextPageUrl;
	}

	public void setFrom(String from){
		this.from = from;
	}

	public String getFrom(){
		return from;
	}

	public void setTo(String to){
		this.to = to;
	}

	public String getTo(){
		return to;
	}

	public void setPrevPageUrl(String prevPageUrl){
		this.prevPageUrl = prevPageUrl;
	}

	public String getPrevPageUrl(){
		return prevPageUrl;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}

	public String getCurrentPage(){
		return currentPage;
	}



	@Override
 	public String toString(){
		return 
			"Response{" + 
			"first_page_url = '" + firstPageUrl + '\'' + 
			",path = '" + path + '\'' + 
			",per_page = '" + perPage + '\'' + 
			",total = '" + total + '\'' + 
			",data = '" + data + '\'' + 
			",last_page = '" + lastPage + '\'' + 
			",last_page_url = '" + lastPageUrl + '\'' + 
			",next_page_url = '" + nextPageUrl + '\'' + 
			",from = '" + from + '\'' + 
			",to = '" + to + '\'' + 
			",prev_page_url = '" + prevPageUrl + '\'' + 
			",current_page = '" + currentPage + '\'' + 
			"}";
		}
}