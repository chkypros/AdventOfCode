
import src.aoc_template.solution as solution


def _parse_card_info(input_line: str) -> tuple[str, list[str], list[str]]:
    colon_index = input_line.index(":")
    pipe_index = input_line.index("|")
    return (
        input_line[:colon_index],
        input_line[colon_index + 2:pipe_index - 1].split(),
        input_line[pipe_index + 2:].split()
    )

def _get_winning_numbers(card_info: tuple[str, list[str], list[str]]) -> list[str]:
    return [number for number in card_info[1] if number in card_info[2]]

class Scratchcards(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> object:
        card_info_list = [_parse_card_info(input_line) for input_line in input_lines]

        winning_numbers_counts = [len(_get_winning_numbers(card_info)) for card_info in card_info_list]
        return sum([2 ** (count - 1) for count in winning_numbers_counts if count > 0])

    def solve_part_two(self, input_lines: list[str]) -> object:
        card_info_list = [(1, _parse_card_info(input_line)) for input_line in input_lines]

        scratchcards = 0
        for i in range(len(card_info_list)):
            card_info = card_info_list[i]
            winning_numbers_count = len(_get_winning_numbers(card_info[1]))
            scratchcards += card_info[0]

            for j in range(i + 1, i + 1 + winning_numbers_count):
                card_info_list[j] = (card_info_list[j][0] + card_info[0], card_info_list[j][1])

        return scratchcards
