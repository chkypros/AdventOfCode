import test.aoc_template.solution_test as solution_test

from src.aoc2024.day3.mull_it_over import MullItOver

class MullItOverTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "161"
    solution = MullItOver()
