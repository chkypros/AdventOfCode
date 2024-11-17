import test.aoc_template.solution_test as solution_test

from src.aoc2023.day10.pipe_maze import PipeMaze

class MirageMaintenanceTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "8"
    EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO = "8"
    solution = PipeMaze()

    def test_part_one_sample_two(self):
        self._run_test(self.solution.solve_part_one, "EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO", "-sample2")

    def test_part_two_sample_three(self):
        self._run_test(self.solution.solve_part_two, "EXPECTED_ANSWER_PART_TWO_SAMPLE_THREE", "-sample3")
