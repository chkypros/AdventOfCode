use crate::prelude::*;

pub struct CalorieCounting {

}

impl solution::Solution for CalorieCounting {

    fn solve_part_one(&self, input_content: &String) -> String {
        solve_for_top(input_content, 1)
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        solve_for_top(input_content, 3)
    }
}

fn solve_for_top(input_content: &String, _top_elves_to_find: i32) -> String {
    let max = input_content.split("\n\n")
        .filter(|s| !s.is_empty())
        .map(|x| calculate_elf_sum(x.to_string()))
        .max()
        .unwrap();
    max.to_string()
}

fn calculate_elf_sum(elf_content: String) -> u32 {
    elf_content.split('\n')
        .filter(|s| !s.is_empty())
        .map(|x| x.parse::<u32>().unwrap())
        .sum()
}
