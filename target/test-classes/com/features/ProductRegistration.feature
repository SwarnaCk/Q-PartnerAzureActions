Feature: Product Registration

  Scenario: Successful product registration
    Given I am on the login page
    When I enter username "brateleedemock@yopmail.com.dev2"
    And I enter password "Cloudkaptan@1"
    And I click the login button
    Then I should be logged in successfully