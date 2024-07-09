package com.example.upravljanjestudijskimprogramima;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class jsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void saveToJSON(List<T> object, String fileName) {
        try {
            objectMapper.writeValue(new File(fileName), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> loadFromJSON(String fileName, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
