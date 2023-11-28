use std::collections::HashSet;

use itertools::Itertools;

use crate::prelude::*;

pub struct RucksackReorganization;

impl solution::Solution for RucksackReorganization {

    fn solve_part_one(&self, input_content: &String) -> String {
        return input_content.lines()
            .filter(|line| !line.is_empty())
            .map(separate_compartments)
            .map(find_common_item)
            .map(calculate_item_priority)
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines()
            .filter(|line| !line.is_empty())
            .chunks(3)
            .into_iter()
            .map(Iterator::collect)
            .map(|bags| find_badge_item(&bags))
            .map(calculate_item_priority)
            .sum::<i32>()
            .to_string()
    }
}

fn separate_compartments(input_content: &str) -> (HashSet<char>, HashSet<char>) {
    let half_index = input_content.len() / 2;
    let compartment_content = input_content.split_at(half_index);
    let items_compartment1 = get_characters(compartment_content.0);
    let items_compartment2 = get_characters(compartment_content.1);
    (items_compartment1, items_compartment2)
}

fn get_characters(content: &str) -> HashSet<char> {
    content.chars().collect()
}

fn find_common_item(compartments: (HashSet<char>, HashSet<char>)) -> char {
    let Some(common_item) = compartments.0
        .intersection(&compartments.1)
        .next() else {panic!("Could not find common item between {compartments:?}")};
    common_item.to_owned()
}

fn calculate_item_priority(item: char) -> i32 {
    match item {
        'a'..='z' => (item as i32) - ('a' as i32) + 1,
        'A'..='Z' => (item as i32) - ('A' as i32) + 27,
        _ => panic!("Got invalid item: {item}")
    }
}

fn find_badge_item(lines: &Vec<&str>) -> char {
    let Some(intersection) = lines.into_iter()
        .map(|l| l.chars().collect())
        .reduce(find_intersection)
        else { panic!("Could not find intersection for {lines:?}")};

    let Some(badge) = intersection.iter().next() else { panic!("Could not find intersection for {lines:?}")};
    badge.to_owned()
}

fn find_intersection(reduced: HashSet<char>, current: HashSet<char>) -> HashSet<char> {
    if reduced.is_empty() {
        current
    } else {
        reduced.intersection(&current).cloned().collect()
    }
}
