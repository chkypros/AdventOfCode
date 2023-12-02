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
        input_content.lines()
            .map(parse_input)
            .map(find_min_set)
            .map(calculate_power)
            .sum::<i32>()
            .to_string()
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

fn find_min_set(game: Game) -> (i32, i32, i32) {
    let reveals = game.reveals;
    let max_reds = reveals.iter().map(|reveal| reveal.reds).max().expect("Should have reds");
    let max_greens = reveals.iter().map(|reveal| reveal.greens).max().expect("Should have greens");
    let max_blues = reveals.iter().map(|reveal| reveal.blues).max().expect("Should have blues");

    (max_reds, max_greens, max_blues)
}

fn calculate_power((reds, greens, blues): (i32, i32, i32)) -> i32 {
    reds * greens * blues
}
