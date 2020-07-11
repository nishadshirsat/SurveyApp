package com.example.streethawkerssurveyapp.pojo_class;

public class FamilyMembers {

    private String family_member_name;
    private String family_member_relationship;
    private String family_member_age;
    private String family_member_adhaar;


    public FamilyMembers(String family_member_name, String family_member_relationship, String family_member_age, String family_member_adhaar) {
        this.family_member_name = family_member_name;
        this.family_member_relationship = family_member_relationship;
        this.family_member_age = family_member_age;
        this.family_member_adhaar = family_member_adhaar;
    }

    public String getFamily_member_name() {
        return family_member_name;
    }

    public void setFamily_member_name(String family_member_name) {
        this.family_member_name = family_member_name;
    }

    public String getFamily_member_relationship() {
        return family_member_relationship;
    }

    public void setFamily_member_relationship(String family_member_relationship) {
        this.family_member_relationship = family_member_relationship;
    }

    public String getFamily_member_age() {
        return family_member_age;
    }

    public void setFamily_member_age(String family_member_age) {
        this.family_member_age = family_member_age;
    }

    public String getFamily_member_adhaar() {
        return family_member_adhaar;
    }

    public void setFamily_member_adhaar(String family_member_adhaar) {
        this.family_member_adhaar = family_member_adhaar;
    }
}
