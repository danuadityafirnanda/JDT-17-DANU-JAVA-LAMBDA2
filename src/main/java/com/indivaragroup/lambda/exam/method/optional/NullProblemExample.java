package com.indivaragroup.lambda.exam.method.optional;

public class NullProblemExample {

    static String findUser(int id) {
        if (id == 1) return "Budi";
        return null; // user tidak ditemukan
    }

    public void run() {
        String user = findUser(99); // tidak ditemukan, return null

        // BAHAYA: ini akan throw NullPointerException!
        System.out.println(user.toUpperCase());
        // Error: Exception in thread "main"
        //        java.lang.NullPointerException

        // Cara lama: cek null manual (verbose dan sering terlupakan)
        if (user != null) {
            System.out.println(user.toUpperCase());
        } else {
            System.out.println("User tidak ditemukan");
        }
    }
}

