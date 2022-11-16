package com.rkulig.shop.admin.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UploadedFilesNameUtilsTest {

    @ParameterizedTest
    @CsvSource({ // przy pomocy tych dwoch adnotacji okreslamy dane testowe (in) i dane ktore powinnyismy otrzymac (out)
            "test test.png, test-test.png",
            "hello world.png, hello-world.png",
            "óóóóó.png, ooooo.png",
            "Produkt 1.png, produkt-1.png",
            "Produkt__1.png, produkt-1.png"
    })
    void shouldSlugifyFileName(String in, String out) {
        String fileName = UploadedFilesNameUtils.slugifyFileName(in);
        assertEquals(fileName, out);
    }
}