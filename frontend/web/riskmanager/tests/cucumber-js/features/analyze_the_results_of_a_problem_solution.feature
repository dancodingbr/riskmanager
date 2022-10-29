Feature: Analyze the results of a problem solution
    In order to analyze which action plans had helped to reduce a risk level of a problem
    As the User
    I want to study the actions plans' results

    Scenario: Calculates a risk level of a problem
        Given a related <problem>
        And an <action_plan> done
        And a <probability_level> occurrence of this mitigated problem
        And an <impact_level> of this mitigated problem
        When the system calculates a risk level using a risk assessment matrix
        Then this result in the <risk_level> chance to occurs the problem again

        Examples:
            | problem                    | action_plan                                      | probability_level       | impact_level    | risk_level    |
            | '1','BAD GRADES ON MATH'   | '1','STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'    | 'RARE'                  | 'HIGH'          | 'LOW'         |
            | '2','BODY WEIGHT RAISING'  | '2','DECREASE 20% OF CALORIES CONSUMED PER DAY'  | 'INFREQUENT'            | 'MODERATE'      | 'LOW'         |

    Scenario: Save an analyzed result
        Given a related <problem>
        And an <action_plan> done
        And a <probability_level> occurrence of this mitigated problem
        And an <impact_level> of this mitigated problem
        And a <risk_level> risk calculated
        When the user saves this analyzed result
        Then shows that operation was <operation_status>

        Examples:
            | problem                    | action_plan                                      | probability_level       | impact_level    | risk_level    | operation_status    |
            | '1','BAD GRADES ON MATH'   | '1','STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'    | 'RARE'                  | 'HIGH'          | 'LOW'         | 'SUCCESS'           |
            | '2','BODY WEIGHT RAISING'  | '2','DECREASE 20% OF CALORIES CONSUMED PER DAY'  | 'INFREQUENT'            | 'MODERATE'      | 'LOW'         | 'SUCCESS'           |

    Scenario: List the analyzed results
        Given a related <problem>
        And the <analyzed_results> stored
            | problem                    | action_plan                                      | probability_level       | impact_level    | risk_level    |
            | '1','BAD GRADES ON MATH'   | '1','STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'    | 'RARE'                  | 'HIGH'          | 'LOW'         |
            | '2','BODY WEIGHT RAISING'  | '2','DECREASE 20% OF CALORIES CONSUMED PER DAY'  | 'INFREQUENT'            | 'MODERATE'      | 'LOW'         |
        When the system gets the analyzed results associated
        Then shows <action_plan>
        And <risk_level> of each analyzed result

        Examples:
            | problem                    | action_plan                                      | risk_level    |
            | '1','BAD GRADES ON MATH'   | '1','STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'    | 'LOW'         |
            | '2','BODY WEIGHT RAISING'  | '2','DECREASE 20% OF CALORIES CONSUMED PER DAY'  | 'LOW'         |
