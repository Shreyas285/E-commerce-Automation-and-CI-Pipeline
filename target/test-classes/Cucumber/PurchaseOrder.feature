@Main
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

  Background: 
    Given I have landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submiting order
    Given Log in with valid name <username> and password <password>
    When I add product <productName> to the cart
    When Checkout <productName> and submit the order
    Then "THANK YOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username               | password | productName     | status  |
      | demoshreyas39@mail.com | Demo123! | ADIDAS ORIGINAL | success |