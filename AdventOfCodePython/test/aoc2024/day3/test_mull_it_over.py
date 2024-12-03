import test.aoc_template.solution_test as solution_test
from aoc_common import test_utils

from src.aoc2024.day3.mull_it_over import MullItOver

class MullItOverTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "161"
    EXPECTED_ANSWER_PART_ONE = "183788984"
    EXPECTED_ANSWER_PART_TWO_SAMPLE_TWO = "48"
    EXPECTED_ANSWER_PART_TWO = "62098619"
    solution = MullItOver()

    def test_part_two_sample(self):
        input_lines = test_utils.get_input_lines(self, "-sample2")
        result = self.solution.solve_part_two(input_lines)
        self.assertEqual(self.EXPECTED_ANSWER_PART_TWO_SAMPLE_TWO, str(result))
