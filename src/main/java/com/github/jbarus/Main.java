package com.github.jbarus;

import com.github.jbarus.splitter.BasketSplitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter("C:/Users/Jakub/Downloads/Zadanie/config.json");
        List<String> itemList = new ArrayList<>(Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"));
        System.out.println(basketSplitter.split(itemList));
    }
}