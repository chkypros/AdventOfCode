import test.aoc_template.solution_test as solution_test

from src.aoc2023.day5.if_you_give_a_seed_a_fertilizer import IfYouGiveASeedAFertilizer

class IfYouGiveASeedAFertilizerTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "35"
    EXPECTED_ANSWER_PART_ONE = "309796150"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "46"
    EXPECTED_ANSWER_PART_TWO = "0"
    solution = IfYouGiveASeedAFertilizer()
