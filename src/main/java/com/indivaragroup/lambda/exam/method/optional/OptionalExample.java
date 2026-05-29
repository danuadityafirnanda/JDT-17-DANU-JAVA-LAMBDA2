package com.indivaragroup.lambda.exam.method.optional;


import java.util.Optional;

public class OptionalExample {

    static Optional<String> findUser(int id) {
        if (id == 1) return Optional.of("Budi");
        return Optional.empty(); // tidak ada user
    }

    public void run() {
        Optional<String> user = findUser(99);

        // Cara 1: cek dengan isPresent()
        if (user.isPresent()) {
            System.out.println(user.get().toUpperCase());
        } else {
            System.out.println("User tidak ditemukan");
        }

        // Cara 2: orElse() — nilai default jika kosong
        String name = user.orElse("Unknown");
        System.out.println(name); // Output: Unknown

        // Cara 3: ifPresent() — jalankan lambda jika ada nilai
        user.ifPresent(u -> System.out.println("Halo, " + u));
        // (tidak ada output karena user kosong)
    }
}

