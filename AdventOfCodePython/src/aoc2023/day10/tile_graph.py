from abc import ABC

class TileSide:
    NORTH: "TileSide"
    SOUTH: "TileSide"
    WEST: "TileSide"
    EAST: "TileSide"

    def __init__(self, next_node_offset: tuple[int, int]):
        self.next_node_offset = next_node_offset
        self.opposite: TileSide = None

    @classmethod
    def init_sides(cls):
        cls.NORTH = TileSide((-1, 0))
        cls.SOUTH = TileSide((1, 0))
        cls.WEST = TileSide((0, -1))
        cls.EAST = TileSide((0, 1))

        cls.NORTH.opposite = cls.SOUTH
        cls.SOUTH.opposite = cls.NORTH
        cls.WEST.opposite = cls.EAST
        cls.EAST.opposite = cls.WEST

    def get_next_node_offset(self) -> tuple[int, int]:
        return self.next_node_offset

    def get_opposite(self):
        return self.opposite

class Tile(ABC):

    def __init__(self, position: tuple[int, int]):
        super().__init__()
        self.__position = position

    def get_position(self) -> tuple[int, int]:
        return self.__position

class PipeTile(Tile):
    def __init__(self, connectors: list[TileSide], position: tuple[int, int]):
        super().__init__(position)
        self.connectors = connectors

    def has(self, connector: TileSide) -> bool:
        return connector in self.connectors

    def get_other_connector(self, side):
        side_index = self.connectors.index(side)
        return self.connectors[side_index + 1 % 2]

class GroundTile(Tile):
    def __init__(self, position: tuple[int, int]):
        super().__init__(position)

class StartingTile(Tile):
    def __init__(self, position: tuple[int, int]):
        super().__init__(position)

class TileGraph:
    starting_position: tuple[int, int] = None
    tiles: list[list[Tile]] = []
