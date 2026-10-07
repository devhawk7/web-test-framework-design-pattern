Feature: Shopping cart functionality

  Scenario: Added product appears in the cart
    Given I am logged in as a standard user
    When I add the test product to the cart
    And I open the shopping cart
    Then the cart page should be opened
    And the cart should contain the test product

  Scenario: Cart badge and product price are correct
    Given I am logged in as a standard user
    Then the test product price should be correct
    When I add the test product to the cart
    Then the cart badge count should be 1