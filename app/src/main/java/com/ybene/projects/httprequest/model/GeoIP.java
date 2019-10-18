package com.ybene.projects.httprequest.model;

import androidx.annotation.NonNull;

public class GeoIP {

    // ===== Attributs =====
    private String status, query, country, countryCode, region;
    // =====================

    // ===== Getters & setters =====
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    // =============================

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Statut : " + status + "\n");
        stringBuilder.append("Query : " + query + "\n");
        stringBuilder.append("Country : " + country + "\n");
        stringBuilder.append("Country code : " + countryCode + "\n");
        stringBuilder.append("Region : " + region);
        return stringBuilder.toString();
    }
}
