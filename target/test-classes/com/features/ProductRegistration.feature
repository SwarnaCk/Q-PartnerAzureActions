Feature: Product Registration

  Scenario: Successful product registration
    Given I am on the login page
    When I enter username
    And I enter password
    And I click the login button
    Then I should be logged in successfully
    When I click on project Registration Tab
    And I fill the system information details
    And I fill the project information details
    And I fill the purchasing information details
    And I fill the project owner information details
    