Feature: List all problem registers
		In order to see which problems needs to be managed
		As the User
		I want to list the problems stored

		Scenario: List all problems
			Given a problems saved by the user
				| id		| description					|
				|	1			|	BAD GRADES ON MATH 	|
				|	2			|	BODY WEIGHT RAISING	|
			When the system gets all problems
			Then shows these problems registered

				