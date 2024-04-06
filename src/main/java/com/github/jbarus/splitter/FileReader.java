package com.github.jbarus.splitter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {
    public static Map<String,List<String>> readFromJSON(String absolutePath){
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
