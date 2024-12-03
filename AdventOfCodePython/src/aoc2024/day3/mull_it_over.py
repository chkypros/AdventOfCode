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
