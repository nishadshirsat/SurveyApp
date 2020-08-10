package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("api_key")
	@Expose
	private String apiKey;
	@SerializedName("role")
	@Expose
	private String role;
	@SerializedName("role_id")
	@Expose
	private String roleId;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("app_access")
	@Expose
	private String appAccess;
	@SerializedName("view_completed_survey")
	@Expose
	private String viewCompletedSurvey;
	@SerializedName("view_pending_survey")
	@Expose
	private String viewPendingSurvey;
	@SerializedName("view_suspended_survey")
	@Expose
	private String viewSuspendedSurvey;
	@SerializedName("create_survey")
	@Expose
	private String createSurvey;
	@SerializedName("update_survey")
	@Expose
	private String updateSurvey;
	@SerializedName("is_active")
	@Expose
	private String isActive;
	@SerializedName("area_alloted")
	@Expose
	private String areaAlloted;
	@SerializedName("corporation")
	@Expose
	private String corporation;
	@SerializedName("zone")
	@Expose
	private String zone;
	@SerializedName("tvc")
	@Expose
	private String tvc;
	@SerializedName("ward")
	@Expose
	private String ward;
	@SerializedName("area")
	@Expose
	private String area;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAppAccess() {
		return appAccess;
	}

	public void setAppAccess(String appAccess) {
		this.appAccess = appAccess;
	}

	public String getViewCompletedSurvey() {
		return viewCompletedSurvey;
	}

	public void setViewCompletedSurvey(String viewCompletedSurvey) {
		this.viewCompletedSurvey = viewCompletedSurvey;
	}

	public String getViewPendingSurvey() {
		return viewPendingSurvey;
	}

	public void setViewPendingSurvey(String viewPendingSurvey) {
		this.viewPendingSurvey = viewPendingSurvey;
	}

	public String getViewSuspendedSurvey() {
		return viewSuspendedSurvey;
	}

	public void setViewSuspendedSurvey(String viewSuspendedSurvey) {
		this.viewSuspendedSurvey = viewSuspendedSurvey;
	}

	public String getCreateSurvey() {
		return createSurvey;
	}

	public void setCreateSurvey(String createSurvey) {
		this.createSurvey = createSurvey;
	}

	public String getUpdateSurvey() {
		return updateSurvey;
	}

	public void setUpdateSurvey(String updateSurvey) {
		this.updateSurvey = updateSurvey;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getAreaAlloted() {
		return areaAlloted;
	}

	public void setAreaAlloted(String areaAlloted) {
		this.areaAlloted = areaAlloted;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getTvc() {
		return tvc;
	}

	public void setTvc(String tvc) {
		this.tvc = tvc;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}