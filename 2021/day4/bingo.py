

class Board:

    def __init__(self, board):
        self.board = board
        self.last_number = 0
        self.winning = False

    def mark_number(self, number):
        if self.winning:
            return False
        self.last_number = number
        for idx_lst, lst in enumerate(self.board):
            for idx_item, item in enumerate(lst):
                if item == number:
                    self.board[idx_lst][idx_item] = 100
                    if (self.check_for_winning_board()):
                        return True
        return False

    def winning_board(self):
        self.winning = True
        sum = self.sum_unmarked()
        print("\n Winning board: \n")
        self.print_board()
        print("sum: " + str(sum))
        print("last: " + str(self.last_number))

        print("solution:  " + str(sum*self.last_number))

    def sum_unmarked(self):
        sum = 0
        for line in self.board:
            for number in line:
                if number != 100:
                    sum += number
        return sum

    def check_for_winning_board(self):
        columns = [0, 0, 0, 0, 0]
        for x, lst in enumerate(self.board):
            if lst.count(100) == 5:
                return True
            for y, item in enumerate(lst):
                columns[y] = columns[y] + item
            for item in columns:
                if item == 500:
                    return True
        return False

    def print_board(self):
        print('\n'.join([' '.join([str(cell) for cell in row])
                         for row in self.board]))


class Bingo:

    def __init__(self):
        self.part1 = True
        self.numbers = None
        self.boards = []
        f = open("day4/input.txt", "r")
        self.make_board = []
        for line in f:
            line.strip()
            if self.numbers == None:
                self.numbers = [int(x) for x in line.split(',')]
            else:
                if line != '\n':
                    self.make_board.append([int(x) for x in line.split()])
                else:
                    if self.make_board:
                        self.boards.append(Board(self.make_board))
                        self.make_board = []
        self.boards.append(Board(self.make_board))
        self.read_number()

    def read_number(self):
        last_board = None
        for number in self.numbers:
            for board in self.boards:
                if board.mark_number(number):
                    if self.part1:
                        self.part1 = False
                        print("Part1")
                        board.winning_board()
                        print("")
                    last_board = board
                    self.boards.remove(board)
        print("last winning board")
        last_board.winning_board()


bingo = Bingo()
