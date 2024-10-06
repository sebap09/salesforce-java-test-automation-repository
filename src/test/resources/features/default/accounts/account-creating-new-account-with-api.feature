Feature: Account - creating new account with the use of API

  As: A adminUser
  I can: Create new account with the use of API

  Scenario: Creating new account with the use of API as adminUser
    Given I generate authorization token
    And I create Account with API