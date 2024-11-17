from abc import ABC, abstractmethod
from enum import EnumType

class TileSide(EnumType): NORTH = 'N'; SOUTH = 'S'; WEST = 'W'; EAST = 'E'

class Tile(ABC):
    @abstractmethod
    def has(self, connector: TileSide) -> bool: pass

class PipeTile(Tile):
    def __init__(self, connectors: list[TileSide]):
        super().__init__()
        self.connectors = connectors

    def has(self, connector: TileSide) -> bool:
        return connector in self.connectors

class GroundTile(Tile):
    def has(self, connector: TileSide) -> bool: return False

class StartingTile(Tile):
    def has(self, connector: TileSide) -> bool: raise NotImplemented

class TileGraph:
    starting_position: tuple[int, int] = None
    tiles: list[list[Tile]] = []
