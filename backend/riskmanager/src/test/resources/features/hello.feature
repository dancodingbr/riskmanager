
Feature: Retrieve hello world message
	Scenario: Client app makes a GET call
		Given a <request>
		When client calls it 
		Then it receives a <message>

		Examples:
			| request		| message				|
			| '/hello'	| 'hello world'	|
