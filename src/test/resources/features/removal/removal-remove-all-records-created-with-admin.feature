Feature: Removal - remove all records created with admin user

  As: A adminUser
  I can: Remove created records with the use of API

  Scenario: Generating token and fetching adminUser id
    Given I generate authorization token
    When I fetch Id from "User" by "Test Automation Admin User" Name

  Scenario Outline: Fetching and removing records with the use of API as adminUser
    When I fetch Id from "<object>" created by currently fetched user
    And I remove all "<object>" records created by currently fetched user

    Examples:
      | object  |
      |  Asset  |
      | Product |
      | Contact |
      | Account |
