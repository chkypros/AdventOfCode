use crate::prelude::*;

pub struct RockPaperScissors {

}

impl Solution for RockPaperScissors {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines().count().to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().next().unwrap().to_string()
    }
}
