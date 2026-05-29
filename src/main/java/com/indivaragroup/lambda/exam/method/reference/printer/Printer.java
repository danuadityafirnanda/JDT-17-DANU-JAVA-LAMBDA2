package com.indivaragroup.lambda.exam.method.reference.printer;

public class Printer {
    private String prefix;

    public Printer(String prefix) {
        this.prefix = prefix;
    }

    public void print(String message) {
        System.out.println(prefix + ": " + message);
    }
}





