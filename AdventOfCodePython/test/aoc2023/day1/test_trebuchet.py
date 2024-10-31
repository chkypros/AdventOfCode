import test.aoc_template.solution_test as solution_test
import test.aoc_common.test_utils as test_utils

from src.aoc2023.day1.trebuchet import Trebuchet

class TrebuchetTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE_ONE = "142"
    EXPECTED_ANSWER_PART_ONE = "55108"
    EXPECTED_ANSWER_PART_TWO_SAMPLE_TWO = "281"
    EXPECTED_ANSWER_PART_TWO = "56324"
    solution = Trebuchet()

    def test_part_one_sample(self):
        input_lines = test_utils.get_input_lines(self, "-sample1")
        self.assertEqual(self.EXPECTED_ANSWER_PART_ONE_SAMPLE_ONE, self.solution.solve_part_one(input_lines))

    def test_part_two_sample(self):
        input_lines = test_utils.get_input_lines(self, "-sample2")
        self.assertEqual(self.EXPECTED_ANSWER_PART_TWO_SAMPLE_TWO, self.solution.solve_part_two(input_lines))
