use std::collections::{HashSet, VecDeque};

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
        let mut look_ahead_instances: VecDeque<usize> = VecDeque::new();

        input_content.lines()
            .filter(|s| !s.is_empty())
            .map(break_card_line)
            .map(find_winning_numbers)
            .map(|numbers| numbers.len())
            .fold(0, |acc, numbers| acc + factor_in_next_numbers(numbers, &mut look_ahead_instances))
            .to_string()
    }
}

fn break_card_line(line: &str) -> (u32, HashSet<u32>, HashSet<u32>) {
    let (card_desc, numbers_desc) = line.split_once(": ").expect("Should have colon delimiter");
    let card_no: u32 = card_desc.split_whitespace()
        .skip(1)
        .fold("", |_, b| b)
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
        (2u32).pow(count - 1)
    }
}

fn factor_in_next_numbers(numbers: usize, look_ahead_instances: &mut VecDeque<usize>) -> usize {
    let instances = 1 + look_ahead_instances.pop_front().unwrap_or_else(|| 0);

    if look_ahead_instances.len() < numbers {
        look_ahead_instances.resize(numbers, 0);
    }

    for i in 0..numbers {
        look_ahead_instances[i] = instances + look_ahead_instances[i];
    }

    instances
}
