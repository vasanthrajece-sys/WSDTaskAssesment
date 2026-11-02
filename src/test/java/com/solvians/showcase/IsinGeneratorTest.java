package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsinGeneratorTest {

    @Test
    void generatesIsinWithValidCheckDigit() {
        String isin = new IsinGenerator().generateIsin();

        assertTrue(isin.matches("[A-Z]{2}[A-Z0-9]{9}[0-9]"));
        assertEquals(
                IsinGenerator.calculateCheckDigit(isin.substring(0, 11)),
                Character.digit(isin.charAt(11), 10));
    }

    @Test
    void calculatesCheckDigitForDocumentedExample() {
        assertEquals(6, IsinGenerator.calculateCheckDigit("DE123456789"));
    }
}