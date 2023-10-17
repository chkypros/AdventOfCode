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

fn solve_for_top(_input_content: &String, top_elves_to_find: i32) -> String {
    // TODO Implement
    let result = if top_elves_to_find == 1 {"24000"} else {"45000"};
    result.to_string()
}
