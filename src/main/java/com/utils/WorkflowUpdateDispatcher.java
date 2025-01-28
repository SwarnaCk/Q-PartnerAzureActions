package com.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WorkflowUpdateDispatcher {

        private WorkflowUpdateDispatcher() {
        }

        public static void updateWorkflowWithAllScenarios() throws IOException {
                // Read scenarios from JSON file
                ObjectMapper objectMapper = new ObjectMapper();
                List<Map<String, Object>> scenarios = objectMapper.readValue(new File("scenarios.json"),
                                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

                // Create YAML structure
                Map<String, Object> workflow = new LinkedHashMap<>();
                workflow.put("name", "Run Cucumber Tests");

                Map<String, Object> on = new LinkedHashMap<>();
                Map<String, Object> workflowDispatch = new LinkedHashMap<>();
                Map<String, Object> inputs = new LinkedHashMap<>();
                Map<String, Object> scenario = new LinkedHashMap<>();

                scenario.put("type", "choice");
                scenario.put("description", "Select a scenario to run");
                List<String> options = new ArrayList<>();
                for (Map<String, Object> scenarioMap : scenarios) {
                        options.add((String) scenarioMap.get("name"));
                }
                options.add("All");
                scenario.put("options", options);
                scenario.put("required", true);

                inputs.put("scenario", scenario);
                workflowDispatch.put("inputs", inputs);
                on.put("workflow_dispatch", workflowDispatch);
                workflow.put("on", on);

                Map<String, Object> jobs = new LinkedHashMap<>();
                Map<String, Object> runTests = new LinkedHashMap<>();
                runTests.put("runs-on", "ubuntu-latest");

                List<Map<String, Object>> steps = new ArrayList<>();

                // Checkout code step
                steps.add(Map.of("name", "Checkout code", "uses", "actions/checkout@v3"));

                // Set up JDK step
                Map<String, Object> setupJdk = new LinkedHashMap<>();
                setupJdk.put("name", "Set up JDK");
                setupJdk.put("uses", "actions/setup-java@v3");
                setupJdk.put("with", Map.of("java-version", "17", "distribution", "temurin"));
                steps.add(setupJdk);

                // Run Specific Test Case step
                Map<String, Object> runSpecificTestCase = new LinkedHashMap<>();
                runSpecificTestCase.put("name", "Run Specific Test Case");
                runSpecificTestCase.put("env", Map.of(
                                "GIT_TOKEN", "${{ secrets.GIT_TOKEN }}",
                                "AIO_TOKEN", "${{ secrets.AIO_TOKEN }}"));
                runSpecificTestCase.put("if", "${{ github.event.inputs.scenario != 'All' }}");
                runSpecificTestCase.put("run",
                                "echo \"Running scenario by name: ${{ github.event.inputs.scenario }}\"\n" +
                                                "export AIO_TOKEN=\"${{ secrets.AIO_TOKEN }}\" && \\\n" +
                                                "export GIT_TOKEN=\"${{ secrets.GIT_TOKEN }}\" && \\\n" +
                                                "mvn clean test --no-transfer-progress -q -Dcucumber.filter.name=\"${{ github.event.inputs.scenario }}\"");
                steps.add(runSpecificTestCase);

                // Run all tests step
                Map<String, Object> runAllTests = new LinkedHashMap<>();
                runAllTests.put("name", "Run all tests if no specific scenario is selected");
                runAllTests.put("if", "${{ github.event.inputs.scenario == 'All' }}");
                runAllTests.put("run", "export AIO_TOKEN=\"${{ secrets.AIO_TOKEN }}\" && \\\n" +
                                "export GIT_TOKEN=\"${{ secrets.GIT_TOKEN }}\" && \\\n" +
                                "mvn clean test --no-transfer-progress -q");
                steps.add(runAllTests);

                runTests.put("steps", steps);
                jobs.put("run-tests", runTests);
                workflow.put("jobs", jobs);

                // Write YAML to file
                DumperOptions dumpoptions = new DumperOptions();
                dumpoptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
                dumpoptions.setPrettyFlow(true);
                dumpoptions.setIndent(2);
                Yaml yaml = new Yaml(dumpoptions);
                try (FileWriter writer = new FileWriter(".github/workflows/run-tests.yml")) {
                        yaml.dump(workflow, writer);
                }

                System.out.println(
                                "GitHub Actions workflow updated with dynamic scenarios and multi-line run commands.");
        }
}