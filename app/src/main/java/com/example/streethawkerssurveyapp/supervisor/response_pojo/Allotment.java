package com.example.streethawkerssurveyapp.supervisor.response_pojo;

import com.google.gson.annotations.SerializedName;

public class Allotment{

	@SerializedName("area_name")
	private String areaName;

	@SerializedName("ward_name")
	private String wardName;

	@SerializedName("zone_name")
	private String zoneName;

	@SerializedName("corporation_name")
	private String corporationName;

	@SerializedName("tvc_name")
	private String tvcName;

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return areaName;
	}

	public void setWardName(String wardName){
		this.wardName = wardName;
	}

	public String getWardName(){
		return wardName;
	}

	public void setZoneName(String zoneName){
		this.zoneName = zoneName;
	}

	public String getZoneName(){
		return zoneName;
	}

	public void setCorporationName(String corporationName){
		this.corporationName = corporationName;
	}

	public String getCorporationName(){
		return corporationName;
	}

	public void setTvcName(String tvcName){
		this.tvcName = tvcName;
	}

	public String getTvcName(){
		return tvcName;
	}

	@Override
 	public String toString(){
		return 
			"Allotment{" + 
			"area_name = '" + areaName + '\'' + 
			",ward_name = '" + wardName + '\'' + 
			",zone_name = '" + zoneName + '\'' + 
			",corporation_name = '" + corporationName + '\'' + 
			",tvc_name = '" + tvcName + '\'' + 
			"}";
		}
}