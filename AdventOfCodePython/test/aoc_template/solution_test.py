import unittest

import test.aoc_common.test_utils as test_utils
from src.aoc_template.solution import AbstractSolution


class AbstractSolutionTest(unittest.TestCase):
    solution: AbstractSolution

    def test_part_one(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_ONE'):
            input_lines = test_utils.get_input_lines(self)
            self.assertEqual(self.EXPECTED_ANSWER_PART_ONE, self.solution.solve_part_one(input_lines))
        else:
            self.skipTest("Not implemented yet")

    def test_part_one_sample(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_ONE_SAMPLE'):
            input_lines = test_utils.get_input_lines(self, '-sample')
            self.assertEqual(self.EXPECTED_ANSWER_PART_ONE_SAMPLE, self.solution.solve_part_one(input_lines))
        else:
            self.skipTest("Not implemented yet")

    def test_part_two(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_TWO'):
            input_lines = test_utils.get_input_lines(self)
            self.assertEqual(self.EXPECTED_ANSWER_PART_TWO, self.solution.solve_part_two(input_lines))
        else:
            self.skipTest("Not implemented yet")

    def test_part_two_sample(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_TWO_SAMPLE'):
            input_lines = test_utils.get_input_lines(self, '-sample')
            self.assertEqual(self.EXPECTED_ANSWER_PART_TWO_SAMPLE, self.solution.solve_part_two(input_lines))
        else:
            self.skipTest("Not implemented yet")
