package com.indivaragroup.lambda.exam.challenge.property;

import com.indivaragroup.lambda.exam.challenge.property.data.PropertyDataProvider;
import com.indivaragroup.lambda.exam.challenge.property.model.PropertyAsset;
import com.indivaragroup.lambda.exam.challenge.property.service.PropertyService;

import java.util.List;

public class PropertyMain {
    public void run() {
        List<PropertyAsset> propertyList = PropertyDataProvider.getSampleData();
        PropertyService propertyService = new PropertyService();

        System.out.println("\n======== Task 1 ========");
        propertyService.displayAllProperties(propertyList);

        System.out.println("\n======== Task 2 ========");
        propertyService.filterUnsoldProperties(propertyList);

        System.out.println("\n======== Task 3 ========");
        propertyService.sortPropertiesByPrice(propertyList);

        System.out.println("\n======== Task 4 ========");
        propertyService.filterPropertiesUsingStream(propertyList);

        System.out.println("\n======== Task 5 ========");
        propertyService.transformPropertyData(propertyList);

        System.out.println("\n======== Task 6 ========");
        propertyService.calculateTotalAndAverage(propertyList);

        System.out.println("\n======== Task 7 ========");
        propertyService.groupProperties(propertyList);

        System.out.println("\n======== Task 8 ========");
        propertyService.constructorReference();

        System.out.println("\n======== Task 9 ========");
        propertyService.customComparator(propertyList);

        System.out.println("\n======== Task 10 ========");
        propertyService.generateReport(propertyList);

        System.out.println("\n===== Task tambahan =====");
        propertyService.executeAdvancedFiltersAndStats(propertyList);
    }
}