import src.aoc_template.solution as solution
from collections import Counter

def _parse_lists(input_lines: list[str]) -> tuple[list[int], list[int]]:
    left_list = []
    right_list = []
    for i in range(len(input_lines)):
        parts = input_lines[i].split()
        left_list.append(int(parts[0]))
        right_list.append(int(parts[1]))
    return left_list, right_list

class HistorianHysteria(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        diff = 0
        left_list, right_list = _parse_lists(input_lines)
        sorted_left_list = sorted(left_list)
        sorted_right_list = sorted(right_list)
        for i in range(len(sorted_left_list)):
            diff += abs(sorted_left_list[i] - sorted_right_list[i])
        return diff

    def solve_part_two(self, input_lines: list[str]) -> object:
        left_list, right_list = _parse_lists(input_lines)
        left_count_map = Counter(left_list)
        right_count_map = Counter(right_list)
        scores = [num * left_count_map[num] * right_count_map.get(num, 0) for num in left_count_map.keys()]
        return sum(scores)
