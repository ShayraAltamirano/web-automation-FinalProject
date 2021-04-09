@Tasks
Feature: Tasks
  CRUD

  @createProject @createSection @deleteProject
  Scenario: Create a task
    When I click on Add task plus sign
    And I type "my task" in Task Name field
    And I click on Add task button
    Then the task should be created

  @createProject @createSection @createTask @deleteProject
  Scenario: Delete a task
    When I select Delete option of Task
    And I click on Delete Task button
    Then the Task should be deleted

  @createProject @createSection @createTask @deleteProject
  Scenario: Update a task
    When I select Edit option of task
    And I change the schedule of task to "Tomorrow"
    Then The Task should be scheduled for Tomorrow

  @createProject @createSection @createTask @deleteProject
  Scenario: Duplicate a task
    When I select Duplicate Task Menu option
    Then The Task is Duplicated