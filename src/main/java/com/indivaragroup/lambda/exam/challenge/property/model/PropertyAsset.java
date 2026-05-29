package com.indivaragroup.lambda.exam.challenge.property.model;

import com.indivaragroup.lambda.exam.challenge.property.util.PropertyFormatter;

public class PropertyAsset {
    private String id;
    private String propertyName;
    private String propertyType;
    private String location;
    private double price;
    private int landArea;
    private int buildingArea;
    private boolean isSold;
    private int yearBuilt;

    public PropertyAsset() {}

    public PropertyAsset(String id) {
        this.id = id;
    }

    public PropertyAsset(String id, String propertyName, String propertyType, String location, double price, int landArea, int buildingArea, boolean isSold, int yearBuilt) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.location = location;
        this.price = price;
        this.landArea = landArea;
        this.buildingArea = buildingArea;
        this.isSold = isSold;
        this.yearBuilt = yearBuilt;
    }

    public void print() {
        String status = isSold ? "Terjual" : "Belum Terjual";
        System.out.printf("[%s] %-28s | %-9s | %-14s | %s | %dm² | %s%n",
                id, propertyName, propertyType, location,
                PropertyFormatter.formatToRupiah(price), landArea, status);
    }

    public String getId() { return id; }
    public String getPropertyName() { return propertyName; }
    public String getPropertyType() { return propertyType; }
    public String getLocation() { return location; }
    public double getPrice() { return price; }
    public int getLandArea() { return landArea; }
    public int getBuildingArea() { return buildingArea; }
    public boolean isSold() { return isSold; }
    public int getYearBuilt() { return yearBuilt; }
}