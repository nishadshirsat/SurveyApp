package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

public class AadharDetails{

	@SerializedName("subdist")
	private String subdist;

	@SerializedName("loc")
	private String loc;

	@SerializedName("country")
	private String country;

	@SerializedName("care_of")
	private String careOf;

	@SerializedName("gender")
	private String gender;

	@SerializedName("raw_xml")
	private String rawXml;

	@SerializedName("dist")
	private String dist;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("face_score")
	private String faceScore;

	@SerializedName("house")
	private String house;

	@SerializedName("zip_data")
	private String zipData;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("uri_number")
	private String uriNumber;

	@SerializedName("profile_image")
	private String profileImage;

	@SerializedName("has_image")
	private String hasImage;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("street")
	private String street;

	@SerializedName("id")
	private String id;

	@SerializedName("state")
	private String state;

	@SerializedName("landmark")
	private String landmark;

	@SerializedName("zip")
	private String zip;

	@SerializedName("vtc")
	private String vtc;

	@SerializedName("face_status")
	private String faceStatus;

	@SerializedName("share_code")
	private String shareCode;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("aadhaar_number")
	private String aadhaarNumber;

	@SerializedName("dob")
	private String dob;

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

	public void setCareOf(String careOf){
		this.careOf = careOf;
	}

	public String getCareOf(){
		return careOf;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setRawXml(String rawXml){
		this.rawXml = rawXml;
	}

	public String getRawXml(){
		return rawXml;
	}

	public void setDist(String dist){
		this.dist = dist;
	}

	public String getDist(){
		return dist;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setFaceScore(String faceScore){
		this.faceScore = faceScore;
	}

	public String getFaceScore(){
		return faceScore;
	}

	public void setHouse(String house){
		this.house = house;
	}

	public String getHouse(){
		return house;
	}

	public void setZipData(String zipData){
		this.zipData = zipData;
	}

	public String getZipData(){
		return zipData;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	public void setUriNumber(String uriNumber){
		this.uriNumber = uriNumber;
	}

	public String getUriNumber(){
		return uriNumber;
	}

	public void setProfileImage(String profileImage){
		this.profileImage = profileImage;
	}

	public String getProfileImage(){
		return profileImage;
	}

	public void setHasImage(String hasImage){
		this.hasImage = hasImage;
	}

	public String getHasImage(){
		return hasImage;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLandmark(String landmark){
		this.landmark = landmark;
	}

	public String getLandmark(){
		return landmark;
	}

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setVtc(String vtc){
		this.vtc = vtc;
	}

	public String getVtc(){
		return vtc;
	}

	public void setFaceStatus(String faceStatus){
		this.faceStatus = faceStatus;
	}

	public String getFaceStatus(){
		return faceStatus;
	}

	public void setShareCode(String shareCode){
		this.shareCode = shareCode;
	}

	public String getShareCode(){
		return shareCode;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setAadhaarNumber(String aadhaarNumber){
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getAadhaarNumber(){
		return aadhaarNumber;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
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
			"AadharDetails{" + 
			"subdist = '" + subdist + '\'' + 
			",loc = '" + loc + '\'' + 
			",country = '" + country + '\'' + 
			",care_of = '" + careOf + '\'' + 
			",gender = '" + gender + '\'' + 
			",raw_xml = '" + rawXml + '\'' + 
			",dist = '" + dist + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",face_score = '" + faceScore + '\'' + 
			",house = '" + house + '\'' + 
			",zip_data = '" + zipData + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",uri_number = '" + uriNumber + '\'' + 
			",profile_image = '" + profileImage + '\'' + 
			",has_image = '" + hasImage + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",street = '" + street + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			",landmark = '" + landmark + '\'' + 
			",zip = '" + zip + '\'' + 
			",vtc = '" + vtc + '\'' + 
			",face_status = '" + faceStatus + '\'' + 
			",share_code = '" + shareCode + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",aadhaar_number = '" + aadhaarNumber + '\'' + 
			",dob = '" + dob + '\'' + 
			",po = '" + po + '\'' + 
			"}";
		}
}