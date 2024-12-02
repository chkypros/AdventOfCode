import test.aoc_template.solution_test as solution_test

from src.aoc2024.day1.historian_hysteria import HistorianHysteria

class HistorianHysteriaTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "11"
    EXPECTED_ANSWER_PART_ONE = "3569916"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "31"
    EXPECTED_ANSWER_PART_TWO = "0"
    solution = HistorianHysteria()
