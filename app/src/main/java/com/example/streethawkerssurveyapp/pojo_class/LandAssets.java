package com.example.streethawkerssurveyapp.pojo_class;

public class LandAssets {

    private String plot;
    private String house_size;
    private String area;
    private String kucchha;
    private String rental_income;

    public LandAssets(String plot, String house_size, String area, String kucchha, String rental_income) {
        this.plot = plot;
        this.house_size = house_size;
        this.area = area;
        this.kucchha = kucchha;
        this.rental_income = rental_income;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getHouse_size() {
        return house_size;
    }

    public void setHouse_size(String house_size) {
        this.house_size = house_size;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getKucchha() {
        return kucchha;
    }

    public void setKucchha(String kucchha) {
        this.kucchha = kucchha;
    }

    public String getRental_income() {
        return rental_income;
    }

    public void setRental_income(String rental_income) {
        this.rental_income = rental_income;
    }
}
