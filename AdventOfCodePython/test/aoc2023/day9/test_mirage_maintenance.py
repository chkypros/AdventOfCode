import test.aoc_template.solution_test as solution_test

from src.aoc2023.day9.mirage_maintenance import MirageMaintenance

class MirageMaintenanceTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "114"
    EXPECTED_ANSWER_PART_ONE = "1980437560"
    EXPECTED_ANSWER_PART_TWO_SAMPLE = "2"
    EXPECTED_ANSWER_PART_TWO = "977"
    solution = MirageMaintenance()
