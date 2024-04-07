package com.ocado.basket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileReader {

    // Reads data from a JSON file located at the specified absolute path and returns a HashMap
    // with keys representing products and values representing lists of transport methods.
    public static HashMap<String,List<String>> readFromJSON(String absolutePath){
        HashMap<String, List<String>> productList = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(new File(absolutePath));

            rootNode.fields().forEachRemaining(entry ->{
                String key = entry.getKey();
                JsonNode valueNode = entry.getValue();
                List<String> value = new ArrayList<>();
                valueNode.forEach(node->value.add(node.asText()));
                productList.put(key,value);
            });

        } catch (IOException e) {
            throw new RuntimeException("The specified file does not exist");
        }
        return productList;
    }
}
