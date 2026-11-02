package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateUpdateTest {

    @Test
    void generatesCommaSeparatedUpdateWithinRequiredRanges() throws Exception {
        String[] fields = new CertificateUpdate().call().split(",");

        assertTrue(fields[0].matches("\\d+"));
        assertTrue(fields[1].matches("[A-Z]{2}[A-Z0-9]{9}[0-9]"));
        assertEquals(
                IsinGenerator.calculateCheckDigit(fields[1].substring(0, 11)),
                Character.digit(fields[1].charAt(11), 10));
        assertTrue(Double.parseDouble(fields[2]) >= 100.00);
        assertTrue(Double.parseDouble(fields[2]) <= 200.00);
        assertTrue(Integer.parseInt(fields[3]) >= 1_000);
        assertTrue(Integer.parseInt(fields[3]) <= 5_000);
        assertTrue(Double.parseDouble(fields[4]) >= 100.00);
        assertTrue(Double.parseDouble(fields[4]) <= 200.00);
        assertTrue(Integer.parseInt(fields[5]) >= 1_000);
        assertTrue(Integer.parseInt(fields[5]) <= 10_000);
    }
}