class BingoBoard:

    SIZE = 5

    def __init__(self, board_description: list[str]):
        self.completed = False

        self.checked = [[False for _ in range(BingoBoard.SIZE)] for _ in range(BingoBoard.SIZE)]
        self.board = [[num for num in line.split()] for line in board_description[1:]]

    def check_number(self, number: str) -> bool:
        if self.completed: return False

        for i in range(BingoBoard.SIZE):
            for j in range(BingoBoard.SIZE):
                if self._check_number(number, i, j):
                    break

        return self.completed

    def _check_number(self, number, i, j):
        if number == self.board[i][j]:
            self.checked[i][j] = True
            if self.is_row_complete(i) or self.is_column_complete(j):
                self.completed = True
                return True
        return False

    def is_row_complete(self, row):
        for column in range(BingoBoard.SIZE):
            if (not self.checked[row][column]): return False
        return True

    def is_column_complete(self, column):
        for row in range(BingoBoard.SIZE):
            if (not self.checked[row][column]): return False
        return True

    def get_unmarked_score(self) -> int:
        score = 0
        for i in range(BingoBoard.SIZE):
            for j in range(BingoBoard.SIZE):
                if (not self.checked[i][j]):
                    score += int(self.board[i][j])
        return score
