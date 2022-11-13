# Risk Manager

This project consists in the development of a risk manager application based on [PDCA](https://www.mindtools.com/media/Diagrams/PDCA2017.jpg) concept. As from reported problems or vulnerabilities, the user will be able to analyze and choose which action plans are effective for solving or control them. 

![App-Screen-Sample](https://github.com/dancodingbr/riskmanager/blob/feature/analyze-results/docs/misc/app-screen-sample.png)

## Description

The main features that's application is going to provide are:

- Mount [risk assessment matrix](https://www.schgroup.com/wp-content/uploads/2019/07/risk1.png) based on crossing impact and probability levels.

- The risk can be considered a cause/problem/vulnerability that will be treated.

- Using PDCA cycle to perform for risk treatment.

- The goal is apply PDCA cycle over a risk until this will be considered "under control".

#### Structure

> **Problem**: ... 
>
> Risk: ... (Probabilitiy: ... vs Impact: ...) [Before Action Plan]
>
> **Plan**: 1-N action plans
>
> **Do**: 1-N tasks to-do
>
> **Check**: ... 
>
> Risk: ... (Probabilitiy: ... vs Impact: ...) [After Action Plan] 
>
> **Adjust**: ... 
>
> Solved the problem? / Are you satisfied?: Yes / No. If No, review action plans an activities to do
>

#### Example

> **Problem**: Bad grades on Math
>
> Risk: Critical (Probabilitiy: Imminent vs Impact: High) [Before Action Plan]
>
> **Plan**: Study 8 hours per week on next semester
>
> **Do**: Study efectively
>
> **Check**: Monitoring grades achieved during the semester
>
> Risk: Low (Probabilitiy: Rare vs Impact: High) [After Action Plan] 
>
> **Adjust**: Think about the grades achieved and plan the next semester
>
> Solved the problem? / Are you satisfied?: Yes / No
>

## Technologies

- Web: Spring Boot, Angular
- Database: Postgresql

## Installation

For more information, see the project's [wiki](https://github.com/dancodingbr/riskmanager/blob/main/docs/wiki.md).

## Contributing

You can contribute in several ways to this project, such as: reporting issues like bugs, feature requests; review source code, documentation; make pull requests, fixing bugs, etc.

## Documentation

The project documentation and artifacts can be found [here](https://github.com/dancodingbr/riskmanager/tree/main/docs).

## License

MIT

