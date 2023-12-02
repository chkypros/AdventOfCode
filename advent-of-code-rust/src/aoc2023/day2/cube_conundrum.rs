use crate::aoc2023::day2::game::Game;
use crate::prelude::*;

pub struct CubeConundrum;

impl solution::Solution for CubeConundrum {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines()
            .map(parse_input)
            .filter(Game::is_game_possible)
            .map(|game: Game| game.id)
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}

fn parse_input(line: &str) -> Game {
    let (game_desc, reveals_desc) = line.split_once(": ").expect("Should have colon");
    let (_, game_id_desc) = game_desc.split_once(" ").expect("Should have space");
    let game_id = game_id_desc.parse::<i32>().expect("Should be a number");

    let reveals = reveals_desc.split("; ")
        .map(|reveal_desc| reveal_desc.into())
        .collect();

    Game { id: game_id, reveals }
}
