import time
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
            start_time = time.time()
            input_lines = test_utils.get_input_lines(self, label)
            result = method(input_lines)
            duration = time.time() - start_time
            self.assertEqual(self.__getattribute__(attribute), str(result))
            print(f"Executed [{method.__qualname__}] with input label [{label}] in [{duration} seconds]")
        else:
            self.skipTest("Not implemented yet")
