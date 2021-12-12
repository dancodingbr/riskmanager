# Risk Manager

This project consists in the development of a risk manager application based on PDCA concept. As from reported problems or vulnerabilities, the user will be able to analyze and choose which action plans are effective for solving or control them. 

## Description

The main features that's application is going to provide are:

- Mount risk assessment matrix based on crossing impact and probability levels.
- The risk can be considered a cause/problem/vulnerability that will be treated.
- Using PDCA cycle to perform for risk treatment.
- The goal is apply PDCA cycle over a risk until this will be considered "under control".

#### Structure

> __Problem__: ...
> Risk: ... (Probabilitiy: ... vs Impact: ...) [Before Action Plan]
> __Plan__: 1-N action plans
> __Do__: 1-N tasks to-do
> __Check__: ...
> Risk: ... (Probabilitiy: ... vs Impact: ...) [After Action Plan] 
> __Adjust__: ...
> Solved the problem? / Are you satisfied?: Yes / No. If No, review action plans an activities to do

#### Example

> __Problem__: Bad grades on Math
> Risk: Critical (Probabilitiy: Imminent vs Impact: High) [Before Action Plan]
> __Plan__: Study 8 hours per week on next semester
> __Do__: Study efectively
> __Check__: Monitoring grades achieved during the semester
> Risk: Low (Probabilitiy: Rare vs Impact: High) [After Action Plan] 
> __Adjust__: Think about the grades achieved and plan the next semester
> Solved the problem? / Are you satisfied?: Yes / No

## Technologies

- Web: Spring Boot, Angular
- Database: Postgresql

## Installation

_installation_

## Contributing

You can contribute in several ways to this project, such as: reporting issues like bugs, feature requests; review source code, documentation; make pull requests, fixing bugs, etc.

## Documentation

The project documentation and artifacts can be found [here](https://github.com/dancodingbr/riskmanager/tree/main/docs).

## License

_license_

