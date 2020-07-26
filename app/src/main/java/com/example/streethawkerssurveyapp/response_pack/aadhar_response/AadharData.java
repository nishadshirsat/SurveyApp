package com.example.streethawkerssurveyapp.response_pack.aadhar_response;

import com.google.gson.annotations.SerializedName;

public class AadharData {

	@SerializedName("zip")
	private String zip;

	@SerializedName("care_of")
	private String careOf;

	@SerializedName("address")
	private Address address;

	@SerializedName("gender")
	private String gender;

	@SerializedName("raw_xml")
	private String rawXml;

	@SerializedName("face_status")
	private boolean faceStatus;

	@SerializedName("face_score")
	private int faceScore;

	@SerializedName("share_code")
	private String shareCode;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("zip_data")
	private String zipData;

	@SerializedName("profile_image")
	private String profileImage;

	@SerializedName("full_name")
	private String fullName;

	@SerializedName("has_image")
	private boolean hasImage;

	@SerializedName("aadhaar_number")
	private String aadhaarNumber;

	@SerializedName("dob")
	private String dob;

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setCareOf(String careOf){
		this.careOf = careOf;
	}

	public String getCareOf(){
		return careOf;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
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

	public void setFaceStatus(boolean faceStatus){
		this.faceStatus = faceStatus;
	}

	public boolean isFaceStatus(){
		return faceStatus;
	}

	public void setFaceScore(int faceScore){
		this.faceScore = faceScore;
	}

	public int getFaceScore(){
		return faceScore;
	}

	public void setShareCode(String shareCode){
		this.shareCode = shareCode;
	}

	public String getShareCode(){
		return shareCode;
	}

	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public String getClientId(){
		return clientId;
	}

	public void setZipData(String zipData){
		this.zipData = zipData;
	}

	public String getZipData(){
		return zipData;
	}

	public void setProfileImage(String profileImage){
		this.profileImage = profileImage;
	}

	public String getProfileImage(){
		return profileImage;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public String getFullName(){
		return fullName;
	}

	public void setHasImage(boolean hasImage){
		this.hasImage = hasImage;
	}

	public boolean isHasImage(){
		return hasImage;
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

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"zip = '" + zip + '\'' + 
			",care_of = '" + careOf + '\'' + 
			",address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",raw_xml = '" + rawXml + '\'' + 
			",face_status = '" + faceStatus + '\'' + 
			",face_score = '" + faceScore + '\'' + 
			",share_code = '" + shareCode + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",zip_data = '" + zipData + '\'' + 
			",profile_image = '" + profileImage + '\'' + 
			",full_name = '" + fullName + '\'' + 
			",has_image = '" + hasImage + '\'' + 
			",aadhaar_number = '" + aadhaarNumber + '\'' + 
			",dob = '" + dob + '\'' + 
			"}";
		}
}