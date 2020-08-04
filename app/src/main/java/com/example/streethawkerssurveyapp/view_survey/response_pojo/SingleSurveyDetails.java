package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SingleSurveyDetails {

	@SerializedName("other_documents")
	private String otherDocuments;

	@SerializedName("name_of_the_street_vendor")
	private String nameOfTheStreetVendor;

	@SerializedName("identity_proof_documents_type")
	private String identityProofDocumentsType;

	@SerializedName("name_of_father_husband")
	private String nameOfFatherHusband;

	@SerializedName("permanent_address")
	private String permanentAddress;

	@SerializedName("timing_of_vending_to_1")
	private String timingOfVendingTo1;

	@SerializedName("aadhar_card_details")
	private AadharDetails aadharCardDetails;

	@SerializedName("number_of_yrs_of_vending")
	private String numberOfYrsOfVending;

	@SerializedName("identity_proof_documents_front")
	private String identityProofDocumentsFront;

	@SerializedName("zone")
	private String zone;

	@SerializedName("id")
	private String id;

	@SerializedName("photo_of_the_street_vendor")
	private String photoOfTheStreetVendor;

	@SerializedName("survey_status")
	private String surveyStatus;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("annual_income")
	private String annualIncome;

	@SerializedName("area")
	private String area;

	@SerializedName("vending_history_proof_documents_back")
	private String vendingHistoryProofDocumentsBack;

	@SerializedName("bank_ifsc")
	private String bankIfsc;

	@SerializedName("corporation")
	private String corporation;

	@SerializedName("family_members_been_surveyed")
	private List<FamilyMembersBeenSurveyedItem> familyMembersBeenSurveyed;

	@SerializedName("timing_of_vending_from_1")
	private String timingOfVendingFrom1;

	@SerializedName("criminal_case_details")
	private List<CriminalCaseDetailsItem> criminalCaseDetails;

	@SerializedName("applicant_recognized_as_a_street_vendor")
	private String applicantRecognizedAsAStreetVendor;

	@SerializedName("approved_by")
	private String approvedBy;

	@SerializedName("type_of_vending")
	private String typeOfVending;

	@SerializedName("date_of_start_of_vending_activity")
	private String dateOfStartOfVendingActivity;

	@SerializedName("aadhaar_number")
	private String aadhaarNumber;

	@SerializedName("allotment_of_tehbazari_document")
	private String allotmentOfTehbazariDocument;

	@SerializedName("timing_of_vending_from")
	private String timingOfVendingFrom;

	@SerializedName("name_of_vending_site")
	private String nameOfVendingSite;

	@SerializedName("timing_of_vending_to")
	private String timingOfVendingTo;

	@SerializedName("vending_history_proof_documents_front")
	private String vendingHistoryProofDocumentsFront;

	@SerializedName("name_of_mother")
	private String nameOfMother;

	@SerializedName("identity_proof_documents_back")
	private String identityProofDocumentsBack;

	@SerializedName("bank_account_number")
	private String bankAccountNumber;

	@SerializedName("landline_number")
	private String landlineNumber;

	@SerializedName("date_of_birth")
	private String dateOfBirth;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("recording")
	private String recording;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("ward")
	private String ward;

	@SerializedName("choice_of_vending_area")
	private String choiceOfVendingArea;

	@SerializedName("uri_number")
	private String uriNumber;

	@SerializedName("spouse_name")
	private String spouseName;

	@SerializedName("bank_branch_name")
	private String bankBranchName;

	@SerializedName("whether_widowed_widower")
	private String whetherWidowedWidower;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("vending_history_proof_documents_type")
	private String vendingHistoryProofDocumentsType;

	@SerializedName("bank_name")
	private String bankName;

	@SerializedName("family_members")
	private List<FamilyMembersItem> familyMembers;

	@SerializedName("suveyor_id")
	private String suveyorId;

	@SerializedName("comments")
	private String comments;

	@SerializedName("photo_of_vendor_site")
	private String photoOfVendorSite;

	@SerializedName("sex")
	private String sex;

	@SerializedName("education_status")
	private String educationStatus;

	@SerializedName("contact_number")
	private String contactNumber;

	@SerializedName("land_fixed_assets")
	private List<LandFixedAssetsItem> landFixedAssets;

	@SerializedName("residential_correspondence_address")
	private String residentialCorrespondenceAddress;

	@SerializedName("acknowledgement_receipt")
	private String acknowledgementReceipt;

	@SerializedName("bar_code")
	private String barCode;

	@SerializedName("no_of_days_active")
	private String noOfDaysActive;

	@SerializedName("criminal_case_pending")
	private String criminalCasePending;

	@SerializedName("is_approved")
	private String isApproved;

	@SerializedName("undertaking_by_the_applicant")
	private String undertakingByTheApplicant;

	@SerializedName("category")
	private String category;

	@SerializedName("type_of_structure")
	private String typeOfStructure;

	@SerializedName("age")
	private String age;

	@SerializedName("tehbazari_available")
	private String tehbazariAvailable;

	public void setOtherDocuments(String otherDocuments){
		this.otherDocuments = otherDocuments;
	}

	public String getOtherDocuments(){
		return otherDocuments;
	}

	public void setNameOfTheStreetVendor(String nameOfTheStreetVendor){
		this.nameOfTheStreetVendor = nameOfTheStreetVendor;
	}

	public String getNameOfTheStreetVendor(){
		return nameOfTheStreetVendor;
	}

	public void setIdentityProofDocumentsType(String identityProofDocumentsType){
		this.identityProofDocumentsType = identityProofDocumentsType;
	}

	public String getIdentityProofDocumentsType(){
		return identityProofDocumentsType;
	}

	public void setNameOfFatherHusband(String nameOfFatherHusband){
		this.nameOfFatherHusband = nameOfFatherHusband;
	}

	public String getNameOfFatherHusband(){
		return nameOfFatherHusband;
	}

	public void setPermanentAddress(String permanentAddress){
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentAddress(){
		return permanentAddress;
	}

	public void setTimingOfVendingTo1(String timingOfVendingTo1){
		this.timingOfVendingTo1 = timingOfVendingTo1;
	}

	public String getTimingOfVendingTo1(){
		return timingOfVendingTo1;
	}


	public AadharDetails getAadharCardDetails() {
		return aadharCardDetails;
	}

	public void setAadharCardDetails(AadharDetails aadharCardDetails) {
		this.aadharCardDetails = aadharCardDetails;
	}

	public void setNumberOfYrsOfVending(String numberOfYrsOfVending){
		this.numberOfYrsOfVending = numberOfYrsOfVending;
	}

	public String getNumberOfYrsOfVending(){
		return numberOfYrsOfVending;
	}

	public void setIdentityProofDocumentsFront(String identityProofDocumentsFront){
		this.identityProofDocumentsFront = identityProofDocumentsFront;
	}

	public String getIdentityProofDocumentsFront(){
		return identityProofDocumentsFront;
	}

	public void setZone(String zone){
		this.zone = zone;
	}

	public String getZone(){
		return zone;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setPhotoOfTheStreetVendor(String photoOfTheStreetVendor){
		this.photoOfTheStreetVendor = photoOfTheStreetVendor;
	}

	public String getPhotoOfTheStreetVendor(){
		return photoOfTheStreetVendor;
	}

	public void setSurveyStatus(String surveyStatus){
		this.surveyStatus = surveyStatus;
	}

	public String getSurveyStatus(){
		return surveyStatus;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	public void setAnnualIncome(String annualIncome){
		this.annualIncome = annualIncome;
	}

	public String getAnnualIncome(){
		return annualIncome;
	}

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setVendingHistoryProofDocumentsBack(String vendingHistoryProofDocumentsBack){
		this.vendingHistoryProofDocumentsBack = vendingHistoryProofDocumentsBack;
	}

	public String getVendingHistoryProofDocumentsBack(){
		return vendingHistoryProofDocumentsBack;
	}

	public void setBankIfsc(String bankIfsc){
		this.bankIfsc = bankIfsc;
	}

	public String getBankIfsc(){
		return bankIfsc;
	}

	public void setCorporation(String corporation){
		this.corporation = corporation;
	}

	public String getCorporation(){
		return corporation;
	}

	public void setFamilyMembersBeenSurveyed(List<FamilyMembersBeenSurveyedItem> familyMembersBeenSurveyed){
		this.familyMembersBeenSurveyed = familyMembersBeenSurveyed;
	}

	public List<FamilyMembersBeenSurveyedItem> getFamilyMembersBeenSurveyed(){
		return familyMembersBeenSurveyed;
	}

	public void setTimingOfVendingFrom1(String timingOfVendingFrom1){
		this.timingOfVendingFrom1 = timingOfVendingFrom1;
	}

	public String getTimingOfVendingFrom1(){
		return timingOfVendingFrom1;
	}

	public void setCriminalCaseDetails(List<CriminalCaseDetailsItem> criminalCaseDetails){
		this.criminalCaseDetails = criminalCaseDetails;
	}

	public List<CriminalCaseDetailsItem> getCriminalCaseDetails(){
		return criminalCaseDetails;
	}

	public void setApplicantRecognizedAsAStreetVendor(String applicantRecognizedAsAStreetVendor){
		this.applicantRecognizedAsAStreetVendor = applicantRecognizedAsAStreetVendor;
	}

	public String getApplicantRecognizedAsAStreetVendor(){
		return applicantRecognizedAsAStreetVendor;
	}

	public void setApprovedBy(String approvedBy){
		this.approvedBy = approvedBy;
	}

	public String getApprovedBy(){
		return approvedBy;
	}

	public void setTypeOfVending(String typeOfVending){
		this.typeOfVending = typeOfVending;
	}

	public String getTypeOfVending(){
		return typeOfVending;
	}

	public void setDateOfStartOfVendingActivity(String dateOfStartOfVendingActivity){
		this.dateOfStartOfVendingActivity = dateOfStartOfVendingActivity;
	}

	public String getDateOfStartOfVendingActivity(){
		return dateOfStartOfVendingActivity;
	}

	public void setAadhaarNumber(String aadhaarNumber){
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getAadhaarNumber(){
		return aadhaarNumber;
	}

	public void setAllotmentOfTehbazariDocument(String allotmentOfTehbazariDocument){
		this.allotmentOfTehbazariDocument = allotmentOfTehbazariDocument;
	}

	public String getAllotmentOfTehbazariDocument(){
		return allotmentOfTehbazariDocument;
	}

	public void setTimingOfVendingFrom(String timingOfVendingFrom){
		this.timingOfVendingFrom = timingOfVendingFrom;
	}

	public String getTimingOfVendingFrom(){
		return timingOfVendingFrom;
	}

	public void setNameOfVendingSite(String nameOfVendingSite){
		this.nameOfVendingSite = nameOfVendingSite;
	}

	public String getNameOfVendingSite(){
		return nameOfVendingSite;
	}

	public void setTimingOfVendingTo(String timingOfVendingTo){
		this.timingOfVendingTo = timingOfVendingTo;
	}

	public String getTimingOfVendingTo(){
		return timingOfVendingTo;
	}

	public void setVendingHistoryProofDocumentsFront(String vendingHistoryProofDocumentsFront){
		this.vendingHistoryProofDocumentsFront = vendingHistoryProofDocumentsFront;
	}

	public String getVendingHistoryProofDocumentsFront(){
		return vendingHistoryProofDocumentsFront;
	}

	public void setNameOfMother(String nameOfMother){
		this.nameOfMother = nameOfMother;
	}

	public String getNameOfMother(){
		return nameOfMother;
	}

	public void setIdentityProofDocumentsBack(String identityProofDocumentsBack){
		this.identityProofDocumentsBack = identityProofDocumentsBack;
	}

	public String getIdentityProofDocumentsBack(){
		return identityProofDocumentsBack;
	}

	public void setBankAccountNumber(String bankAccountNumber){
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountNumber(){
		return bankAccountNumber;
	}

	public void setLandlineNumber(String landlineNumber){
		this.landlineNumber = landlineNumber;
	}

	public String getLandlineNumber(){
		return landlineNumber;
	}

	public void setDateOfBirth(String dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setRecording(String recording){
		this.recording = recording;
	}

	public String getRecording(){
		return recording;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setWard(String ward){
		this.ward = ward;
	}

	public String getWard(){
		return ward;
	}

	public void setChoiceOfVendingArea(String choiceOfVendingArea){
		this.choiceOfVendingArea = choiceOfVendingArea;
	}

	public String getChoiceOfVendingArea(){
		return choiceOfVendingArea;
	}

	public void setUriNumber(String uriNumber){
		this.uriNumber = uriNumber;
	}

	public String getUriNumber(){
		return uriNumber;
	}

	public void setSpouseName(String spouseName){
		this.spouseName = spouseName;
	}

	public String getSpouseName(){
		return spouseName;
	}

	public void setBankBranchName(String bankBranchName){
		this.bankBranchName = bankBranchName;
	}

	public String getBankBranchName(){
		return bankBranchName;
	}

	public void setWhetherWidowedWidower(String whetherWidowedWidower){
		this.whetherWidowedWidower = whetherWidowedWidower;
	}

	public String getWhetherWidowedWidower(){
		return whetherWidowedWidower;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setVendingHistoryProofDocumentsType(String vendingHistoryProofDocumentsType){
		this.vendingHistoryProofDocumentsType = vendingHistoryProofDocumentsType;
	}

	public String getVendingHistoryProofDocumentsType(){
		return vendingHistoryProofDocumentsType;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setFamilyMembers(List<FamilyMembersItem> familyMembers){
		this.familyMembers = familyMembers;
	}

	public List<FamilyMembersItem> getFamilyMembers(){
		return familyMembers;
	}

	public void setSuveyorId(String suveyorId){
		this.suveyorId = suveyorId;
	}

	public String getSuveyorId(){
		return suveyorId;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setPhotoOfVendorSite(String photoOfVendorSite){
		this.photoOfVendorSite = photoOfVendorSite;
	}

	public String getPhotoOfVendorSite(){
		return photoOfVendorSite;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getSex(){
		return sex;
	}

	public void setEducationStatus(String educationStatus){
		this.educationStatus = educationStatus;
	}

	public String getEducationStatus(){
		return educationStatus;
	}

	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}

	public String getContactNumber(){
		return contactNumber;
	}

	public void setLandFixedAssets(List<LandFixedAssetsItem> landFixedAssets){
		this.landFixedAssets = landFixedAssets;
	}

	public List<LandFixedAssetsItem> getLandFixedAssets(){
		return landFixedAssets;
	}

	public void setResidentialCorrespondenceAddress(String residentialCorrespondenceAddress){
		this.residentialCorrespondenceAddress = residentialCorrespondenceAddress;
	}

	public String getResidentialCorrespondenceAddress(){
		return residentialCorrespondenceAddress;
	}

	public void setAcknowledgementReceipt(String acknowledgementReceipt){
		this.acknowledgementReceipt = acknowledgementReceipt;
	}

	public String getAcknowledgementReceipt(){
		return acknowledgementReceipt;
	}

	public void setBarCode(String barCode){
		this.barCode = barCode;
	}

	public String getBarCode(){
		return barCode;
	}

	public void setNoOfDaysActive(String noOfDaysActive){
		this.noOfDaysActive = noOfDaysActive;
	}

	public String getNoOfDaysActive(){
		return noOfDaysActive;
	}

	public void setCriminalCasePending(String criminalCasePending){
		this.criminalCasePending = criminalCasePending;
	}

	public String getCriminalCasePending(){
		return criminalCasePending;
	}

	public void setIsApproved(String isApproved){
		this.isApproved = isApproved;
	}

	public String getIsApproved(){
		return isApproved;
	}

	public void setUndertakingByTheApplicant(String undertakingByTheApplicant){
		this.undertakingByTheApplicant = undertakingByTheApplicant;
	}

	public String getUndertakingByTheApplicant(){
		return undertakingByTheApplicant;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setTypeOfStructure(String typeOfStructure){
		this.typeOfStructure = typeOfStructure;
	}

	public String getTypeOfStructure(){
		return typeOfStructure;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	public void setTehbazariAvailable(String tehbazariAvailable){
		this.tehbazariAvailable = tehbazariAvailable;
	}

	public String getTehbazariAvailable(){
		return tehbazariAvailable;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"other_documents = '" + otherDocuments + '\'' + 
			",name_of_the_street_vendor = '" + nameOfTheStreetVendor + '\'' + 
			",identity_proof_documents_type = '" + identityProofDocumentsType + '\'' + 
			",name_of_father_husband = '" + nameOfFatherHusband + '\'' + 
			",permanent_address = '" + permanentAddress + '\'' + 
			",timing_of_vending_to_1 = '" + timingOfVendingTo1 + '\'' + 
			",aadhar_card_details = '" + aadharCardDetails + '\'' + 
			",number_of_yrs_of_vending = '" + numberOfYrsOfVending + '\'' + 
			",identity_proof_documents_front = '" + identityProofDocumentsFront + '\'' + 
			",zone = '" + zone + '\'' + 
			",id = '" + id + '\'' + 
			",photo_of_the_street_vendor = '" + photoOfTheStreetVendor + '\'' + 
			",survey_status = '" + surveyStatus + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",annual_income = '" + annualIncome + '\'' + 
			",area = '" + area + '\'' + 
			",vending_history_proof_documents_back = '" + vendingHistoryProofDocumentsBack + '\'' + 
			",bank_ifsc = '" + bankIfsc + '\'' + 
			",corporation = '" + corporation + '\'' + 
			",family_members_been_surveyed = '" + familyMembersBeenSurveyed + '\'' + 
			",timing_of_vending_from_1 = '" + timingOfVendingFrom1 + '\'' + 
			",criminal_case_details = '" + criminalCaseDetails + '\'' + 
			",applicant_recognized_as_a_street_vendor = '" + applicantRecognizedAsAStreetVendor + '\'' + 
			",approved_by = '" + approvedBy + '\'' + 
			",type_of_vending = '" + typeOfVending + '\'' + 
			",date_of_start_of_vending_activity = '" + dateOfStartOfVendingActivity + '\'' + 
			",aadhaar_number = '" + aadhaarNumber + '\'' + 
			",allotment_of_tehbazari_document = '" + allotmentOfTehbazariDocument + '\'' + 
			",timing_of_vending_from = '" + timingOfVendingFrom + '\'' + 
			",name_of_vending_site = '" + nameOfVendingSite + '\'' + 
			",timing_of_vending_to = '" + timingOfVendingTo + '\'' + 
			",vending_history_proof_documents_front = '" + vendingHistoryProofDocumentsFront + '\'' + 
			",name_of_mother = '" + nameOfMother + '\'' + 
			",identity_proof_documents_back = '" + identityProofDocumentsBack + '\'' + 
			",bank_account_number = '" + bankAccountNumber + '\'' + 
			",landline_number = '" + landlineNumber + '\'' + 
			",date_of_birth = '" + dateOfBirth + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",recording = '" + recording + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",ward = '" + ward + '\'' + 
			",choice_of_vending_area = '" + choiceOfVendingArea + '\'' + 
			",uri_number = '" + uriNumber + '\'' + 
			",spouse_name = '" + spouseName + '\'' + 
			",bank_branch_name = '" + bankBranchName + '\'' + 
			",whether_widowed_widower = '" + whetherWidowedWidower + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",vending_history_proof_documents_type = '" + vendingHistoryProofDocumentsType + '\'' + 
			",bank_name = '" + bankName + '\'' + 
			",family_members = '" + familyMembers + '\'' + 
			",suveyor_id = '" + suveyorId + '\'' + 
			",comments = '" + comments + '\'' + 
			",photo_of_vendor_site = '" + photoOfVendorSite + '\'' + 
			",sex = '" + sex + '\'' + 
			",education_status = '" + educationStatus + '\'' + 
			",contact_number = '" + contactNumber + '\'' + 
			",land_fixed_assets = '" + landFixedAssets + '\'' + 
			",residential_correspondence_address = '" + residentialCorrespondenceAddress + '\'' + 
			",acknowledgement_receipt = '" + acknowledgementReceipt + '\'' + 
			",bar_code = '" + barCode + '\'' + 
			",no_of_days_active = '" + noOfDaysActive + '\'' + 
			",criminal_case_pending = '" + criminalCasePending + '\'' + 
			",is_approved = '" + isApproved + '\'' + 
			",undertaking_by_the_applicant = '" + undertakingByTheApplicant + '\'' + 
			",category = '" + category + '\'' + 
			",type_of_structure = '" + typeOfStructure + '\'' + 
			",age = '" + age + '\'' + 
			",tehbazari_available = '" + tehbazariAvailable + '\'' + 
			"}";
		}
}