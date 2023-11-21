use crate::prelude::*;

pub struct RockPaperScissors {

}

impl solution::Solution for RockPaperScissors {

    fn solve_part_one(&self, input_content: &String) -> String {
        "8".to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().next().unwrap().to_string()
    }
}
