Feature: Google Search Functionality

  Scenario: Verify Google search page title
    Given I am on the Google search page
    Then the page title should be "Google"