package com.github.jbarus.splitter;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void testReadFromJSON() {
        String absolutePath = "src/test/resources/config.json";

        HashMap<String, List<String>> result = FileReader.readFromJSON(absolutePath);

        assertNotNull(result);
        assertEquals(100, result.size());
        assertTrue(result.containsKey("Tea - Apple Green Tea"));
        assertTrue(result.containsKey("Garlic - Peeled"));
        assertEquals(8, result.get("Tea - Apple Green Tea").size());
        assertEquals(1, result.get("Garlic - Peeled").size());
    }

    @Test
    void testReadFromJSONWithInvalidPath() {
        String absolutePath = "invalid_path.json";

        assertThrows(RuntimeException.class, () -> FileReader.readFromJSON(absolutePath));
    }
}