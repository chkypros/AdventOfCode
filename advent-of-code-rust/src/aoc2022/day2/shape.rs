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

    pub fn beats(&self, other: &Shape) -> bool {
        match (&self, other) {
            (Shape::ROCK, Shape::SCISSORS)
                | (Shape::PAPER, Shape::ROCK)
                | (Shape::SCISSORS, Shape::PAPER) => true,
            _ => false
        }
    }
}
