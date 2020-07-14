package com.example.streethawkerssurveyapp.pojo_class;

public class CriminalCases {

    private String criminal_case_number;
    private String criminal_case_date;
    private String criminal_case_fir_number;
    private String criminal_case_name_of_police;
    private String criminal_case_status;

    public CriminalCases(String criminal_case_number, String criminal_case_date, String criminal_case_fir_number, String criminal_case_name_of_police, String criminal_case_status) {
        this.criminal_case_number = criminal_case_number;
        this.criminal_case_date = criminal_case_date;
        this.criminal_case_fir_number = criminal_case_fir_number;
        this.criminal_case_name_of_police = criminal_case_name_of_police;
        this.criminal_case_status = criminal_case_status;
    }

    public String getCriminal_case_number() {
        return criminal_case_number;
    }

    public void setCriminal_case_number(String criminal_case_number) {
        this.criminal_case_number = criminal_case_number;
    }

    public String getCriminal_case_date() {
        return criminal_case_date;
    }

    public void setCriminal_case_date(String criminal_case_date) {
        this.criminal_case_date = criminal_case_date;
    }

    public String getCriminal_case_fir_number() {
        return criminal_case_fir_number;
    }

    public void setCriminal_case_fir_number(String criminal_case_fir_number) {
        this.criminal_case_fir_number = criminal_case_fir_number;
    }

    public String getCriminal_case_name_of_police() {
        return criminal_case_name_of_police;
    }

    public void setCriminal_case_name_of_police(String criminal_case_name_of_police) {
        this.criminal_case_name_of_police = criminal_case_name_of_police;
    }

    public String getCriminal_case_status() {
        return criminal_case_status;
    }

    public void setCriminal_case_status(String criminal_case_status) {
        this.criminal_case_status = criminal_case_status;
    }
}
