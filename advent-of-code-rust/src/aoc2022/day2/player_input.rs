use crate::aoc2022::day2::shape::Shape;

#[derive(Debug, Copy, Clone)]
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
            _ => panic!("Invalid player input: {value}")
        }
    }
}
