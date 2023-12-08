use std::collections::HashSet;
use crate::prelude::*;

pub struct Scratchcards;

impl solution::Solution for Scratchcards {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines()
            .filter(|s| !s.is_empty())
            .map(break_card_line)
            .map(find_winning_numbers)
            .map(calculate_points)
            .sum::<u32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}

fn break_card_line(line: &str) -> (u32, HashSet<u32>, HashSet<u32>) {
    let (card_desc, numbers_desc) = line.split_once(": ").expect("Should have colon delimiter");
    let card_no: u32 = card_desc.split_whitespace()
        .skip(1)
        .fold("", |_,b| b)
        .parse().expect("Should be a number");

    let (winning_numbers_desc, card_numbers_desc) = numbers_desc.split_once("| ").expect("Should have a pipe delimiter");

    (card_no, parse_set(winning_numbers_desc), parse_set(card_numbers_desc))
}

fn parse_set(desc: &str) -> HashSet<u32> {
    desc.split_whitespace()
        .map(|nd| nd.parse::<u32>().expect("Should be a valid number"))
        .collect()
}

fn find_winning_numbers((_card_no, winning_numbers, card_numbers): (u32, HashSet<u32>, HashSet<u32>)) -> HashSet<u32> {
    winning_numbers.intersection(&card_numbers).map(u32::to_owned).collect()
}

fn calculate_points(winning_numbers: HashSet<u32>) -> u32 {
    if winning_numbers.is_empty() {
        0
    } else {
    let count = winning_numbers.len() as u32;
    (2 as u32).pow(count - 1)
        }
}
