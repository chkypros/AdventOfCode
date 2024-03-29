use crate::prelude::*;

pub struct Template;

impl solution::Solution for Template {

    fn solve_part_one(&self, input_content: &String) -> String {
        input_content.lines().count().to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}
