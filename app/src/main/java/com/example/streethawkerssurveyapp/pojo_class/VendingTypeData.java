package com.example.streethawkerssurveyapp.pojo_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendingTypeData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("vending_type")
    @Expose
    private String vendingType;

    public VendingTypeData(String id, String vendingType) {
        this.id = id;
        this.vendingType = vendingType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendingType() {
        return vendingType;
    }

    public void setVendingType(String vendingType) {
        this.vendingType = vendingType;
    }

    @Override
    public String toString(){
        return vendingType;
    }


}