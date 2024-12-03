import re

import src.aoc_template.solution as solution

class MullItOver(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        products = [
            int(match[0]) * int(match[1])
            for i in range(len(input_lines))
            for match in re.findall("mul\((\d+),(\d+)\)", input_lines[i])
        ]
        return sum(products)

    def solve_part_two(self, input_lines: list[str]) -> object:
        mul_sum = 0
        do_mul = True
        for line in input_lines:
            for match in re.findall(r"(do|don't)\(\)|(mul)\((\d+),(\d+)\)", line):
                match match:
                    case [_, "mul", a, b]:
                        if do_mul: mul_sum += int(a) * int(b)
                    case ["do", _, _, _]: do_mul = True
                    case ["don't", _, _, _]: do_mul = False
        return mul_sum
