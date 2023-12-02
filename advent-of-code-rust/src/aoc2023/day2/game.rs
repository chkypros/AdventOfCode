use std::collections::LinkedList;
use crate::aoc2023::day2::reveal::Reveal;

#[derive(Debug)]
pub struct Game {
    pub id: i32,
    pub reveals: LinkedList<Reveal>
}

impl Game {
    pub fn is_game_possible(self: &Self) -> bool {
        self.reveals.iter().all(Reveal::is_reveal_possible)
    }
}
