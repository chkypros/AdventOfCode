import test.aoc_template.solution_test as solution_test

from src.aoc2023.day6.wait_for_it import WaitForIt

class WaitForItTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "288"
    EXPECTED_ANSWER_PART_ONE = "1159152"
    solution = WaitForIt()
