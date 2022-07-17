
Feature: Retrieve hello world message
	Scenario: Client app click on hello link
		Given a home page
		When client clicks on <link>
		Then shows a <message>

		Examples:
			| link		| message		|
			| '/hello'	| 'hello world'	|
