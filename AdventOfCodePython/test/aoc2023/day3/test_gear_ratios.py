import test.aoc_template.solution_test as solution_test

from src.aoc2023.day3.gear_ratios import GearRatios

class GearRatiosTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "4361"
    EXPECTED_ANSWER_PART_ONE = "533784"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "467835"
    EXPECTED_ANSWER_PART_TWO = "78826761"
    solution = GearRatios()
