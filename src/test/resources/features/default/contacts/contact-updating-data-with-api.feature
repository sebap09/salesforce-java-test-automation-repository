Feature: Contact - creating new contact with the use of API - using fetched object id and updating Email and Mobile Phone fields values

  As: A adminUser
  I can: Create new contact with the use of API

  Scenario: Creating new contact with the use of API as adminUser - using fetched object id and updating Email and Mobile Phone fields values
    Given I generate authorization token
    When I fetch Id from "Account" by "Test Automation Account" Name
    And I create Contact with API
    And I update "Email,MobilePhone" in "contact" with "example@gmail.com,999" value