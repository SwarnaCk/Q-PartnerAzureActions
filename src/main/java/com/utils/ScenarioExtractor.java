package com.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ScenarioExtractor {

    private ScenarioExtractor() {
    }

    public static void getAllScenarios() throws IOException {
        // Directory where the .feature files are stored
        String featureDir = "src/test/resources/features"; // Adjust based on your project structure

        // Get all feature files in the directory
        File[] featureFiles = new File(featureDir).listFiles((dir, name) -> name.endsWith(".feature"));

        // List to hold all the scenarios with tags
        List<Map<String, Object>> scenariosList = new ArrayList<>();

        // Process each feature file
        for (File featureFile : featureFiles) {
            List<String> lines = Files.readAllLines(Paths.get(featureFile.getPath()));

            String scenarioName = null;
            List<String> tags = new ArrayList<>();

            // Traverse through the lines of the feature file
            for (String line : lines) {
                line = line.trim();

                // Match tags
                if (line.startsWith("@")) {
                    tags.add(line.substring(1).trim()); // Remove '@' and trim whitespace
                }

                // Match scenario name
                if (line.startsWith("Scenario:") || line.startsWith("Scenario Outline:")) {
                    scenarioName = line.substring(line.indexOf(":") + 1).trim();
                }

                // If we found a scenario, save the details
                if (scenarioName != null && !tags.isEmpty()) {
                    Map<String, Object> scenarioMap = new HashMap<>();
                    scenarioMap.put("name", scenarioName);
                    scenarioMap.put("tags", tags);
                    scenariosList.add(scenarioMap);

                    // Reset for the next scenario
                    scenarioName = null;
                    tags = new ArrayList<>();
                }
            }
        }

        // Convert list to JSON and save to file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("scenarios.json"), scenariosList);

        System.out.println("Scenarios JSON created successfully.");
    }
}
