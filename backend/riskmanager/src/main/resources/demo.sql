DROP TABLE IF EXISTS analyzed_result;

CREATE TABLE analyzed_result (
	id INT NOT NULL,
	problem VARCHAR(100) NOT NULL,
	action_plan VARCHAR(100) NOT NULL,
	probability_level VARCHAR(100) NOT NULL,
	impact_level VARCHAR(100) NOT NULL,
	risk_level VARCHAR(100) NOT NULL
);

INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level, risk_level) VALUES (1, 'BAD GRADES ON MATH', 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER', 'RARE', 'HIGH', 'LOW');
INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level, risk_level) VALUES (2, 'BODY WEIGHT RAISING', 'DECREASE 20% OF CALORIES CONSUMED PER DAY', 'INFREQUENT', 'MODERATE', 'LOW');
