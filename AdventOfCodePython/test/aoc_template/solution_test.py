import unittest
import test.aoc_common.test_utils as test_utils

from src.aoc_template.solution import AbstractSolution

class AbstractSolutionTest(unittest.TestCase):
    solution: AbstractSolution

    def test_part_one(self):
        if hasattr(self, 'EXPECTED_ANSWER_PART_ONE'):
            input = test_utils.get_input(self)
            self.assertEqual(self.EXPECTED_ANSWER_PART_ONE, self.solution.solve_part_one(input))
