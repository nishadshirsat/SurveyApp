package com.example.streethawkerssurveyapp.view_survey.response_pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DocumentsData implements Serializable {

    @SerializedName("document_type")
    private String document_type;

    @SerializedName("document")
    private String document;

    public DocumentsData(String document_type, String document) {
        this.document_type = document_type;
        this.document = document;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
