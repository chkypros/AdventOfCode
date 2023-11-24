use crate::aoc2022::day2::opponent_input::OpponentInput;
use crate::aoc2022::day2::player_input::PlayerInput;
use crate::aoc2022::day2::shape::Shape;
use crate::prelude::*;

pub struct RockPaperScissors {

}

impl solution::Solution for RockPaperScissors {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines()
            .filter(|line| !line.is_empty())
            .map(parse_pair)
            .map(convert_to_shapes_pair)
            .map(evaluate_score)
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines()
            .filter(|line| !line.is_empty())
            .map(calculate_pair)
            .map(evaluate_score)
            .sum::<i32>()
            .to_string()
    }
}

fn parse_pair(pair_content: &str) -> (OpponentInput, PlayerInput) {
    let Some((part1, part2)) = pair_content.split_once(" ") else { panic!("Invalid pair: {pair_content}") };
    (OpponentInput::from(part1), PlayerInput::from(part2))
}

fn convert_to_shapes_pair(pair_input: (OpponentInput, PlayerInput)) -> (Shape, Shape) {
    let opponent_shape: Shape = pair_input.0.into();
    let player_shape: Shape = pair_input.1.into();
    (opponent_shape, player_shape)
}

fn calculate_pair(pair_content: &str) -> (Shape, Shape) {
    let Some((part1, part2)) = pair_content.split_once(" ") else { panic!("Invalid pair: {pair_content}") };
    let opponent_shape: Shape = OpponentInput::from(part1).into();

    let player_shape: Shape = match part2 {
        "X" => opponent_shape.wins_over(),
        "Y" => opponent_shape,
        "Z" => opponent_shape.loses_to(),
        _ => panic!("Invalid outcome: {part2}"),
    };

    (opponent_shape, player_shape)
}

fn evaluate_score(shape_pair: (Shape, Shape)) -> i32 {
    outcome_score(shape_pair) + shape_pair.1.score()
}

fn outcome_score(shape_pair: (Shape, Shape)) -> i32 {
    let opponent_shape: &Shape = &shape_pair.0;
    let player_shape: &Shape = &shape_pair.1;

    if opponent_shape == player_shape {
        3
    } else if &opponent_shape.wins_over() == player_shape {
        0
    } else {
        6
    }
}
