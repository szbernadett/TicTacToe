from symbols import Symbol

class Board():

    def __init__(self, grid_size: int) -> None:
        self.__grid_size=grid_size
        self.__grid=[[Symbol.EMPTY for x in range(self.__grid_size)] for y in range(self.grid_size)]

    @property
    def grid_size(self) -> int:
        return self.__grid_size
    
    @grid_size.setter
    def grid_size(self, grid_size: int) -> None:
        self.__grid_size=grid_size

    @property  
    def grid(self) -> list:
        return self.__grid
    
    @grid.setter
    def grid(self, grid: list) -> None:
        self.__grid=grid

    

    