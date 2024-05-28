
from player import Player
from symbols import Symbol
from board import Board
from tkinter import *

class GameRunner():

    def __init__(self, board: Board) -> None:
        self.__players=(Player(Symbol.X), Player(Symbol.O))
        self.__board=board
        self.__max_moves=pow(self.__board.grid_size, 2)
        self.__move_count=0

    @property
    def players(self) -> tuple:
        return self.__players
    
    @players.setter
    def players(self, players: tuple) -> None:
        self.__players=players

    @property
    def board(self) -> Board:
        return self.__board
    
    @board.setter
    def board(self, board: Board) -> None:
        self.__board=board

    @property
    def max_moves(self) -> int:
        return self.__max_moves
    
    @max_moves.setter
    def max_moves(self, max: int) -> None:
        self.__max_moves=max

    @property
    def move_count(self) -> int:
        return self.__move_count
    
    @move_count.setter
    def move_count(self, count: int) -> None:
        self.__move_count += count

    def register_move(self, button: Button, player: Player) -> None:
        row=button.grid_info()["row"]
        col=button.grid_info()["column"]
        self.__board.grid[row][col]=player.symbol
        self.move_count = 1
        button.config(text=player.symbol)
        button.config(state="disabled")
        
        

    def switch_player(self, player: Player) -> Player:
        if player == self.__players[0]:
            return self.__players[1]
        else:
            return self.__players[0]
        

    def check_rows(self) -> bool:
        win=False
        match=0
        for x in range(self.__board.grid_size):
            if Symbol.EMPTY not in self.__board.grid[x]:
                for y in range(self.__board.grid_size-1):
                    if not self.__board.grid[x][y] == self.__board.grid[x][y+1]:
                        break
                    else:
                        match+=1

                if match == self.__board.grid_size-1:
                    win=True
                    break
               
        return win


    def check_columns(self) -> bool:
        win=False
        match=0
        for y in range(self.__board.grid_size):
                for x in range(self.__board.grid_size-1):
                    if self.__board.grid[x][y] == Symbol.EMPTY or not self.__board.grid[x][y] == self.__board.grid[x+1][y]:
                        break
                    else:
                        match += 1

                if match == self.__board.grid_size-1:
                    win=True
                    break
                else:
                    match=0
        
        return win


    def check_diagonal_1(self) -> bool:
        win=False
        match=0
        for x in range(self.__board.grid_size-1):
            if self.__board.grid[x][x] == Symbol.EMPTY or not self.__board.grid[x][x] ==self.__board.grid[x+1][x+1]:
                break
            else:
                match += 1

        if match == self.__board.grid_size-1:
            win = True
        
        return win
    
    def check_diagonal_2(self) -> bool:
        win=False
        match=0
        for x, y in zip(range(self.__board.grid_size-1), range(self.__board.grid_size-1, 0, -1)):
            if self.__board.grid[x][y] == Symbol.EMPTY or not self.__board.grid[x][y] == self.__board.grid[x+1][y-1]:
                break
            else:
                match += 1

        if match == self.__board.grid_size-1:
            win=True
   
        return win
    


    def check_board(self) -> bool:
        return self.check_rows() or self.check_columns() or self.check_diagonal_1() or self.check_diagonal_2()