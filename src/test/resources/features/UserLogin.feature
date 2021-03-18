@test
Feature: login

  Scenario Outline: testing

   Given the user navigate to the page
    And log in the page via entering the username and password
    Then It landed on the 'Inventory' page
    And the user adds '<Product>' to the shopping cart
    Then the user go to basket and check the '<Basket>' to the shopping cart

   Examples:
    | Product                 | Basket                  |
#    | Sauce Labs Bolt T-Shirt | Sauce Labs Bolt T-Shirt |
    | Sauce Labs Backpack     | Sauce Labs Backpack 1    |


