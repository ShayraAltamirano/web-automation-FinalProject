@Projects
Feature: Projects
  CRUD

  Background:
    Given I navigate to Todoist Login page
    When I login to Todoist as "shayra.al@gmail.com " with password "test12345"

  @deleteProject
  Scenario: Create a project
    When I select create project option
    And I type "my project" in Project Name field
    And I click on Add button
    Then the Project should be created

  @createProject
  Scenario: Delete a project
    When I select Delete option of project
    And I click on Delete button
    Then the Project should be deleted

  @createProject @deleteProject
  Scenario: Update a project
    When I select edit option of project
    And I change color of Project
    Then The project's color is changed

  @createProject
  Scenario: Archive a project
    When I select Archive Project Menu option
    Then The Project is archived