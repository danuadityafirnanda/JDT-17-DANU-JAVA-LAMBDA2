package com.indivaragroup.lambda.exam.method.reference.lazy;

public class NotLazyExample {

    // Method ini menerima String biasa
    static void log(boolean debug, String message) {
        if (debug) {
            System.out.println("DEBUG: " + message);
        }
    }

    static String heavyOperation() {
        System.out.println("(operasi berat dijalankan...)");
        return "hasil operasi berat";
    }

    public void run() {
        boolean debugMode = false;

        // MASALAH: heavyOperation() SELALU dijalankan,
        // meskipun debugMode = false dan hasilnya tidak dipakai!
        log(debugMode, heavyOperation());
        // Output: (operasi berat dijalankan...)
        // Padahal debug = false, jadi tidak perlu!
    }
}
