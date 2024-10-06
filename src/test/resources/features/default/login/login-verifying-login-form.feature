Feature: Login - verifying location of elements in login form

  As: A adminUser
  I can: Login to Salesforce platform with the use of correctly aligned form

  @initSessionBefore
  Scenario: Verifying location of elements in login form
    Given I am on the login page
    Then I verify login icon is displayed
    And I verify that "username" input field is below login icon
    And I verify that "password" input field is below login icon
    And I verify that password input field is between username input field and login button
    And I verify that login form is to the left of promo

  @endSessionAfter
  Scenario: Logging to Salesforce platform as adminUser
    When I fill "adminUser" credential to "username" input field
    And I fill "adminUser" credential to "password" input field
    And I click login button
    Then I verify user is correctly logged in