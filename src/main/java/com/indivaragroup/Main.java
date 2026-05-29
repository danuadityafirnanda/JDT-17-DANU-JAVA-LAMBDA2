package com.indivaragroup;

import com.indivaragroup.lambda.exam.challenge.property.PropertyMain;
import com.indivaragroup.lambda.exam.interfacing.ExampleInterface;
import com.indivaragroup.lambda.exam.interfacing.ExampleParameterInterface;
import com.indivaragroup.lambda.exam.method.optional.NullProblemExample;
import com.indivaragroup.lambda.exam.method.optional.OptionalExample;
import com.indivaragroup.lambda.exam.method.reference.lazy.LazyExample;
import com.indivaragroup.lambda.exam.method.reference.lazy.NotLazyExample;
import com.indivaragroup.lambda.exam.method.reference.printer.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main<T> {


    static void propertyAssets(){
        PropertyMain property = new PropertyMain();
        property.run();
    }

    static void createLambda(){
        ExampleInterface exampleInterface = new ExampleInterface() {
            @Override
            public void run() {
                System.out.println("Hello ini dari anonymous class");
            }
        };

        ExampleInterface exampleInterface1 = () -> System.out.println("Hello, ini dari lambda");

        exampleInterface.run();
        exampleInterface1.run();
    }

    static void createLambdaWithParameter(){
        ExampleParameterInterface exampleParameterInterfaceUpperCase = (String exampleValue) -> {
            String valueChange = exampleValue.toUpperCase();

            if (valueChange.equals("HELLO")) {
                valueChange = "BERAK";
            }

            return valueChange;
        };
        ExampleParameterInterface exampleParameterInterfaceLowerCase = (String exampleValue) -> exampleValue.toLowerCase();

        System.out.println(exampleParameterInterfaceUpperCase.runTransform("Hello"));
        System.out.println(exampleParameterInterfaceLowerCase.runTransform("Hello"));
    }

    static void interfaceConsumer(){
        Consumer<String> printer = (String name) ->  System.out.println("Helo, " + name);
        printer.accept("Danu");

        BiConsumer<String, Integer> info = (String name, Integer age) ->
                System.out.println(name + " berumur " + age + " tahun");

        info.accept("Danu", 22);
        List<String> names = List.of("Budi","Citra","Danu");
        names.forEach(printer);
    }

    static void interfaceFunction(){
        // Definisi Function (sudah ada di Java)
        // Function<T, R> { R apply(T t); }
        // T = tipe input, R = tipe output (bisa berbeda!)

        // Contoh: ubah String menjadi Integer (panjang karakter)
        Function<String, Integer> getLength = (text) -> text.length();

        System.out.println(getLength.apply("Hello"));  // Output: 5
        System.out.println(getLength.apply("Java"));   // Output: 4

        // Contoh: ubah Integer menjadi String
        Function<Integer, String> toLabel = (num) -> "Nomor: " + num;

        System.out.println(toLabel.apply(42));  // Output: Nomor: 42

        // Mengubah nama menjadi huruf besar
        Function<String, String> toUpper = name -> name.toUpperCase();

        System.out.println(toUpper.apply("budi"));  // Output: BUDI

        // Menggabungkan dua Function dengan andThen()
        Function<String, String> addGreeting = name -> "Halo, " + name + "!";
        Function<String, String> greetUpper = toUpper.andThen(addGreeting);

        System.out.println(greetUpper.apply("budi"));  // Output: Halo, BUDI!

    }

    static void interfacePredicate(){
        // Definisi Predicate (sudah ada di Java)
        // Predicate<T> { boolean test(T t); }

        // Contoh: cek apakah angka genap
        Predicate<Integer> isEven = (num) -> num % 2 == 0;

        System.out.println(isEven.test(4));   // Output: true
        System.out.println(isEven.test(7));   // Output: false

        // Menggabungkan Predicate
        Predicate<Integer> isPositive = (num) -> num > 0;
        Predicate<Integer> isPositiveEven = isEven.and(isPositive);

        System.out.println(isPositiveEven.test(4));   // Output: true
        System.out.println(isPositiveEven.test(-4));  // Output: false

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = numbers.stream()
                .filter(isEven)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);  // Output: [2, 4, 6, 8, 10]

        // Filter angka lebih dari 5
        Predicate<Integer> moreThan5 = num -> num > 5;
        List<Integer> bigNumbers = numbers.stream()
                .filter(isEven.and(moreThan5))
                .collect(Collectors.toList());

        System.out.println(bigNumbers);   // Output: [6, 8, 10]

    }

    static void interfaceSupplier(){
        // Definisi Supplier (sudah ada di Java)
        // Supplier<T> { T get(); }

        // Contoh: supplier yang menghasilkan angka random
        Supplier<Integer> randomNum = () -> (int)(Math.random() * 100);

        System.out.println(randomNum.get());  // Output: angka random, misal 42
        System.out.println(randomNum.get());  // Output: angka random lain, misal 87

        // Contoh: supplier untuk membuat objek baru
        Supplier<List<String>> newList = () -> new ArrayList<>();

        List<String> list1 = newList.get();  // List baru
        List<String> list2 = newList.get();  // List baru lainnya

        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("Budi", 85);
        studentScores.put("Citra", 92);
        studentScores.put("Dani", 78);
        studentScores.put("Eka", 95);

        // Tampilkan semua nilai
        System.out.println("=== Daftar Nilai ===");
        studentScores.forEach((name, score) ->
                System.out.println(name + ": " + score)
        );

        // Tampilkan hanya yang lulus (nilai >= 80)
        System.out.println("\n=== Yang Lulus ===");
        studentScores.forEach((name, score) -> {
            if (score >= 80) {
                System.out.println("✓ " + name + " (" + score + ")");
            }
        });
    }

    static void printerExample(){
        // Membuat object
        Printer printer = new Printer("INFO");

        // Dengan Lambda
        Consumer<String> log1 = (msg) -> printer.print(msg);

        // Dengan Method Reference (object::methodName)
        Consumer<String> log2 = printer::print;

        log1.accept("Aplikasi dimulai");  // Output: INFO: Aplikasi dimulai
        log2.accept("Proses selesai");    // Output: INFO: Proses selesai
    }

    static void nullPointerProblem(){
        NullProblemExample nullProblemExample = new NullProblemExample();
        nullProblemExample.run();
    }

    static void optionalExample(){
        OptionalExample optionalExample = new OptionalExample();
        optionalExample.run();
    }

    static void lazyExample(){
        LazyExample lazyExample = new LazyExample();
        lazyExample.run();
    }

    static void notLazyExample(){
        NotLazyExample notLazyExample = new NotLazyExample();
        notLazyExample.run();
    }

    public static void main(String[] args) {
//        createLambda();
//        createLambdaWithParameter();
//        interfaceConsumer();
//        interfaceFunction();
//        interfacePredicate();
//        interfaceSupplier();
//        printerExample();
//        nullPointerProblem();
//        optionalExample();
//        lazyExample();
//        notLazyExample();

        propertyAssets();
    }

    public void loggingMonitoring(T error){
        Consumer<T> printer = (T name) ->  System.out.println("Error, " + name);

        printer.accept(error);
    }
}