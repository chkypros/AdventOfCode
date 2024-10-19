import test.aoc_template.solution as solution
from src.aoc_template.day0.giant_squid import GiantSquid 

class GiantSquidTest(solution.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE = 4512
    EXPECTED_ANSWER_PART_TWO = 1924
    solution = GiantSquid()
