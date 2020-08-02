package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

public class LandFixedAssetsItem{

	@SerializedName("area")
	private String area;

	@SerializedName("house_size")
	private String houseSize;

	@SerializedName("kucchha")
	private String kucchha;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("plot")
	private String plot;

	@SerializedName("rental_income")
	private String rentalIncome;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setHouseSize(String houseSize){
		this.houseSize = houseSize;
	}

	public String getHouseSize(){
		return houseSize;
	}

	public void setKucchha(String kucchha){
		this.kucchha = kucchha;
	}

	public String getKucchha(){
		return kucchha;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPlot(String plot){
		this.plot = plot;
	}

	public String getPlot(){
		return plot;
	}

	public void setRentalIncome(String rentalIncome){
		this.rentalIncome = rentalIncome;
	}

	public String getRentalIncome(){
		return rentalIncome;
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

	@Override
 	public String toString(){
		return 
			"LandFixedAssetsItem{" + 
			"area = '" + area + '\'' + 
			",house_size = '" + houseSize + '\'' + 
			",kucchha = '" + kucchha + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",plot = '" + plot + '\'' + 
			",rental_income = '" + rentalIncome + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}