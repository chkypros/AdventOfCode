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

fn solve_for_top(input_content: &String, top_elves_to_find: usize) -> String {
    let mut calories_per_elf = input_content
        .split("\n\n")
        .filter(|s| !s.is_empty())
        .map(calculate_elf_sum)
        .collect::<Vec<u32>>();

    calories_per_elf.sort_by(|a, b| b.cmp(a));
    calories_per_elf
        .iter()
        .take(top_elves_to_find)
        .sum::<u32>()
        .to_string()
}

fn calculate_elf_sum(elf_content: &str) -> u32 {
    elf_content.split('\n')
        .filter(|s| !s.is_empty())
        .map(|x| x.parse::<u32>().unwrap())
        .sum()
}
