import re

import src.aoc_template.solution as solution


def _get_calibration_value(line: str) -> int:
    characters = re.findall("[\d]", line)
    return int(characters[0] + characters[-1])

class Trebuchet(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        calibration_values = map(lambda line : _get_calibration_value(line), input_lines)
        return str(sum(calibration_values))
