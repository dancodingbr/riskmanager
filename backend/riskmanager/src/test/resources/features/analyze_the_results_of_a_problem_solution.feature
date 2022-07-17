Feature: Analyze the results of a problem solution
    In order to analyze which action plans had helped to reduce a risk level of a problem
    As the User
    I want to study the actions plans results

    Scenario: Calculates a risk level of a problem
        Given a related <problem>
        And an <action_plan> done
        And a <probability_level> occurrence of this mitigated problem
        And an <impact_level> of this mitigated problem
        When the system calculates a risk level using a risk assessment matrix
        Then this results in the <risk_level> chance to occurs the problem again

        Examples:
            | problem                | action_plan                                  | probability_level     | impact_level  | risk_level  |
            | 'BAD GRADES ON MATH'   | 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'    | 'RARE'                | 'HIGH'        | 'LOW'       |
            | 'BODY WEIGHT RAISING'  | 'DECREASE 20% OF CALORIES CONSUMED PER DAY'  | 'INFREQUENT'          | 'MODERATE'    | 'LOW'       |
