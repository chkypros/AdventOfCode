import src.aoc_template.solution as solution

XMAS_LETTERS = ['X', 'M', 'A', 'S']
X_MAS_SURROUNDING_LETTERS = {'M', 'S'}

def _is_expected_letter(letter: str, input_lines: list[str], position: tuple[int, int]) -> bool:
    if not (0 <= position[0] < len(input_lines)) or not (0 <= position[1] < len(input_lines[0])):
        return False
    return letter == input_lines[position[0]][position[1]]

def _xmas_found(row: int, column: int, input_lines: list[str], step: tuple[int, int]) -> bool:

    position = row, column
    for i in range(len(XMAS_LETTERS)):
        if not _is_expected_letter(XMAS_LETTERS[i], input_lines, position):
            return False
        position = position[0] + step[0], position[1] + step[1]

    # print(f"Found one starting on ({row},{column}) with step: {step}")
    return True

def _count_xmas_starting_from(row: int, column: int, input_lines: list[str]) -> int:
    return len([
        1
        for row_step in range(-1, 2)
        for column_step in range(-1, 2)
        if not (row_step == 0 and column_step == 0)
        if _xmas_found(row, column, input_lines, (row_step, column_step))
    ])

def _has_x_mas_at(row: int, column: int, input_lines: list[str]) -> bool:
    return 'A' == input_lines[row][column] \
            and 0 < row < len(input_lines) - 1 and 0 < column < len(input_lines[0]) - 1 \
            and X_MAS_SURROUNDING_LETTERS == {input_lines[row - 1][column - 1], input_lines[row + 1][column + 1]} \
            and X_MAS_SURROUNDING_LETTERS == {input_lines[row + 1][column - 1], input_lines[row - 1][column + 1]}

class CeresSearch(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        xmas_counts = [
            _count_xmas_starting_from(i, j, input_lines)
            for i in range(len(input_lines))
            for j in range(len(input_lines[0]))
        ]

        return sum(xmas_counts)

    def solve_part_two(self, input_lines: list[str]) -> object:
        return len([
            1
            for i in range(len(input_lines))
            for j in range(len(input_lines[0]))
            if _has_x_mas_at(i, j, input_lines)
        ])
