import src.aoc_template.solution as solution
import src.aoc_template.day0.giant_squid_utils as giant_squid_utils

class GiantSquid(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        draw_numbers = input_lines[0].split(',')
        bingo_boards = giant_squid_utils.get_bingo_boards(input_lines[1:])

        for number_str in draw_numbers:
            for board in bingo_boards:
                number = int(number_str)
                if board.check_number(number_str):
                    return board.get_unmarked_score() * number

        raise Exception("Could not find result")

    def solve_part_two(self, input_lines: list[str]) -> object:
        draw_numbers = input_lines[0].split(',')
        bingo_boards = giant_squid_utils.get_bingo_boards(input_lines[1:])

        boards_to_win = len(bingo_boards)
        for number_str in draw_numbers:
            for board in bingo_boards:
                number = int(number_str)
                if board.check_number(number_str):
                    boards_to_win -= 1
                    if boards_to_win == 0:
                        return board.get_unmarked_score() * number

        raise Exception("Could not find result")
