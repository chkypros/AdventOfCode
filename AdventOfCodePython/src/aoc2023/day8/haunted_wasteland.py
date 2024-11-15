import math
import re
import src.aoc_template.solution as solution

def _map_network_node(line: str) -> tuple[str , tuple[str, str]]:
    result = re.search("(\S{3}) = \\((\S{3}), (\S{3})\\)", line)
    if result is None: raise IOError
    return result.group(1), (result.group(2), result.group(3))

def _parse_input(input_lines: list[str]) -> tuple[list[int], dict[str, tuple[str, str]]]:
    sequence = [1 if instruction == 'R' else 0 for instruction in input_lines[0]]
    network = dict([_map_network_node(line) for line in input_lines[2:]])
    return sequence, network

def _find_starting_nodes(nodes: list[str]) -> list[str]:
    return list(filter(lambda n: n.endswith('A'), nodes))

def _steps_until_z_ending(network, node, sequence) -> int:
    steps = 0
    while True:
        index = steps % len(sequence)
        steps += 1
        node = network[node][sequence[index]]
        if node.endswith('Z'): break
    return steps

class HauntedWasteland(solution.AbstractSolution):

    def solve_part_one(self, input_lines: list[str]) -> object:
        sequence, network = _parse_input(input_lines)
        return _steps_until_z_ending(network, 'AAA', sequence)

    def solve_part_two(self, input_lines: list[str]) -> object:
        sequence, network = _parse_input(input_lines)
        node_paths = _find_starting_nodes(list(network.keys()))
        steps_to_z = [_steps_until_z_ending(network, node, sequence) for node in node_paths]
        return math.lcm(*steps_to_z)
