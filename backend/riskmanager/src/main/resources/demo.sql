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

-------------------

DROP TABLE IF EXISTS problem;
DROP SEQUENCE IF EXISTS problem_id_seq;

CREATE SEQUENCE problem_id_seq INCREMENT BY 1 MINVALUE 1 START WITH 1;

CREATE TABLE problem (
	id BIGINT NOT NULL DEFAULT nextval('problem_id_seq'),
	description VARCHAR(100) NOT NULL
);

INSERT INTO problem (id, description) VALUES (nextval('problem_id_seq'), 'BAD GRADES ON MATH');
INSERT INTO problem (id, description) VALUES (nextval('problem_id_seq'), 'BODY WEIGHT RAISING');

-------------------

ALTER TABLE analyzed_result ADD COLUMN problem_id BIGINT;

UPDATE analyzed_result SET analyzed_result.problem_id = (SELECT id FROM problem WHERE description = analyzed_result.problem);

ALTER TABLE analyzed_result ALTER COLUMN problem_id BIGINT NOT NULL;

--ALTER TABLE problem ADD CONSTRAINT problem_pk PRIMARY KEY (id);

ALTER TABLE analyzed_result DROP COLUMN problem;

--ALTER TABLE analyzed_result ADD CONSTRAINT problem_fk FOREIGN KEY (problem_id) REFERENCES problem (id);

--ALTER TABLE analyzed_result ADD CONSTRAINT analyzed_result_pk PRIMARY KEY (id);
