use crate::prelude::*;

pub trait Solution {
    fn solve(&self);

    fn solve_part_one(&self, _input_content: &String) -> String { "N/A".to_string() }

    fn solve_part_two(&self, _input_content: &String) -> String { "N/A".to_string() }

    fn solve_for_file(&self, input_file: &str) {
        let input_content = get_input(input_file, "");

        let part_one_answer = self.solve_part_one(&input_content);
        let part_two_answer = self.solve_part_two(&input_content);

        println!("Part One Answer = {part_one_answer}");
        println!("Part Two Answer = {part_two_answer}");
    }
}
