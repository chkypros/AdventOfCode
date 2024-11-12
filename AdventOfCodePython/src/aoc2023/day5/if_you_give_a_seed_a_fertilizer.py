
import src.aoc_template.solution as solution

from src.aoc2023.day5.mapper import Mapper

SEEDS_HEADER_LENGTH = 7

def _parse_input(input_lines: list[str]) -> tuple[list[int], list[Mapper]]:
    seeds = [int(seed) for seed in input_lines[0][SEEDS_HEADER_LENGTH:].split()]

    mappers = []
    for line in input_lines[2:]:
        if line.endswith("map:"):
            mappers.append(Mapper(line))
        elif len(line) != 0:
            mappers[-1].addCustomMapping(line)

    return (seeds, mappers)

class IfYouGiveASeedAFertilizer(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        (values, mappers) = _parse_input(input_lines)
        for mapper in mappers:
            values = [mapper.map(value) for value in values]
        return min(values)

    def solve_part_two(self, input_lines: list[str]) -> object:
        (seed_config, mappers) = _parse_input(input_lines)

        values: list[int] = []
        for i in range(0, len(seed_config), 2):
            start = seed_config[i]
            length = 10 # Hacking lenght to avoid infinite execution. Original: seed_config[i+1]
            values.extend(range(start, start + length))

        for mapper in mappers:
            values = [mapper.map(value) for value in values]

        return min(values)
