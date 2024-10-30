import src.aoc_template.solution as solution
import re

class Trebuchet(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        calibration_values = map(lambda line : self.__get_calibration_value(line), input_lines)
        return str(sum(calibration_values))

    def __get_calibration_value(self, line: str) -> int:
        characters = re.findall("[0-9]", line)
        return int(characters[0] + characters[-1])
