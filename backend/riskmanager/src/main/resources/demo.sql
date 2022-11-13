INSERT INTO problem (id, description) VALUES(nextval('problem_id_seq'),'BAD GRADES ON MATH');

INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'STUDY AT ANOTHER SCHOOL');
INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'ENROLL IN TWO MATH COURSES');

INSERT INTO problem (id, description) VALUES(nextval('problem_id_seq'),'DIRTY HOUSE');

INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'DO CLEANING ONCE A WEEK');
INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'HIRE CLEANING SERVICE');
INSERT INTO action_plan (id, description) VALUES(nextval('action_plan_id_seq'),'CLEAN THE HOUSE ONLY BEFORE WELCOME VISITORS');


INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'BAD GRADES ON MATH'
	,SELECT id FROM action_plan WHERE description = 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'
	,'RARE'
	,'HIGH'
	,'LOW'
);
INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'BAD GRADES ON MATH'
	,SELECT id FROM action_plan WHERE description = 'STUDY AT ANOTHER SCHOOL'
	,'FREQUENT'
	,'HIGH'
	,'HIGH'
);
INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'BAD GRADES ON MATH'
	,SELECT id FROM action_plan WHERE description = 'ENROLL IN TWO MATH COURSES'
	,'OCCASIONAL'
	,'MODERATE'
	,'MODERATE'
);

INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'DIRTY HOUSE'
	,SELECT id FROM action_plan WHERE description = 'DO CLEANING ONCE A WEEK'
	,'RARE'
	,'LOW'
	,'VERY_LOW'
);
INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'DIRTY HOUSE'
	,SELECT id FROM action_plan WHERE description = 'HIRE CLEANING SERVICE'
	,'INFREQUENT'
	,'LOW'
	,'VERY_LOW'
);
INSERT INTO analyzed_result (
	id, 
	problem_id, 
	action_plan_id, 
	probability_level, 
	impact_level, 
	risk_level
) VALUES(
	nextval('analyzed_result_id_seq')
	,SELECT id FROM problem WHERE description = 'DIRTY HOUSE'
	,SELECT id FROM action_plan WHERE description = 'CLEAN THE HOUSE ONLY BEFORE WELCOME VISITORS'
	,'IMMINENT'
	,'HIGH'
	,'CRITICAL'
);
