import src.aoc_template.solution as solution

def _parse_history(line: str) -> list[int]:
    return [int(number) for number in line.split()]

def _all_zero_sequence(sequence: list[int]) -> bool:
    non_zero_elements = [element for element in sequence if element != 0]
    return len(non_zero_elements) == 0

def _calculate_sequence_stack(history: list[int]) -> list[list[int]]:
    sequence_stack = [history]
    while not _all_zero_sequence(sequence_stack[-1]):
        next_sequence = [sequence_stack[-1][i] - sequence_stack[-1][i-1] for i in range(1, len(sequence_stack[-1]))]
        sequence_stack.append(next_sequence)
    return sequence_stack

def _extrapolate_next_value(sequence_stack: list[list[int]]) -> int:
    return sum([sequence[-1] for sequence in sequence_stack])

def _extrapolate_prev_value(sequence_stack: list[list[int]]) -> int:
    prev_value = 0
    for i in reversed(range(0, len(sequence_stack))):
        prev_value = sequence_stack[i][0] - prev_value
    return prev_value

class MirageMaintenance(solution.AbstractSolution):

    def solve_part_one(self, input_lines: list[str]) -> object:
        histories = [_parse_history(line) for line in input_lines]
        sequence_stacks = [_calculate_sequence_stack(history) for history in histories]
        next_values = [_extrapolate_next_value(sequence_stack) for sequence_stack in sequence_stacks]
        return sum(next_values)

    def solve_part_two(self, input_lines: list[str]) -> object:
        histories = [_parse_history(line) for line in input_lines]
        sequence_stacks = [_calculate_sequence_stack(history) for history in histories]
        prev_values = [_extrapolate_prev_value(sequence_stack) for sequence_stack in sequence_stacks]
        return sum(prev_values)
