from functools import reduce

import src.aoc_template.solution as solution


def _parse_race_info(input_lines: list[str]) -> list[tuple[int, int]]:
    times = input_lines[0].split()[1:]
    distances = input_lines[1].split()[1:]
    return [(int(times[i]), int(distances[i])) for i in range(len(times))]

def _parse_race_info_part_two(input_lines: list[str]) -> list[tuple[int, int]]:
    times = str.join('', input_lines[0].split()[1:])
    distances = str.join('', input_lines[1].split()[1:])
    return [(int(times), int(distances))]


def _travel_with_hold(hold_time, total_time):
    return (total_time - hold_time) * hold_time


def _find_next_win_hold(time, distance, step):
    # Ignoring holding for 0 seconds or all time
    (start, end) = (1, time - 1) if step == 1 else (time - 1, 1)
    index = start

    while index != end:
        if (_travel_with_hold(index, time) > distance):
            return index
        index += step

    return None


def _find_win_range(time: int, distance: int):
    min = _find_next_win_hold(time, distance, 1)
    max = _find_next_win_hold(time, distance, -1)
    return (min, max)


class WaitForIt(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        race_infos = _parse_race_info(input_lines)
        win_range_infos = [_find_win_range(*race_info) for race_info in race_infos]
        result = reduce(lambda acc, range: acc * (range[1] + 1 - range[0]), win_range_infos, 1)
        return str(result)

    def solve_part_two(self, input_lines: list[str]) -> str:
        race_infos = _parse_race_info_part_two(input_lines)
        win_range_infos = [_find_win_range(*race_info) for race_info in race_infos]
        result = reduce(lambda acc, range: acc * (range[1] + 1 - range[0]), win_range_infos, 1)
        return str(result)
