from tkinter import *
from board import Board
from symbols import Symbol
from game_runner import GameRunner
from player import Player
from tkinter import messagebox

class MainWindow(Tk):

    def __init__(self, screenName: str | None = None, baseName: str | None = None, className: str = "Tk", useTk: bool = True, sync: bool = False, use: str | None = None, board_grid_size=3) -> None:
        super().__init__(screenName, baseName, className, useTk, sync, use)

        self.title("Tic Tac Toe")
        self.geometry("500x500")
        self.resizable(False, False)
        self.board_grid_size=board_grid_size
        self.board=None 
        self.player=None

        self.button_grid_frame=Frame()
        self.button_grid_frame.pack(pady=(100, 50))
        self.board=Board(self.board_grid_size) 
        self.game_runner=GameRunner(self.board)
        self.player=self.game_runner.players[0]
        self.create_buttons()
        self.player_label=Label(self, text="It's " + str(self.player) + "'s turn", font="sans 12")
        self.player_label.pack()

        
        

    

    def create_buttons(self) -> None:    
        for x in range(self.board_grid_size):
            for y in range(self.board_grid_size):
               button=Button(self.button_grid_frame, text=Symbol.EMPTY, width=8, pady=20, font="sans 12 bold")
               button['command']= lambda btn=button: self.press_button(btn, self.player)
               button.grid(row=x, column=y)

    
    def press_button(self, button: Button, player: Player) -> None:
        self.game_runner.register_move(button, player)
              
        if self.game_runner.check_board():
            for node in self.button_grid_frame.winfo_children():
                node.config(state="disabled")

            self.player_label.config(text=str(self.player) + " won!")
        elif self.game_runner.move_count == self.game_runner.max_moves:
            self.player_label.config(text="No more moves left")
        else:
            self.player=self.game_runner.switch_player(self.player)
            self.player_label.config(text="It's " + str(self.player) + "'s turn")


