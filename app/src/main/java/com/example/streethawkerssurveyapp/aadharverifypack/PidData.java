package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class PidData{

	@SerializedName("Hmac")
	private String hmac;

	@SerializedName("Resp")
	private Resp resp;

	@SerializedName("DeviceInfo")
	private DeviceInfo deviceInfo;

	@SerializedName("Data")
	private Data data;

	@SerializedName("Skey")
	private Skey skey;

	public void setHmac(String hmac){
		this.hmac = hmac;
	}

	public String getHmac(){
		return hmac;
	}

	public void setResp(Resp resp){
		this.resp = resp;
	}

	public Resp getResp(){
		return resp;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo){
		this.deviceInfo = deviceInfo;
	}

	public DeviceInfo getDeviceInfo(){
		return deviceInfo;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setSkey(Skey skey){
		this.skey = skey;
	}

	public Skey getSkey(){
		return skey;
	}

	@Override
 	public String toString(){
		return 
			"PidData{" + 
			"hmac = '" + hmac + '\'' + 
			",resp = '" + resp + '\'' + 
			",deviceInfo = '" + deviceInfo + '\'' + 
			",data = '" + data + '\'' + 
			",skey = '" + skey + '\'' + 
			"}";
		}
}