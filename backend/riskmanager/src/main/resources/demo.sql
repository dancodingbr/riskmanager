DROP TABLE IF EXISTS analyzed_result;
DROP SEQUENCE IF EXISTS analyzed_result_id_seq;

CREATE SEQUENCE analyzed_result_id_seq INCREMENT BY 1 MINVALUE 1 START WITH 1;

CREATE TABLE analyzed_result (
	id BIGINT NOT NULL DEFAULT nextval('analyzed_result_id_seq'),
	problem VARCHAR(100) NOT NULL,
	action_plan VARCHAR(100) NOT NULL,
	probability_level VARCHAR(100) NOT NULL,
	impact_level VARCHAR(100) NOT NULL,
	risk_level VARCHAR(100) NOT NULL
);

INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level, risk_level) VALUES (nextval('analyzed_result_id_seq'), 'BAD GRADES ON MATH', 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER', 'RARE', 'HIGH', 'LOW');
INSERT INTO analyzed_result (id, problem, action_plan, probability_level, impact_level, risk_level) VALUES (nextval('analyzed_result_id_seq'), 'BODY WEIGHT RAISING', 'DECREASE 20% OF CALORIES CONSUMED PER DAY', 'INFREQUENT', 'MODERATE', 'LOW');
