import re
from functools import reduce

import src.aoc_template.solution as solution

MAX_OF_COLOR = {
    "green": 13,
    "red": 12,
    "blue": 14
}

def _parse_line(line: str) -> tuple[int, list[tuple[int, int, int]]]:
    parts = line.split(":")
    game_id = int(parts[0].split()[1])
    sets = [_parse_set(s) for s in parts[1].split(";")]
    return game_id, sets

def _parse_set(s: str) -> tuple[int, int, int]:
    greens = _get_color_count("green", s)
    reds = _get_color_count("red", s)
    blues = _get_color_count("blue", s)
    return greens, reds, blues

def _get_color_count(color, s) -> int:
    result = re.search(f"(\d+) {color}", s)
    return int(result.group(1)) if result is not None else 0

def _part_one_filter_line(game: tuple[int, list[tuple[int, int, int]]]) -> bool:
    for s in game[1]:
        for i in range(3):
            if s[i] > list(MAX_OF_COLOR.values())[i]:
                return False
    return True

def _part_two_get_game_power(game: tuple[int, list[tuple[int, int, int]]]) -> int:
    min_set = reduce(lambda m, s: (
        max(m[0], s[0]),
        max(m[1], s[1]),
        max(m[2], s[2])
    ), game[1], (0, 0, 0))
    return reduce(lambda a,b: a * b, min_set)

class CodeConundrum(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        game_lines = map(_parse_line, input_lines)
        filtered_game_lines = filter(_part_one_filter_line, game_lines)
        game_id_sum = reduce(lambda sum, element: sum + element[0], filtered_game_lines, 0)
        return str(game_id_sum)

    def solve_part_two(self, input_lines: list[str]) -> str:
        game_lines = map(_parse_line, input_lines)
        game_powers = map(_part_two_get_game_power, game_lines)
        return str(sum(game_powers))
