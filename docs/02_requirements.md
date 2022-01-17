# Risk Manager
## _Requirements_

This is an requirements document of Risk Manager project.

## Features

What are the features required?

####  Feature: Register a problem
 
```
 Story: Create a problem register
 In order to manage the characteristics of a problem
 As the User
 I want to create a problem register
 Acceptance Criteria
  - the form must contains two input fields: probability and impact level. The user will choose the value of both.
  - As from probability and impact level values, the system must to calculate a risk level of a problem using a risk assessment matrix and show it to user.
```
 
```
 Story: Edit the problem register
 In order to manage the characteristics of a problem
 As the User
 I want to edit a problem register
 Acceptance Criteria
  - the form must contains two input fields: probability and impact level. The user will choose the value of both.
  - As from probability and impact level values, the system must to calculate a risk level of a problem using a risk assessment matrix and show it to user.
```
 
```
 Story: Remove the problem register
 In order to no manage a problem register anymore
 As the User
 I want to remove a problem register
 Acceptance Criteria
  - _description of condition or non-functional requirement, for example_
```
 
```
 Story: List all problem registers
 In order to see which problems needs to be managed
 As the User
 I want to list the problems stored
 Acceptance Criteria
  - _description of condition or non-functional requirement, for example_
```

####  Feature: Create potential action plans to solve a problem
 
```
 Story: Create a action plan
 In order to manage the characteristics of an action plan
 As the User
 I want to create an action plan register
 Acceptance Criteria
  - the system must to enable the select of the problem related to this action plan.
```
 
```
 Story: Edit the action plan
 In order to manage the characteristics of an action plan
 As the User
 I want to edit an action plan register
 Acceptance Criteria
  - the system must to enable the select of the problem related to this action plan.
```
 
```
 Story: Remove the action plan
 In order to no manage an action plan register anymore
 As the User
 I want to remove an action plan register
 Acceptance Criteria
  - the system must to remove only the action plan related to the respective problem.
```
 
```
 Story: List all action plans 
 In order to see which action plans needs to be managed
 As the User
 I want to list the action plans stored
 Acceptance Criteria
  - the system must to list only action plans to the respective problem.
```

####  Feature: Register tasks to do for each action plan
 
```
 Story: Create a task
 In order to manage the characteristics of an action plan's task
 As the User
 I want to create an action plan's task register
 Acceptance Criteria
  - the system must to enable the select of the action plan related to this task.
```
 
```
 Story: Edit the task
 In order to manage the characteristics of an action plan's task
 As the User
 I want to edit an action plan's task register
 Acceptance Criteria
  - the system must to enable the select of the action plan related to this task.
```
 
```
 Story: Remove the task
 In order to no manage an action plan's task register anymore
 As the User
 I want to remove an action plan's task register
 Acceptance Criteria
  - the system must to remove only the task related to the respective action plan.
```
 
```
 Story: List all tasks 
 In order to see which action plan's tasks needs to be managed
 As the User
 I want to list the action plan's tasks stored
 Acceptance Criteria
  - the system must to list only tasks to the respective action plan.
```

####  Feature: Analyze the results of a problem solution
 
```
 Story: Analyze the results
 In order to analyze which action plans had helped to reduce a risk level of a problem
 As the User
 I want to study the actions plans' results
 Acceptance Criteria
  - the system must to enable the select of the problem that will be checked.
  - the form must have two input fields for each action plan: probability and impact level. The user will choose the value of both based on own analyzes of a action plan's results.
  - As from probability and impact level values, the system must to calculate a risk level of a problem, solved by a respective action plan, using a risk assessment matrix and show it to user.
```

####  Feature: Choose the effective action plans for the problem
 
```
 Story: View the results
 In order to view which action plans were effetive in a problem resolution
 As the User
 I want to choose the best action plans
 Acceptance Criteria
- the system must to enable the select of the action plans that were more effective to solve or control the problem.
```

## References

Project Artifacts

| FILE |  TOOL | LINK |
| ------ | ------ | ------ |
| UX/UI | Pencil Project | [02_ux-ui-design.epgz](https://github.com/dancodingbr/riskmanager/tree/main/docs/02_ux-ui-design/02_ux-ui-design.epgz) |
| File 2 | Tool B | [file-name-2](http://example.com) |
| File N | Tool C | [file-name-N](http://example.com) |


