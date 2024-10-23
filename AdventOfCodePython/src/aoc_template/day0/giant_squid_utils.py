from .bingo_board import BingoBoard

__SIZE = BingoBoard.SIZE + 1

def get_bingo_boards(board_descriptions: list[str]) -> list[BingoBoard]:
    return [BingoBoard(board_descriptions[i : i + __SIZE]) for i in range(0, len(board_descriptions), __SIZE)]
