@test
Feature: Check the filter functionality

  Background:
    Given the user navigate to the page
    And log in the page via entering the username and password
    Then It landed on the 'Inventory' page

  Scenario: Test the price filter with alphabetical order
    When The user click the 'Name (A to Z)' filter option
    Then The name has been sorted in 'an' alphabetical order

  Scenario: Test the price filter with reverse alphabetical order
    When The user click the 'Name (Z to A)' filter option
    Then The name has been sorted in 'reverse' alphabetical order

  Scenario: Test the price filter with low to high price range
    When The user click the 'Price (low to high)' filter option
    Then The name has been sorted in 'reverse' alphabetical order






