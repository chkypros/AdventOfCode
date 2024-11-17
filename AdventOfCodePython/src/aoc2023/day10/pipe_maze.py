import src.aoc_template.solution as solution
from aoc2023.day10.tile_graph import TileGraph, Tile, TileSide, PipeTile, GroundTile, StartingTile

def _get_tile(tile_str: str) -> Tile:
    match tile_str:
        case '|': return PipeTile([TileSide.NORTH, TileSide.SOUTH])
        case '-': return PipeTile([TileSide.WEST, TileSide.EAST])
        case 'L': return PipeTile([TileSide.NORTH, TileSide.EAST])
        case 'J': return PipeTile([TileSide.NORTH, TileSide.WEST])
        case '7': return PipeTile([TileSide.WEST, TileSide.SOUTH])
        case 'F': return PipeTile([TileSide.EAST, TileSide.SOUTH])
        case '.': return GroundTile()
        case 'S': return StartingTile()

def _parse_tile_graph(input_lines: list[str]) -> TileGraph:
    graph = TileGraph()

    for i, line in enumerate(input_lines):
        tiles_row: list[Tile] = []
        for j, tile_str in enumerate(line):
            tile: Tile = _get_tile(tile_str)
            if isinstance(tile, StartingTile):
                graph.starting_position = (i,j)
            tiles_row.append(tile)
        graph.tiles.append(tiles_row)

    return graph

def _populate_starting_tile(graph: TileGraph) -> None:
    s_i = graph.starting_position[0]
    s_j = graph.starting_position[1]
    connectors = []
    if graph.tiles[s_i - 1][s_j].has(TileSide.SOUTH): connectors.append(TileSide.NORTH)
    if graph.tiles[s_i + 1][s_j].has(TileSide.NORTH): connectors.append(TileSide.SOUTH)
    if graph.tiles[s_i][s_j - 1].has(TileSide.EAST): connectors.append(TileSide.WEST)
    if graph.tiles[s_i][s_j + 1].has(TileSide.WEST): connectors.append(TileSide.EAST)

    graph.tiles[s_i][s_j] = PipeTile(connectors)

def _count_tiles_in_loop(graph: TileGraph) -> int:
    return 4

class PipeMaze(solution.AbstractSolution):

    def solve_part_one(self, input_lines: list[str]) -> object:
        tile_graph: TileGraph = _parse_tile_graph(input_lines)
        _populate_starting_tile(tile_graph)
        loop_length: int = _count_tiles_in_loop(tile_graph)

        return loop_length // 2
