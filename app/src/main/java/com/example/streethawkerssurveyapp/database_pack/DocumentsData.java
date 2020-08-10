package com.example.streethawkerssurveyapp.database_pack;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "documentsdata")
public class DocumentsData {

    @PrimaryKey
    @NonNull
    private String id;

    private String survey_id;

    //Documents
    private String identity_proof_type;

    private String identity_proof_front;

    private String identity_proof_back;

    private String vending_history_proof_type;

    private String vending_history_proof_front;

    private String vending_history_proof_back;

    private String allotment_tehzabari_document;

    private String undertaking_doc;

    private String acknowledgement_doc;

    private String other_doc_type;

    private String other_document_url;

    private String other_document_json;

    private String comments;

    private String recording;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getIdentity_proof_type() {
        return identity_proof_type;
    }

    public void setIdentity_proof_type(String identity_proof_type) {
        this.identity_proof_type = identity_proof_type;
    }

    public String getIdentity_proof_front() {
        return identity_proof_front;
    }

    public void setIdentity_proof_front(String identity_proof_front) {
        this.identity_proof_front = identity_proof_front;
    }

    public String getIdentity_proof_back() {
        return identity_proof_back;
    }

    public void setIdentity_proof_back(String identity_proof_back) {
        this.identity_proof_back = identity_proof_back;
    }

    public String getVending_history_proof_type() {
        return vending_history_proof_type;
    }

    public String getOther_document_json() {
        return other_document_json;
    }

    public void setOther_document_json(String other_document_json) {
        this.other_document_json = other_document_json;
    }

    public void setVending_history_proof_type(String vending_history_proof_type) {
        this.vending_history_proof_type = vending_history_proof_type;
    }

    public String getVending_history_proof_front() {
        return vending_history_proof_front;
    }

    public void setVending_history_proof_front(String vending_history_proof_front) {
        this.vending_history_proof_front = vending_history_proof_front;
    }

    public String getVending_history_proof_back() {
        return vending_history_proof_back;
    }

    public void setVending_history_proof_back(String vending_history_proof_back) {
        this.vending_history_proof_back = vending_history_proof_back;
    }

    public String getAllotment_tehzabari_document() {
        return allotment_tehzabari_document;
    }

    public void setAllotment_tehzabari_document(String allotment_tehzabari_document) {
        this.allotment_tehzabari_document = allotment_tehzabari_document;
    }

    public String getUndertaking_doc() {
        return undertaking_doc;
    }

    public void setUndertaking_doc(String undertaking_doc) {
        this.undertaking_doc = undertaking_doc;
    }

    public String getAcknowledgement_doc() {
        return acknowledgement_doc;
    }

    public void setAcknowledgement_doc(String acknowledgement_doc) {
        this.acknowledgement_doc = acknowledgement_doc;
    }

    public String getOther_doc_type() {
        return other_doc_type;
    }

    public void setOther_doc_type(String other_doc_type) {
        this.other_doc_type = other_doc_type;
    }

    public String getOther_document_url() {
        return other_document_url;
    }

    public void setOther_document_url(String other_document_url) {
        this.other_document_url = other_document_url;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
