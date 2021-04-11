@test
Feature: Datable

  Scenario Outline: log in step
    Given the user navigate to the page
    And log in the page via entering the following details
      | username   | password   |
      | <username> | <password> |

    Examples:
    | username  | password  |
    | 123 | 123 |
#    | 456 | 123 |
#