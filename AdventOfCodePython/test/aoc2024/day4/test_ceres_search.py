import test.aoc_template.solution_test as solution_test

from src.aoc2024.day4.ceres_search import CeresSearch

class CeresSearchTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "18"
    solution = CeresSearch()
