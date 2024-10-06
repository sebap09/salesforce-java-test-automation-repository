Feature: Asset - creating new asset with the use of API - using fetched objects id and updating Serial Number field value

  As: A adminUser
  I can: Create new asset with the use of API

  Scenario: Creating new asset with the use of API as adminUser - using fetched objects id and updating Serial Number field value
    Given I generate authorization token
    When I fetch Id from "Account" by "Test Automation Account" Name
    And I fetch Id from "Product2" by "Test Automation Product" Name
    And I fetch Id from "Contact" by "Test Automation Contact" Name
    And I create Asset with API
    And I update "SerialNumber" in "asset" with "0000" value