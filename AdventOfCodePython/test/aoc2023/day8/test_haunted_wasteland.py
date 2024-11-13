import test.aoc_template.solution_test as solution_test

from src.aoc2023.day8.haunted_wasteland import HauntedWasteland

class HauntedWastelandTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "2"
    EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO = "6"
    solution = HauntedWasteland()

    def test_part_one_sample_two(self):
        self._run_test(self.solution.solve_part_one, "EXPECTED_ANSWER_PART_ONE_SAMPLE_TWO", "sample2")
