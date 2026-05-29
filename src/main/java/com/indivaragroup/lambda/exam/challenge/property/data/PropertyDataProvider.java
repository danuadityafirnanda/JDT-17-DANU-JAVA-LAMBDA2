package com.indivaragroup.lambda.exam.challenge.property.data;

import com.indivaragroup.lambda.exam.challenge.property.model.PropertyAsset;

import java.util.ArrayList;
import java.util.List;

public class PropertyDataProvider {
    public static List<PropertyAsset> getSampleData() {
        return new ArrayList<>(List.of(
                new PropertyAsset("P01", "Rumah Cluster Emerald", "RUMAH", "Bekasi Timur", 1200000000, 120, 90, false, 2020),
                new PropertyAsset("P02", "Apartemen Grand Bekasi", "APARTEMEN", "Bekasi Barat", 650000000, 0, 45, true, 2019),
                new PropertyAsset("P03", "Ruko Jalan Ahmad Yani", "RUKO", "Bekasi Selatan", 2500000000L, 80, 160, false, 2018),
                new PropertyAsset("P04", "Tanah Kavling Jatibening", "TANAH", "Bekasi Timur", 800000000, 200, 0, false, 0),
                new PropertyAsset("P05", "Rumah Minimalis Pekayon", "RUMAH", "Bekasi Selatan", 950000000, 90, 70, true, 2021),
                new PropertyAsset("P06", "Apartemen Meikarta Tower B", "APARTEMEN", "Bekasi Barat", 450000000, 0, 36, false, 2022),
                new PropertyAsset("P07", "Ruko Galaxy Bekasi", "RUKO", "Bekasi Utara", 3100000000L, 100, 200, true, 2017),
                new PropertyAsset("P08", "Rumah Mewah Kemang Pratama", "RUMAH", "Bekasi Selatan", 4500000000L, 300, 250, false, 2023)
        ));
    }
}