package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftuniDefenseSystem {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        Pattern pattern = Pattern.compile("([A-Z][a-z]+).*?([A-Z][a-z]*[A-Z]).*?(\\d+)L");
        int total = 0;

        String line = scn.nextLine();
        while (!line.equals("OK KoftiShans")) {
            Matcher match = pattern.matcher(line);
            while (match.find()) {
                String guest = match.group(1);
                String alcohol = match.group(2).toLowerCase();
                int quantity = Integer.parseInt(match.group(3));

                System.out.printf("%s brought %d litters of %s!\n", guest, quantity, alcohol);
                total += quantity;
            }
            line = scn.nextLine();
        }
        System.out.printf("%.4f softuni liters", total / 1000.0);
    }
}
