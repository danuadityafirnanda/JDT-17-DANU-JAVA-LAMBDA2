package com.indivaragroup.lambda.exam.method.reference.lazy;

import java.util.function.Supplier;

public class LazyExample {

    // Ganti String dengan Supplier<String>
    static void log(boolean debug, Supplier<String> messageSupplier) {
        if (debug) {
            // .get() hanya dipanggil kalau debug = true
            System.out.println("DEBUG: " + messageSupplier.get());
        }
    }

    static String heavyOperation() {
        System.out.println("(operasi berat dijalankan...)");
        return "hasil operasi berat";
    }

    public void run() {
        boolean debugMode = false;

        // SOLUSI: heavyOperation() hanya dijalankan kalau debug = true
        log(debugMode, () -> heavyOperation());
        // Output: (tidak ada output — operasi berat tidak dijalankan!)

        boolean debugMode2 = true;
        log(debugMode2, () -> heavyOperation());
        // Output: (operasi berat dijalankan...)
        //         DEBUG: hasil operasi berat
    }
}
