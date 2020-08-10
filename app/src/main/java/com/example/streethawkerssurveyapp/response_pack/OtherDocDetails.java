package com.example.streethawkerssurveyapp.response_pack;

public class OtherDocDetails {

    private String document_type;

    private String document;

    public OtherDocDetails(String document_type, String document) {
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
