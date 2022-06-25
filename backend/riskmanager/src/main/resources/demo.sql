DROP TABLE IF EXISTS analyzed_result;

CREATE TABLE analyzed_result (
	id INT NOT NULL,
	problem VARCHAR(100) NOT NULL,
	action_plan VARCHAR(100) NOT NULL,
	probability_level VARCHAR(100) NOT NULL,
	impact_level VARCHAR(100) NOT NULL
);

INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level) VALUES (1, 'Bad grades on math', 'Study 8 hours per week on next semester', 'RARE', 'HIGH');
INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level) VALUES (2, 'Body weight raising', 'Decrease 20% of calories consumed per day', 'INFREQUENT', 'MODERATE');
