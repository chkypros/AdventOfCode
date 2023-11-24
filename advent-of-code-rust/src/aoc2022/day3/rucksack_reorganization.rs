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

fn separate_compartments(compartments: &str) -> (HashSet<&char>, HashSet<&char>) {
    let items:Vec<char> = compartments.chars().collect();
    let half_index = items.len() / 2;
    let items = items.split_at(half_index);
    let items_compartment1 = HashSet::from_iter(items.0.iter());
    let items_compartment2 = HashSet::from_iter(items.1.iter());
    (items_compartment1, items_compartment2)
}

fn find_common_item<'a>(_compartments: (HashSet<&'a char>, HashSet<&'a char>)) -> &'a char {
    todo!()
}

fn calculate_item_priority(_item: &char) -> i32 {
    todo!()
}
