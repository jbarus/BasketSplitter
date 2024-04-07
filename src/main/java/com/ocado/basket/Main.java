package com.ocado.basket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter("C:/Users/Jakub/Downloads/Zadanie/config.json");
        List<String> itemList = new ArrayList<>(Arrays.asList("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry", "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea"));
        System.out.println(basketSplitter.split(itemList));
    }
}