import test.aoc_template.solution_test as solution_test

from src.aoc2023.day4.scratchcards import Scratchcards

class ScratchcardsTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "13"
    EXPECTED_ANSWER_PART_ONE = "26914"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "30"
    EXPECTED_ANSWER_PART_TWO = "13080971"
    solution = Scratchcards()
