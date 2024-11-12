from enum import IntEnum

import src.aoc_template.solution as solution

CARDS_IN_HAND = 5
JOKER_VALUE = 0

class HandType(IntEnum):
    FIVE_OF_A_KIND = 20,
    FOUR_OF_A_KIND = 19,
    FULL_HOUSE = 18,
    THREE_OF_A_KIND = 17,
    TWO_PAIR = 16,
    ONE_PAIR = 15,
    HIGH_CARD = 14

SPECIAL_CARD_VALUES = {'A': 14, 'K': 13, 'Q': 12, 'J': 11, 'T': 10}

def _get_card_value(card: str, j_override: int | None = None) -> int:
    if card == 'J' and j_override is not None:
        return j_override
    if card in SPECIAL_CARD_VALUES:
        return SPECIAL_CARD_VALUES[card]
    else:
        return int(card)

def _get_card_counts(card_values: list[int]) -> dict[int, int]:
    card_counts = {card: card_values.count(card) for card in set(card_values)}
    if JOKER_VALUE in card_counts and card_counts[JOKER_VALUE] != CARDS_IN_HAND:
        joker_count = card_counts.pop(JOKER_VALUE)
        max_card = max(card_counts, key=card_counts.get)
        card_counts[max_card] += joker_count
    return card_counts

def _get_hand_value(card_values: list[int]):
    card_counts = _get_card_counts(card_values)
    if 5 in card_counts.values():
        return HandType.FIVE_OF_A_KIND
    elif 4 in card_counts.values():
        return HandType.FOUR_OF_A_KIND
    elif 3 in card_counts.values() and 2 in card_counts.values():
        return HandType.FULL_HOUSE
    elif 3 in card_counts.values():
        return HandType.THREE_OF_A_KIND
    elif 2 in card_counts.values():
        values = list(card_counts.values())
        if values.count(2) == 2:
            return HandType.TWO_PAIR
        else:
            return HandType.ONE_PAIR
    else:
        return HandType.HIGH_CARD

def _parse_input(input_line: str, j_override: int | None = None) -> tuple[HandType, list[int], int]:
    card_values = [_get_card_value(card, j_override) for card in input_line.split()[0]]
    hand_type_value = _get_hand_value(card_values)
    bid = int(input_line.split()[1])
    return hand_type_value, card_values, bid

class CamelCards(solution.AbstractSolution):
    def solve_part_one(self, input_lines: list[str]) -> str:
        hand_infos = [_parse_input(input_line) for input_line in input_lines]
        hand_infos.sort(key=lambda i: (i[0], i[1]))
        total_winnings = sum([hand_infos[i][2] * (i + 1) for i in range(len(hand_infos))])
        return str(total_winnings)

    def solve_part_two(self, input_lines: list[str]) -> str:
        hand_infos = [_parse_input(input_line, JOKER_VALUE) for input_line in input_lines]
        hand_infos.sort(key=lambda i: (i[0], i[1]))
        total_winnings = sum([hand_infos[i][2] * (i + 1) for i in range(len(hand_infos))])
        return str(total_winnings)
