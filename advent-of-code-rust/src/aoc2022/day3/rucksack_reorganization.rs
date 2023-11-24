use std::collections::HashSet;
use crate::prelude::*;

pub struct RucksackReorganization {

}

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

fn find_common_item(compartments: (HashSet<char>, HashSet<char>)) -> char {
    let mut intersection = compartments.0.intersection(&compartments.1);
    let Some(item) = intersection.next()
        else {
            panic!("Could not find intersection")
        };
    item.to_owned()
}

fn calculate_item_priority(item: char) -> i32 {
    return match item {
        'a'..='z' => 1 + (item as i32 - 'a' as i32),
        'A'..='Z' => 27 + (item as i32 - 'A' as i32),
        _ => panic!("Invalid item found!")
    }
}
