use crate::prelude::*;

pub struct RockPaperScissors {

}

#[derive(Debug, PartialEq)]
pub enum Shape { ROCK, PAPER, SCISSORS }
impl Shape {
    fn beats(&self, other: Shape) -> bool {
        match (&self, other) {
            (Shape::ROCK, Shape::SCISSORS)
                | (Shape::PAPER, Shape::ROCK)
                | (Shape::SCISSORS, Shape::PAPER) => true,
            _ => false
        }
    }
}

#[derive(Debug, Copy, Clone)]
pub enum OpponentInput { A, B, C }
impl Into<Shape> for OpponentInput {
    fn into(self) -> Shape {
        match self {
            OpponentInput::A => Shape::ROCK,
            OpponentInput::B => Shape::PAPER,
            OpponentInput::C => Shape::SCISSORS,
        }
    }
}
impl From<&str> for OpponentInput {
    fn from(value: &str) -> Self {
        match value {
            "A" => OpponentInput::A,
            "B" => OpponentInput::B,
            "C" => OpponentInput::C,
            _ => panic!("Invalid opponent input: {value}")
        }
    }
}

#[derive(Debug, Copy, Clone)]
pub enum PlayerInput { X, Y, Z }
impl PlayerInput {
    fn score(self) -> i32 {
        let shape: Shape = self.into();
        match shape {
            Shape::ROCK => 1,
            Shape::PAPER => 2,
            Shape::SCISSORS => 3,
        }
    }
}
impl Into<Shape> for PlayerInput {
    fn into(self) -> Shape {
        match self {
            PlayerInput::X => Shape::ROCK,
            PlayerInput::Y => Shape::PAPER,
            PlayerInput::Z => Shape::SCISSORS,
        }
    }
}
impl From<&str> for PlayerInput {
    fn from(value: &str) -> Self {
        match value {
            "X" => PlayerInput::X,
            "Y" => PlayerInput::Y,
            "Z" => PlayerInput::Z,
            _ => panic!("Invalid player input: {value}")
        }
    }
}

impl solution::Solution for RockPaperScissors {

    fn solve_part_one(&self, input_content: &String) -> String {
        let pairs = input_content.split("\n")
            .filter(|line| !line.is_empty())
            .map(parse_pair)
            .map(evaluate_score)
            .collect::<Vec<i32>>();
        println!("Found {} pairs", pairs.len());
        pairs.iter()
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().next().unwrap().to_string()
    }
}

fn parse_pair(pair_content: &str) -> (OpponentInput, PlayerInput) {
    let Some((part1, part2)) = pair_content.split_once(" ") else { panic!("Invalid pair: {pair_content}") };
    (OpponentInput::from(part1), PlayerInput::from(part2))
}

fn evaluate_score(shape_pair: (OpponentInput, PlayerInput)) -> i32 {
    outcome_score(shape_pair) + shape_pair.1.score()
}

fn outcome_score(shape_pair: (OpponentInput, PlayerInput)) -> i32 {
    let opponent_shape: Shape = shape_pair.0.into();
    let player_shape: Shape = shape_pair.1.into();

    if opponent_shape == player_shape {
        3
    } else if opponent_shape.beats(player_shape) {
        0
    } else {
        6
    }
}
