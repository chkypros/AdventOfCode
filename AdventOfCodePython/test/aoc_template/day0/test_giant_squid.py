import test.aoc_template.solution_test as solution_test

from src.aoc_template.day0.giant_squid import GiantSquid 

class GiantSquidTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE = "11536"
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "4512"
    EXPECTED_ANSWER_PART_TWO = "1284"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "1924"
    solution = GiantSquid()
