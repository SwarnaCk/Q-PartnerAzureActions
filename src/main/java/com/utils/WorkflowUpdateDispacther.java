package com.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WorkflowUpdateDispacther {

        private WorkflowUpdateDispacther() {
        }

        public static void updateWorkflowWithAllScenarios() throws IOException {
                // Load the scenarios from the JSON file
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> scenariosData = objectMapper.readValue(new File("scenarios.json"),
                                List.class);

                // Create the workflow YAML data structure
                Map<String, Object> workflowData = Map.of(
                                "name", "Run Cucumber Tests",
                                "on", Map.of(
                                                "workflow_dispatch", Map.of(
                                                                "inputs", Map.of(
                                                                                "scenario", Map.of(
                                                                                                "description",
                                                                                                "Select a scenario to run",
                                                                                                "required", true,
                                                                                                "type", "choice",
                                                                                                "options",
                                                                                                scenariosData.stream()
                                                                                                                .map(scenario -> scenario
                                                                                                                                .get("name"))
                                                                                                                .toArray(String[]::new))))),
                                "jobs", Map.of(
                                                "run-tests", Map.of(
                                                                "runs-on", "ubuntu-latest",
                                                                "steps", List.of(
                                                                                Map.of(
                                                                                                "name", "Checkout code",
                                                                                                "uses",
                                                                                                "actions/checkout@v3"),
                                                                                Map.of(
                                                                                                "name", "Set up JDK",
                                                                                                "uses",
                                                                                                "actions/setup-java@v3",
                                                                                                "with", Map.of(
                                                                                                                "java-version",
                                                                                                                "17",
                                                                                                                "distribution",
                                                                                                                "temurin")), // Added
                                                                                                                             // distribution
                                                                                                                             // here
                                                                                Map.of(
                                                                                                "name",
                                                                                                "Run Selected Scenario",
                                                                                                "run",
                                                                                                "mvn clean test -Dcucumber.filter.name=\"${{ github.event.inputs.scenario }}\"")))));

                // Configure DumperOptions for clean YAML
                DumperOptions options = new DumperOptions();
                options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Use block style
                options.setPrettyFlow(true);
                options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN); // Avoid special string formatting
                options.setIndicatorIndent(2); // Proper indent for readability
                options.setIndent(4); // Use 4-space indentation
                options.setWidth(100); // Prevent long-line wrapping

                // Write the workflow YAML to a file
                Yaml yaml = new Yaml(options);
                try (FileWriter writer = new FileWriter(".github/workflows/run-tests.yml")) {
                        yaml.dump(workflowData, writer);
                }

                System.out.println("GitHub Actions workflow updated with dynamic scenarios.");
        }
}
