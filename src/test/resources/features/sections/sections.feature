@Sections
Feature: Sections
  CRUD

  @createProject @deleteProject
  Scenario: Create a section
    When I type "my section" in Name this section field
    And I click on Add section button
    Then the section should be created

  @createProject @createSection @deleteProject
  Scenario: Delete a section
    When I select Delete option of Section
    And I click on Delete Section button
    Then the Section should be deleted

  @createProject @createSection @deleteProject
  Scenario: Update a section
    When I select edit option of section
    And I update Section's name to "my section updated"
    Then The Section should have a new name

  @createProject @createSection @deleteProject
  Scenario: Duplicate a section
    When I select Duplicate Section Menu option
    Then The Section is Duplicated