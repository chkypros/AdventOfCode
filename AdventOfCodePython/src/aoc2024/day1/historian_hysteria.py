import src.aoc_template.solution as solution

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
