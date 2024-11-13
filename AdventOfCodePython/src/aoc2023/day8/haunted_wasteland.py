import re
import src.aoc_template.solution as solution

def _map_network_node(line: str) -> tuple[str , tuple[str, str]]:
    result = re.search("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)", line)
    if result is None: raise IOError
    return result.group(1), (result.group(2), result.group(3))

def _parse_input(input_lines: list[str]) -> tuple[list[int], dict[str, tuple[str, str]]]:
    sequence = [1 if instruction == 'R' else 0 for instruction in input_lines[0]]
    network = dict([_map_network_node(line) for line in input_lines[2:]])
    return sequence, network

class HauntedWasteland(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        sequence, network = _parse_input(input_lines)
        steps = 0
        node = 'AAA'
        while True:
            index = steps % len(sequence)
            steps += 1
            node = network[node][sequence[index]]
            if node == 'ZZZ': break

        return steps
