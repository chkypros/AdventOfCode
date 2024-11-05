
import src.aoc_template.solution as solution

def _get_symbol_adjacent_numbers(schematic: list[str]) -> list[int]:
    numbers = []

    for row in range(len(schematic)):
        start = None
        end = None
        is_adjacent = False
        for column in range(len(schematic[row])):
            c = schematic[row][column]
            if c.isnumeric():
                if start is None:
                    start = column
                if not is_adjacent and _has_adjacent_symbol(schematic, row, column):
                    is_adjacent = True
                if column == len(schematic[row]) - 1:
                    end = column + 1
            else:
                if start is not None:
                    end = column

            if end is not None:
                if is_adjacent:
                    number = schematic[row][start:end]
                    numbers.append(int(number))
                start = None
                end = None
                is_adjacent = False

    return numbers

def _has_adjacent_symbol(schematic: list[str], row: int, column: int) -> bool:
    return (_is_symbol(schematic, row - 1, column - 1)
            or _is_symbol(schematic, row - 1, column)
            or _is_symbol(schematic, row - 1, column + 1)
            or _is_symbol(schematic, row, column - 1)
            or _is_symbol(schematic, row, column + 1)
            or _is_symbol(schematic, row + 1, column - 1)
            or _is_symbol(schematic, row + 1, column)
            or _is_symbol(schematic, row + 1, column + 1)
            )

def _is_symbol(schematic: list[str], row: int, column: int) -> bool:
    if not (0 <= row < len(schematic)) or not (0 <= column < len(schematic[row])):
        return False
    c = schematic[row][column]
    return not c.isnumeric() and c != "."

class GearRatios(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        schematic = input_lines
        numbers = _get_symbol_adjacent_numbers(schematic)
        return str(sum(numbers))
