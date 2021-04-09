@Login
Feature: Login Todoist
  Test successfully and unsuccessfully  login and logout to Todoist //descripción

  Scenario: Login Successfully to Todoist site
    Given I navigate to Todoist Login page
    When I login to Todoist as "shayra.al@gmail.com " with password "test12345"
    Then I should login to Todoist successfully
