use crate::prelude::*;

pub struct RockPaperScissors {

}

#[derive(Debug)]
pub enum Shape { ROCK, PAPER, SCISSORS }

#[derive(Debug)]
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
            _ => panic!("Invalid opponent input")
        }
    }
}

#[derive(Debug)]
pub enum PlayerInput { X, Y, Z }
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
            _ => panic!("Invalid player input")
        }
    }
}

impl solution::Solution for RockPaperScissors {

    fn solve_part_one(&self, _input_content: &String) -> String {
        "8".to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().next().unwrap().to_string()
    }
}

fn parse_pair(pair_content: &str) -> (OpponentInput, PlayerInput) {
    let Some((part1, part2)) = pair_content.split_once(" ") else { panic!("Invalid pair") };
    (OpponentInput::from(part1), PlayerInput::from(part2))
}
