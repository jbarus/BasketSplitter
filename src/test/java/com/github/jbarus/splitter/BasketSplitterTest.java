package com.github.jbarus.splitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BasketSplitterTest {

    private BasketSplitter basketSplitter;

    @BeforeEach
    void setUp() {
        basketSplitter = new BasketSplitter("src/test/resources/config.json");
    }

    @Test
    void testSplitWithEmptyItems() {
        List<String> items = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> basketSplitter.split(items));
    }

    @Test
    void testSplitWithNonexistentProducts() {
        List<String> items = Arrays.asList("NonexistentProduct1", "NonexistentProduct2");
        assertThrows(RuntimeException.class, () -> basketSplitter.split(items));
    }

    @Test
    void testSplitWithOneProductAndOneTransportMethod() {
        List<String> items = Arrays.asList("Cookies Oatmeal Raisin");
        Map<String, List<String>> result = basketSplitter.split(new ArrayList<>(items));
        assertEquals(1, result.size());
        List<String> originalTransportMethods = Arrays.asList("Pick-up point", "Parcel locker");

        boolean isAnyTransportMethodValid = false;
        for (String transportMethod : originalTransportMethods) {
            if (result.containsKey(transportMethod)) {
                isAnyTransportMethodValid = true;
                break;
            }
        }
        assertTrue(isAnyTransportMethodValid);
    }


    @Test
    void testSplitWithMultipleProductsAndMultipleTransportMethods() {
        List<String> items = Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht");
        Map<String, List<String>> result = basketSplitter.split(new ArrayList<>(items));
        assertEquals(2, result.size());
        List<String> originalTransportMethods = Arrays.asList("Pick-up point", "Parcel locker", "In-store pick-up", "Same day delivery", "Courier", "Mailbox delivery", "Next day shipping", "Express Collection");
        int counter = 0;
        for(String method : result.keySet()){
            if(originalTransportMethods.contains(method)){
                counter++;
            }
        }
        assertEquals(2,counter);
    }

}