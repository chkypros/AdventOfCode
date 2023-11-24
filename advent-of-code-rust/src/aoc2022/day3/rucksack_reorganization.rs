use std::collections::HashSet;
use crate::prelude::*;

pub struct RucksackReorganization {

}

impl solution::Solution for RucksackReorganization {

    fn solve_part_one(&self, input_content: &String) -> String {
        return input_content.split("\n")
            .map(separate_compartments)
            .map(find_common_item)
            .map(calculate_item_priority)
            .sum::<i32>()
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().next().unwrap().to_string()
    }
}

fn separate_compartments(input_content: &str) -> (HashSet<char>, HashSet<char>) {
    let all_items_content:Vec<char> = input_content.chars().collect();
    let half_index = all_items_content.len() / 2;
    let items_compartment1 = all_items_content.iter().take(half_index).map(&char::to_owned).collect();
    let items_compartment2 = all_items_content.iter().skip(half_index).map(&char::to_owned).collect();
    (items_compartment1, items_compartment2)
}

fn find_common_item(_compartments: (HashSet<char>, HashSet<char>)) -> char {
    todo!()
}

fn calculate_item_priority(_item: char) -> i32 {
    todo!()
}
