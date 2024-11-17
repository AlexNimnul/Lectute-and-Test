package org.example;

public class StartTest {
    public static void main(String[] args) {

        String kaka = "1 января новый год 2024?";

        String digits = kaka.replaceAll("[^0-9]", "");

        int number = Integer.parseInt(digits);

        int result = number + 10;
        System.out.println("Результат " + result);
    }
}