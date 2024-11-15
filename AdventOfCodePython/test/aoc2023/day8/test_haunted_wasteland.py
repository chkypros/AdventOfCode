import test.aoc_template.solution_test as solution_test

from src.aoc2023.day8.haunted_wasteland import HauntedWasteland

class HauntedWastelandTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "2"
    EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO = "6"
    EXPECTED_ANSWER_PART_ONE = "16271"
    EXPECTED_ANSWER_PART_TWO_SAMPLE_THREE = "6"
    EXPECTED_ANSWER_PART_TWO = "14265111103729"
    solution = HauntedWasteland()

    def test_part_one_sample_two(self):
        self._run_test(self.solution.solve_part_one, "EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO", "-sample2")

    def test_part_two_sample_three(self):
        self._run_test(self.solution.solve_part_two, "EXPECTED_ANSWER_PART_TWO_SAMPLE_THREE", "-sample3")
