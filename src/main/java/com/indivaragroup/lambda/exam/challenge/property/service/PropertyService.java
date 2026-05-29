package com.indivaragroup.lambda.exam.challenge.property.service;

import com.indivaragroup.lambda.exam.challenge.property.model.PropertyAsset;
import com.indivaragroup.lambda.exam.challenge.property.util.PropertyFormatter;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PropertyService {

    public void displayAllProperties(List<PropertyAsset> propertyList) {
        propertyList.forEach(PropertyAsset::print);
    }

    public void filterUnsoldProperties(List<PropertyAsset> propertyList) {
        List<PropertyAsset> unsoldProperties = new ArrayList<>(propertyList);
        unsoldProperties.removeIf(PropertyAsset -> PropertyAsset.isSold());
        unsoldProperties.forEach(PropertyAsset::print);
    }

    public void sortPropertiesByPrice(List<PropertyAsset> propertyList) {
        List<PropertyAsset> sortedProperties = new ArrayList<>(propertyList);

        System.out.println("Termurah ke Termahal:");
        sortedProperties.sort(Comparator.comparingDouble(PropertyAsset::getPrice));
        sortedProperties.forEach(PropertyAsset::print);

        System.out.println("Termahal ke Termurah:");
        sortedProperties.sort(Comparator.comparingDouble(PropertyAsset::getPrice).reversed());
        sortedProperties.forEach(PropertyAsset::print);
    }

    public void filterPropertiesUsingStream(List<PropertyAsset> propertyList) {
        System.out.println("1. Tipe RUMAH:");
        propertyList.stream()
                .filter(p -> p.getPropertyType().equals("RUMAH"))
                .forEach(PropertyAsset::print);

        System.out.println("\n2. Lokasi Bekasi Selatan:");
        propertyList.stream()
                .filter(p -> p.getLocation().equals("Bekasi Selatan"))
                .forEach(PropertyAsset::print);

        System.out.println("\n3. Harga dibawah 1M:");
        propertyList.stream()
                .filter(p -> p.getPrice() < 1000000000)
                .forEach(PropertyAsset::print);

        System.out.println("\n4. Luas tanah > 100m² dan belum terjual:");
        Predicate<PropertyAsset> isLandAreaLarge = p -> p.getLandArea() > 100;
        Predicate<PropertyAsset> isNotSold = p -> !p.isSold();

        propertyList.stream()
                .filter(isLandAreaLarge.and(isNotSold))
                .forEach(PropertyAsset::print);
    }

    public void transformPropertyData(List<PropertyAsset> propertyList) {

        List<String> upperCaseNames = propertyList.stream()
                .map(PropertyAsset::getPropertyName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        List<String> formattedPrices = propertyList.stream()
                .map(PropertyAsset::getPrice)
                .map(PropertyFormatter::formatToRupiah)
                .collect(Collectors.toList());

        for (int i = 0; i < upperCaseNames.size(); i++) {
            System.out.println(upperCaseNames.get(i) + " - " + formattedPrices.get(i));
        }
    }

    public void calculateTotalAndAverage(List<PropertyAsset> propertyList) {
        double totalUnsoldValue = propertyList.stream()
                .filter(p -> !p.isSold())
                .mapToDouble(PropertyAsset::getPrice)
                .sum();
        System.out.println("Total nilai property belum terjual: " + PropertyFormatter.formatToRupiah(totalUnsoldValue));

        System.out.println("Rata-rata harga per tipe:");
        Map<String, Double> averagePriceByType = propertyList.stream()
                .collect(Collectors.groupingBy(
                        PropertyAsset::getPropertyType,
                        Collectors.averagingDouble(PropertyAsset::getPrice)
                ));

        averagePriceByType.forEach((type, average) ->
                System.out.println(type + " : " + PropertyFormatter.formatToRupiah(average))
        );
    }

    public void groupProperties(List<PropertyAsset> propertyList) {
        System.out.println("Grouping by type:");
        propertyList.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getPropertyType))
                .forEach((type, props) -> System.out.println(type + ": " + props.size() + " property"));

        System.out.println("\nGrouping by lokasi (Count):");
        propertyList.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation, Collectors.counting()))
                .forEach((location, count) -> System.out.println(location + " : " + count + " property"));

        System.out.println("\n>> Grouping by status terjual:");
        propertyList.stream()
                .collect(Collectors.partitioningBy(PropertyAsset::isSold))
                .forEach((status, props) -> {
                    System.out.println(status ? "TERJUAL:" : "BELUM TERJUAL:");
                    props.forEach(p -> System.out.println(" - " + p.getPropertyName()));
                });
    }

    public PropertyAsset createNewProperty(Supplier<PropertyAsset> assetSupplier) {
        return assetSupplier.get();
    }

    public void constructorReference() {
        Supplier<PropertyAsset> assetSupplier = PropertyAsset::new;
        PropertyAsset newAsset = createNewProperty(assetSupplier);
        System.out.println("Berhasil membuat object menggunakan Supplier: " + (newAsset != null));

        Function<String, PropertyAsset> assetByIdFunction = PropertyAsset::new;
        PropertyAsset assetWithId = assetByIdFunction.apply("P99");
        System.out.println("Berhasil membuat object dengan Function ID: " + assetWithId.getId());
    }

    public void customComparator(List<PropertyAsset> propertyList) {
        System.out.println("Sort by lokasi (ascending) dan harga (descending):");
        List<PropertyAsset> locationAndPriceSorted = new ArrayList<>(propertyList);
        locationAndPriceSorted.sort(
                Comparator.comparing(PropertyAsset::getLocation)
                        .thenComparing(Comparator.comparingDouble(PropertyAsset::getPrice).reversed())
        );
        locationAndPriceSorted.forEach(PropertyAsset::print);

        System.out.println("\n>> Sort by tipe (ascending) lalu tahun dibangun (descending):");
        List<PropertyAsset> typeAndYearSorted = new ArrayList<>(propertyList);
        typeAndYearSorted.sort(
                Comparator.comparing(PropertyAsset::getPropertyType)
                        .thenComparing(Comparator.comparingInt(PropertyAsset::getYearBuilt).reversed())
        );
        typeAndYearSorted.forEach(PropertyAsset::print);
    }

    public void generateReport(List<PropertyAsset> propertyList) {
        long totalProperties = propertyList.size();
        long soldPropertiesCount = propertyList.stream().filter(PropertyAsset::isSold).count();
        long availablePropertiesCount = totalProperties - soldPropertiesCount;

        List<PropertyAsset> availableProperties = propertyList.stream()
                .filter(p -> !p.isSold())
                .sorted(Comparator.comparingDouble(PropertyAsset::getPrice))
                .collect(Collectors.toList());

        double totalAvailableValue = availableProperties.stream()
                .mapToDouble(PropertyAsset::getPrice)
                .sum();

        Map<String, Long> locationDistribution = propertyList.stream()
                .collect(Collectors.groupingBy(PropertyAsset::getLocation, Collectors.counting()));

        System.out.println("\n==============================================");
        System.out.println(" LAPORAN ASSETS PROPERTY BEKASI REALTY GROUP");
        System.out.println("==============================================");
        System.out.println("\nTotal Property       : " + totalProperties);
        System.out.println("Property Terjual     : " + soldPropertiesCount);
        System.out.println("Property Tersedia    : " + availablePropertiesCount);

        System.out.println("\n--- PROPERTY TERSEDIA (Sorted by Harga) ---");
        int index = 1;
        for (PropertyAsset p : availableProperties) {
            System.out.printf("%d. [%s] %-28s | %s%n",
                    index++, p.getId(), p.getPropertyName(), PropertyFormatter.formatToRupiah(p.getPrice()));
        }

        System.out.println("\n--- TOTAL NILAI ASSETS TERSEDIA ---");
        System.out.println("Total: " + PropertyFormatter.formatToRupiah(totalAvailableValue));

        System.out.println("\n--- DISTRIBUSI PER LOKASI ---");
        locationDistribution.forEach((location, count) ->
                System.out.printf("%-15s : %d property%n", location, count)
        );

        System.out.println("\n========================================");
    }

    public void executeAdvancedFiltersAndStats(List<PropertyAsset> propertyList) {
        BiFunction<String, String, List<PropertyAsset>> filterByTypeAndLocation = (type, location) ->
                propertyList.stream()
                        .filter(p -> p.getPropertyType().equalsIgnoreCase(type) && p.getLocation().equalsIgnoreCase(location))
                        .collect(Collectors.toList());

        System.out.println("1. BiFunction (RUMAH, Bekasi Selatan):");
        filterByTypeAndLocation.apply("RUMAH", "Bekasi Selatan").forEach(PropertyAsset::print);

        System.out.println("\n2. Statistik harga per tipe:");
        Map<String, DoubleSummaryStatistics> priceStatisticsByType = propertyList.stream()
                .collect(Collectors.groupingBy(
                        PropertyAsset::getPropertyType,
                        Collectors.summarizingDouble(PropertyAsset::getPrice)
                ));

        priceStatisticsByType.forEach((type, stat) -> {
            System.out.println(type + " -> Min: " + PropertyFormatter.formatToRupiah(stat.getMin()) +
                    ", Max: " + PropertyFormatter.formatToRupiah(stat.getMax()) +
                    ", Avg: " + PropertyFormatter.formatToRupiah(stat.getAverage()));
        });

        System.out.println("\n3. Optional untuk handle case ID tidak ditemukan:");
        String targetId = "P99";
        Optional<PropertyAsset> foundProperty = propertyList.stream()
                .filter(p -> p.getId().equals(targetId))
                .findFirst();

        foundProperty.ifPresentOrElse(
                PropertyAsset::print,
                () -> System.out.println("Property dengan ID " + targetId + " tidak ditemukan!")
        );
    }
}