
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


def _get_gear_ratios(schematic: list[str]) -> list[int]:
    symbols: dict[tuple[str, int, int], list[int]] = {}

    for row in range(len(schematic)):
        start = None
        end = None
        adjacent_symbols = set()
        for column in range(len(schematic[row])):
            c = schematic[row][column]
            if c.isnumeric():
                if start is None:
                    start = column
                adjacent_symbols.update(_get_adjacent_symbols(schematic, row, column))
                if column == len(schematic[row]) - 1:
                    end = column + 1
            else:
                if start is not None:
                    end = column

            if end is not None:
                number = schematic[row][start:end]
                [_add_number_to_symbol(number, s, symbols) for s in adjacent_symbols]
                start = None
                end = None
                adjacent_symbols = set()

    return [v[0]*v[1] for (k, v) in symbols.items() if k[0] == "*" and len(v) == 2]


def _add_number_to_symbol(number, s, symbols):
    if s not in symbols:
        symbols[s] = []
    return symbols[s].append(int(number))

def _get_adjacent_symbols(schematic: list[str], row: int, column: int) -> set[tuple[str, int, int]]:
    adjacent_symbols = set()

    for r in range(row - 1, row + 2):
        for c in range(column - 1, column + 2):
            if r != row or c != column:
                symbol = _find_symbol(schematic, r, c)
                if symbol is not None:
                    adjacent_symbols.add(symbol)

    return adjacent_symbols


def _find_symbol(schematic: list[iter], row: int, column: int) -> tuple[str, int, int] | None:
    if (_is_symbol(schematic, row, column)):
        return (schematic[row][column], row, column)
    return None

class GearRatios(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        numbers = _get_symbol_adjacent_numbers(input_lines)
        return str(sum(numbers))

    def solve_part_two(self, input_lines: list[str]) -> str:
        gear_ratios = _get_gear_ratios(input_lines)
        return str(sum(gear_ratios))
