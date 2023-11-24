use crate::aoc2022::day2::shape::Shape;

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
            _ => panic!("Invalid opponent input: {value}")
        }
    }
}
