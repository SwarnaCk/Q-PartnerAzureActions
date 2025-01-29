# Feature: Project Registration with solar panel with ESS product with Battery
#   @SCRUM-TC-226
#   Scenario: Successful project registration with solar panel with ESS product with Battery
#     Given I am on the login page
#     When I enter username
#     And I enter password
#     And I click the login button
#     Then I should be logged in successfully
#     When I click on project Registration Tab
#     And I am not able to enter Site ID as I select 'No' option in product dropdown in system information page
#     And I fill model, powerclass, type ,product generation as 'Yes' option is selected in solar panel dropdown
#     And I fill registration no as'Yes' option is selected in ESS product dropdown
#     And I select 'Yes' in the battery status
#     And I fill the project information details
#     And I fill the purchasing information details
#     And I fill the project owner information details
#     Then I verify project Registration Details in Project Details Tab
#     And I click the submit button
#     Then I verify details of Project in project details section