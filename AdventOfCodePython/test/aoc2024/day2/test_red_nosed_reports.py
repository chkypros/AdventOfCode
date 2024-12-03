import test.aoc_template.solution_test as solution_test

from src.aoc2024.day2.red_nosed_reports import RedNosedReports

class HistorianHysteriaTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "2"
    EXPECTED_ANSWER_PART_ONE = "407"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "4"
    EXPECTED_ANSWER_PART_TWO = "0"
    solution = RedNosedReports()
