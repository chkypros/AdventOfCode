import test.aoc_template.solution_test as solution_test

from src.aoc2023.day2.code_conundrum import CodeConundrum

class CodeConundrumTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "8"
    EXPECTED_ANSWER_PART_ONE = "2377"
    solution = CodeConundrum()
