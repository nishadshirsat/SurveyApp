package com.example.streethawkerssurveyapp.pojo_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendingTypeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<VendingTypeData> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<VendingTypeData> getData() {
        return data;
    }

    public void setData(List<VendingTypeData> data) {
        this.data = data;
    }


}
