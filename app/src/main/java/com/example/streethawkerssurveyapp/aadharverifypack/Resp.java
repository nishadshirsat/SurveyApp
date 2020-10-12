package com.example.streethawkerssurveyapp.aadharverifypack;

import com.google.gson.annotations.SerializedName;

public class Resp{

	@SerializedName("qScore")
	private String qScore;

	@SerializedName("fType")
	private String fType;

	@SerializedName("errCode")
	private String errCode;

	@SerializedName("iCount")
	private String iCount;

	@SerializedName("pType")
	private String pType;

	@SerializedName("fCount")
	private String fCount;

	@SerializedName("nmPoints")
	private String nmPoints;

	@SerializedName("iType")
	private String iType;

	@SerializedName("pCount")
	private String pCount;

	public void setQScore(String qScore){
		this.qScore = qScore;
	}

	public String getQScore(){
		return qScore;
	}

	public void setFType(String fType){
		this.fType = fType;
	}

	public String getFType(){
		return fType;
	}

	public void setErrCode(String errCode){
		this.errCode = errCode;
	}

	public String getErrCode(){
		return errCode;
	}

	public void setICount(String iCount){
		this.iCount = iCount;
	}

	public String getICount(){
		return iCount;
	}

	public void setPType(String pType){
		this.pType = pType;
	}

	public String getPType(){
		return pType;
	}

	public void setFCount(String fCount){
		this.fCount = fCount;
	}

	public String getFCount(){
		return fCount;
	}

	public void setNmPoints(String nmPoints){
		this.nmPoints = nmPoints;
	}

	public String getNmPoints(){
		return nmPoints;
	}

	public void setIType(String iType){
		this.iType = iType;
	}

	public String getIType(){
		return iType;
	}

	public void setPCount(String pCount){
		this.pCount = pCount;
	}

	public String getPCount(){
		return pCount;
	}

	@Override
 	public String toString(){
		return 
			"Resp{" + 
			"qScore = '" + qScore + '\'' + 
			",fType = '" + fType + '\'' + 
			",errCode = '" + errCode + '\'' + 
			",iCount = '" + iCount + '\'' + 
			",pType = '" + pType + '\'' + 
			",fCount = '" + fCount + '\'' + 
			",nmPoints = '" + nmPoints + '\'' + 
			",iType = '" + iType + '\'' + 
			",pCount = '" + pCount + '\'' + 
			"}";
		}
}