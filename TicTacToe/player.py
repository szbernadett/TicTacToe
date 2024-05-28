from symbols import Symbol

class Player():

    def __init__(self, symbol: Symbol) -> None:
        self.__symbol=symbol
     


    @property
    def symbol(self) -> Symbol:
        return self.__symbol
    
    @symbol.setter
    def symbol(self, symbol: Symbol) -> None:
        self.__symbol=symbol

    def __str__(self):
        return self.__symbol
    
    def __repr__(self) -> str:
        return self.__symbol