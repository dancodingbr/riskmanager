ALTER TABLE analyzed_result DROP CONSTRAINT IF EXISTS problem_fk;
ALTER TABLE analyzed_result DROP CONSTRAINT IF EXISTS action_plan_fk;
ALTER TABLE analyzed_result DROP CONSTRAINT IF EXISTS analyzed_result_pk;
DROP TABLE IF EXISTS analyzed_result;
DROP SEQUENCE IF EXISTS analyzed_result_id_seq;

ALTER TABLE problem DROP CONSTRAINT IF EXISTS problem_pk;
DROP TABLE IF EXISTS problem;
DROP SEQUENCE IF EXISTS problem_id_seq;

ALTER TABLE action_plan DROP CONSTRAINT IF EXISTS action_plan_pk;
DROP TABLE IF EXISTS action_plan;
DROP SEQUENCE IF EXISTS action_plan_id_seq;

-------------------

CREATE SEQUENCE analyzed_result_id_seq INCREMENT BY 1 MINVALUE 1 START WITH 1;

CREATE TABLE analyzed_result (
	id BIGINT NOT NULL DEFAULT nextval('analyzed_result_id_seq'),
	problem VARCHAR(100) NOT NULL,
	action_plan VARCHAR(100) NOT NULL,
	probability_level VARCHAR(100) NOT NULL,
	impact_level VARCHAR(100) NOT NULL,
	risk_level VARCHAR(100) NOT NULL
);

-------------------

CREATE SEQUENCE problem_id_seq INCREMENT BY 1 MINVALUE 1 START WITH 1;

CREATE TABLE problem (
	id BIGINT NOT NULL DEFAULT nextval('problem_id_seq'),
	description VARCHAR(100) NOT NULL
);

-------------------

ALTER TABLE analyzed_result ADD COLUMN problem_id BIGINT;

UPDATE analyzed_result SET analyzed_result.problem_id = (SELECT id FROM problem WHERE description = analyzed_result.problem);

ALTER TABLE analyzed_result ALTER COLUMN problem_id BIGINT NOT NULL;

ALTER TABLE problem ADD CONSTRAINT problem_pk PRIMARY KEY (id);

ALTER TABLE analyzed_result DROP COLUMN problem;

ALTER TABLE analyzed_result ADD CONSTRAINT problem_fk FOREIGN KEY (problem_id) REFERENCES problem (id);

ALTER TABLE analyzed_result ADD CONSTRAINT analyzed_result_pk PRIMARY KEY (id);

-------------------

CREATE SEQUENCE action_plan_id_seq INCREMENT BY 1 MINVALUE 1 START WITH 1;

CREATE TABLE action_plan (
	id BIGINT NOT NULL DEFAULT nextval('action_plan_id_seq'),
	description VARCHAR(100) NOT NULL
);

-------------------

ALTER TABLE analyzed_result ADD COLUMN action_plan_id BIGINT;

UPDATE analyzed_result SET analyzed_result.action_plan_id = (SELECT id FROM action_plan WHERE description = analyzed_result.action_plan);

ALTER TABLE analyzed_result ALTER COLUMN action_plan_id BIGINT NOT NULL;

ALTER TABLE action_plan ADD CONSTRAINT action_plan_pk PRIMARY KEY (id);

ALTER TABLE analyzed_result DROP COLUMN action_plan;

ALTER TABLE analyzed_result ADD CONSTRAINT action_plan_fk FOREIGN KEY (action_plan_id) REFERENCES action_plan (id);
