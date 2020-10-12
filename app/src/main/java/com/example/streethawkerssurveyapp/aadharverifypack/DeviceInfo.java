package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class DeviceInfo{

	@SerializedName("additional_info")
	private AdditionalInfo additionalInfo;

	@SerializedName("mc")
	private String mc;

	@SerializedName("dpId")
	private String dpId;

	@SerializedName("rdsId")
	private String rdsId;

	@SerializedName("mi")
	private String mi;

	@SerializedName("rdsVer")
	private String rdsVer;

	@SerializedName("dc")
	private String dc;

	public void setAdditionalInfo(AdditionalInfo additionalInfo){
		this.additionalInfo = additionalInfo;
	}

	public AdditionalInfo getAdditionalInfo(){
		return additionalInfo;
	}

	public void setMc(String mc){
		this.mc = mc;
	}

	public String getMc(){
		return mc;
	}

	public void setDpId(String dpId){
		this.dpId = dpId;
	}

	public String getDpId(){
		return dpId;
	}

	public void setRdsId(String rdsId){
		this.rdsId = rdsId;
	}

	public String getRdsId(){
		return rdsId;
	}

	public void setMi(String mi){
		this.mi = mi;
	}

	public String getMi(){
		return mi;
	}

	public void setRdsVer(String rdsVer){
		this.rdsVer = rdsVer;
	}

	public String getRdsVer(){
		return rdsVer;
	}

	public void setDc(String dc){
		this.dc = dc;
	}

	public String getDc(){
		return dc;
	}

	@Override
 	public String toString(){
		return 
			"DeviceInfo{" + 
			"additional_info = '" + additionalInfo + '\'' + 
			",mc = '" + mc + '\'' + 
			",dpId = '" + dpId + '\'' + 
			",rdsId = '" + rdsId + '\'' + 
			",mi = '" + mi + '\'' + 
			",rdsVer = '" + rdsVer + '\'' + 
			",dc = '" + dc + '\'' + 
			"}";
		}
}