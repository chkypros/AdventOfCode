import test.aoc_template.solution_test as solution_test

from src.aoc2023.day9.mirage_maintenance import MirageMaintenance

class MirageMaintenanceTest(solution_test.AbstractSolutionTest):
    EXPECTED_ANSWER_PART_ONE_SAMPLE = "114"
    EXPECTED_ANSWER_PART_ONE = "0"
    solution = MirageMaintenance()
