import src.aoc_template.solution as solution
from aoc2023.day10.tile_graph import *

def _get_tile(tile_str: str, position: tuple[int, int]) -> Tile:
    match tile_str:
        case '|': return PipeTile([NORTH, SOUTH], position)
        case '-': return PipeTile([WEST, EAST], position)
        case 'L': return PipeTile([NORTH, EAST], position)
        case 'J': return PipeTile([NORTH, WEST], position)
        case '7': return PipeTile([WEST, SOUTH], position)
        case 'F': return PipeTile([EAST, SOUTH], position)
        case '.': return GroundTile(position)
        case 'S': return StartingTile(position)

def _parse_tile_graph(input_lines: list[str]) -> TileGraph:
    graph = TileGraph()

    for i, line in enumerate(input_lines):
        tiles_row: list[Tile] = []
        for j, tile_str in enumerate(line):
            tile: Tile = _get_tile(tile_str, (i, j))
            if isinstance(tile, StartingTile):
                graph.starting_position = (i,j)
            tiles_row.append(tile)
        graph.tiles.append(tiles_row)

    return graph

def _update_connectors(connectors: list[TileSide], graph: TileGraph, offset: tuple[int, int], side: TileSide) -> None:
    s_i = graph.starting_position[0]
    s_j = graph.starting_position[1]
    neighbouring_tile = graph.tiles[s_i + offset[0]][s_j + offset[1]]

    if isinstance(neighbouring_tile, PipeTile) and neighbouring_tile.has(side): connectors.append(side.get_opposite())

def _populate_starting_tile(graph: TileGraph) -> None:
    s_i = graph.starting_position[0]
    s_j = graph.starting_position[1]
    connectors = []
    _update_connectors(connectors, graph, (-1, 0), SOUTH)
    _update_connectors(connectors, graph, (1, 0), NORTH)
    _update_connectors(connectors, graph, (0, -1), EAST)
    _update_connectors(connectors, graph, (0, 1), WEST)

    graph.tiles[s_i][s_j] = PipeTile(connectors, (s_i, s_j))

def _get_pipe_tile(graph, i, j) -> PipeTile:
    starting_tile = graph.tiles[i][j]
    assert isinstance(starting_tile, PipeTile)
    return starting_tile

def _get_next_tile_in_loop(graph: TileGraph, current_tile: PipeTile, exiting_side: TileSide) -> tuple[PipeTile, TileSide]:
    entering_side = exiting_side.get_opposite()
    next_node_position = (current_tile.get_position()[0] + exiting_side.get_next_node_offset()[0],
                          current_tile.get_position()[1] + exiting_side.get_next_node_offset()[1])
    next_node = graph.tiles[next_node_position[0]][next_node_position[1]]
    assert isinstance(next_node, PipeTile)
    next_exiting_side = next_node.get_other_connector(entering_side)
    return next_node, next_exiting_side

def _count_tiles_in_loop(graph: TileGraph) -> int:
    starting_tile = _get_pipe_tile(graph, graph.starting_position[0], graph.starting_position[1])
    loop_length = 1

    (current_tile, exiting_side) = _get_next_tile_in_loop(graph, starting_tile, starting_tile.connectors[0])
    while current_tile is not starting_tile:
        loop_length += 1
        print(current_tile.get_position())
        (current_tile, exiting_side) = _get_next_tile_in_loop(graph, current_tile, exiting_side)

    return loop_length

class PipeMaze(solution.AbstractSolution):

    def solve_part_one(self, input_lines: list[str]) -> object:
        tile_graph: TileGraph = _parse_tile_graph(input_lines)
        _populate_starting_tile(tile_graph)
        loop_length: int = _count_tiles_in_loop(tile_graph)

        return loop_length // 2
