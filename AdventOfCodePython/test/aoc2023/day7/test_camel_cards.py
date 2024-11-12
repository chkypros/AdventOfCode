import test.aoc_template.solution_test as solution_test

from src.aoc2023.day7.camel_cards import CamelCards

class CamelCardsTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "6440"
    EXPECTED_ANSWER_PART_ONE = "246912307"
    solution = CamelCards()
