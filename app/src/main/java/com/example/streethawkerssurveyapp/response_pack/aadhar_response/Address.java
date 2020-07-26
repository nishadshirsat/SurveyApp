package com.example.streethawkerssurveyapp.response_pack.aadhar_response;

import com.google.gson.annotations.SerializedName;

public class Address{

	@SerializedName("subdist")
	private String subdist;

	@SerializedName("loc")
	private String loc;

	@SerializedName("country")
	private String country;

	@SerializedName("vtc")
	private String vtc;

	@SerializedName("street")
	private String street;

	@SerializedName("dist")
	private String dist;

	@SerializedName("state")
	private String state;

	@SerializedName("landmark")
	private Object landmark;

	@SerializedName("house")
	private String house;

	@SerializedName("po")
	private String po;

	public void setSubdist(String subdist){
		this.subdist = subdist;
	}

	public String getSubdist(){
		return subdist;
	}

	public void setLoc(String loc){
		this.loc = loc;
	}

	public String getLoc(){
		return loc;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setVtc(String vtc){
		this.vtc = vtc;
	}

	public String getVtc(){
		return vtc;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setDist(String dist){
		this.dist = dist;
	}

	public String getDist(){
		return dist;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLandmark(Object landmark){
		this.landmark = landmark;
	}

	public Object getLandmark(){
		return landmark;
	}

	public void setHouse(String house){
		this.house = house;
	}

	public String getHouse(){
		return house;
	}

	public void setPo(String po){
		this.po = po;
	}

	public String getPo(){
		return po;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"subdist = '" + subdist + '\'' + 
			",loc = '" + loc + '\'' + 
			",country = '" + country + '\'' + 
			",vtc = '" + vtc + '\'' + 
			",street = '" + street + '\'' + 
			",dist = '" + dist + '\'' + 
			",state = '" + state + '\'' + 
			",landmark = '" + landmark + '\'' + 
			",house = '" + house + '\'' + 
			",po = '" + po + '\'' + 
			"}";
		}
}