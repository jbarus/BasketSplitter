package com.ocado.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitter {
    private final Map<String, List<String>> productTransportMap;

    public BasketSplitter(String absolutePathToConfigFile) {
        this.productTransportMap = FileReader.readFromJSON(absolutePathToConfigFile);
    }

    public Map<String, List<String>> split(List<String> items) {
        checkValidity(items);

        Map<String, List<String>> transportProductCountMap = new HashMap<>();
        Map<String, List<String>> result = new HashMap<>();

        List<String> itemsCopy = new ArrayList<>(items);

        for (String item : itemsCopy) {
            List<String> transportMethod = productTransportMap.get(item);
            if (transportMethod != null) {
                for (String method : transportMethod) {
                    transportProductCountMap.computeIfAbsent(method, k -> new ArrayList<>()).add(item);
                }
            }
        }

        while (!itemsCopy.isEmpty()) {
            List<String> longestList = transportProductCountMap.values().stream()
                    .max(java.util.Comparator.comparingInt(List::size))
                    .orElse(null);

            if (longestList != null) {
                String transportMethod = getKeyByValueAndRemove(transportProductCountMap, longestList);
                result.put(transportMethod, longestList);

                for (List<String> list : transportProductCountMap.values()) {
                    list.removeAll(longestList);
                }
                itemsCopy.removeAll(longestList);
            }
        }
        return result;
    }

    private void checkValidity(List<String> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Item list should contain at least one item");
        }
        for (String item : items) {
            if (!productTransportMap.containsKey(item)) {
                throw new IllegalArgumentException("At least one item is not on the list");
            }
        }
    }

    private String getKeyByValueAndRemove(Map<String, List<String>> map, List<String> value) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                map.remove(entry.getKey());
                return entry.getKey();
            }
        }
        return null;
    }
}