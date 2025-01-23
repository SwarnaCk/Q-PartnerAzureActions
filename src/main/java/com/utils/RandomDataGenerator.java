package com.utils;
    
import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
    import java.io.IOException;

public class RandomDataGenerator {
    private RandomDataGenerator() {
        // Initialization code (if any)
    }
    
    
        private static final Faker faker = new Faker();
        
        @SuppressWarnings("unchecked")
        public static void generateRandomProjectData(String fileName) {
            JSONObject projectData = readExistingJson(fileName);
            String name=faker.app().name();
            projectData.put("projectName",name);

            try (FileWriter file = new FileWriter("src/test/resources/testdata/" + fileName)) {
                file.write(projectData.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private static JSONObject readExistingJson(String fileName) {
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader("src/test/resources/testdata/" + fileName)) {
                Object obj = jsonParser.parse(reader);
                return (JSONObject) obj;
            } catch (IOException | ParseException e) {
                // If file doesn't exist or is empty, return a new JSONObject
                return new JSONObject();
            }
        }
}

