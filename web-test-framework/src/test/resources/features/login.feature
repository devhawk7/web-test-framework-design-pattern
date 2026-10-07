Feature: Login functionality

  Background:
    Given I am on the SauceDemo login page

  Scenario: Successful login with valid credentials
    When I login with valid credentials
    Then I should be redirected to the inventory page

  Scenario Outline: Login with different users
    When I login with username "<username>" and password "<password>"
    Then I should be redirected to the inventory page

    Examples:
      | username                 | password     |
      | standard_user            | secret_sauce |
      | problem_user             | secret_sauce |
      | performance_glitch_user  | secret_sauce |