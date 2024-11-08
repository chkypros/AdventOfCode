
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
    def solve_part_one(self, input_lines: list[str]) -> str:
        (values, mappers) = _parse_input(input_lines)
        for mapper in mappers:
            values = [mapper.map(value) for value in values]
        return str(min(values))
