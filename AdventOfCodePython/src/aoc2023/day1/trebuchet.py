import re

import src.aoc_template.solution as solution

VALID_DIGIT_WORDS = "one|two|three|four|five|six|seven|eight|nine"


def _solve(input_lines: list[str], get_calibration_value) -> str:
    calibration_values = map(lambda line : get_calibration_value(line), input_lines)
    return str(sum(calibration_values))


def _get_digit(text):
    if len(text) == 1: return text
    word_list = VALID_DIGIT_WORDS.split("|")
    value = word_list.index(text) + 1
    return str(value)

def _get_calibration_value_p1(line: str) -> int:
    characters = re.findall("[\d]", line)
    return int(characters[0] + characters[-1])

def _get_calibration_value_p2(line: str) -> int:
    characters = re.findall(f'(?=(\d|{VALID_DIGIT_WORDS}))', line)

    digit1 = _get_digit(characters[0])
    digit2 = _get_digit(characters[-1])
    return int(digit1 + digit2)

class Trebuchet(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        return _solve(input_lines, _get_calibration_value_p1)

    def solve_part_two(self, input_lines: list[str]) -> str:
        return _solve(input_lines, _get_calibration_value_p2)
