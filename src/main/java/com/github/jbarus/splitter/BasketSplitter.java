package com.github.jbarus.splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketSplitter {
    private HashMap<String, List<String>> productTransportMap;
    public BasketSplitter(String absolutePathToConfigFile) {
        productTransportMap = FileReader.readFromJSON(absolutePathToConfigFile);
    }
    public Map<String, List<String>> split(List<String> items) {
        if(items.isEmpty()){
            throw new IllegalArgumentException("Item list should contain at least");
        }
        for(String item : items){
            if(!productTransportMap.containsKey(item)){
                throw new RuntimeException("At least one item is not on the list");
            }
        }
        Map<String, List<String>> transportProductCountMap = new HashMap<>();
        Map<String, List<String>> result = new HashMap<>();
        for (String item : items) {
            List<String> transportMethod = productTransportMap.get(item);
            for (String method : transportMethod) {
                transportProductCountMap.merge(method, new ArrayList<>(List.of(item)), (oldList, newList) -> {
                    oldList.addAll(newList);
                    return oldList;
                });
            }
        }

        while (!items.isEmpty()) {
            List<String> longestList = transportProductCountMap.values().stream()
                    .max(java.util.Comparator.comparingInt(List::size))
                    .orElse(null);

            if (longestList != null) {
                String transportMethod = getKeyByValueAndRemove(transportProductCountMap, longestList);
                result.put(transportMethod, longestList);

                for (List<String> list : transportProductCountMap.values()) {
                    list.removeAll(longestList);
                }
                items.removeAll(longestList);
            }
        }
        return result;
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
