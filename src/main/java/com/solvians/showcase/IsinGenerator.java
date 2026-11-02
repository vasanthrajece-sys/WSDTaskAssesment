package com.solvians.showcase;

import java.util.concurrent.ThreadLocalRandom;

public class IsinGenerator {
    private static final String ALPHANUMERIC =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generateIsin() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StringBuilder base = new StringBuilder(11);

        for (int i = 0; i < 2; i++) {
            base.append((char) ('A' + random.nextInt(26)));
        }

        for (int i = 0; i < 9; i++) {
            base.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        int checkDigit = calculateCheckDigit(base.toString());
        return base.append(checkDigit).toString();
    }

    public static int calculateCheckDigit(String baseIsin) {
        if (baseIsin == null || !baseIsin.matches("[A-Z]{2}[A-Z0-9]{9}")) {
            throw new IllegalArgumentException(
                    "ISIN base must contain two uppercase letters followed by nine uppercase letters or digits.");
        }

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < baseIsin.length(); i++) {
            char character = baseIsin.charAt(i);
            if (character >= 'A' && character <= 'Z') {
                digits.append(character - 'A' + 10);
            } else {
                digits.append(character);
            }
        }

        int sum = 0;
        boolean doubleDigit = true;
        for (int i = digits.length() - 1; i >= 0; i--) {
            int value = digits.charAt(i) - '0';
            if (doubleDigit) {
                value *= 2;
                if (value > 9) {
                    value = value / 10 + value % 10;
                }
            }
            sum += value;
            doubleDigit = !doubleDigit;
        }

        return (10 - sum % 10) % 10;
    }
}
