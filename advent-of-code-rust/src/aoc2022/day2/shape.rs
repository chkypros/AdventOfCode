#[derive(Debug, PartialEq, Copy, Clone)]
pub enum Shape { ROCK, PAPER, SCISSORS }

impl Shape {
    pub fn score(self) -> i32 {
        match self {
            Shape::ROCK => 1,
            Shape::PAPER => 2,
            Shape::SCISSORS => 3,
        }
    }

    pub fn wins_over(&self) -> Shape {
        return match self {
            Shape::ROCK => Shape::SCISSORS,
            Shape::PAPER => Shape::ROCK,
            Shape::SCISSORS => Shape::PAPER,
        }
    }

    pub fn loses_to(&self) -> Shape {
        return match self {
            Shape::ROCK => Shape::PAPER,
            Shape::PAPER => Shape::SCISSORS,
            Shape::SCISSORS => Shape::ROCK,
        }
    }
}
