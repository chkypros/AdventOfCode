import unittest

import test.aoc_common.test_utils as test_utils
from src.aoc_template.solution import AbstractSolution


class AbstractSolutionTest(unittest.TestCase):
    solution: AbstractSolution

    def test_part_one(self):
        self._run_test(self.solution.solve_part_one, 'EXPECTED_ANSWER_PART_ONE')

    def test_part_one_sample(self):
        self._run_test(self.solution.solve_part_one, 'EXPECTED_ANSWER_PART_ONE_SAMPLE', '-sample')

    def test_part_two(self):
        self._run_test(self.solution.solve_part_two, 'EXPECTED_ANSWER_PART_TWO')

    def test_part_two_sample(self):
        self._run_test(self.solution.solve_part_two, 'EXPECTED_ANSWER_PART_TWO_SAMPLE', '-sample')

    def _run_test(self, method, attribute, label = ""):
        if hasattr(self, attribute):
            input_lines = test_utils.get_input_lines(self, label)
            self.assertEqual(self.__getattribute__(attribute), method(input_lines))
        else:
            self.skipTest("Not implemented yet")
