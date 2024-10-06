Feature: Product - creating new product with the use of API

  As: A adminUser
  I can: Create new product with the use of API

  Scenario: Creating new product with the use of API as adminUser
    Given I generate authorization token
    And I create Product with API