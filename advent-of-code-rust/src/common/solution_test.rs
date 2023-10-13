use crate::prelude::*;

pub fn solve_all(filepath: &str, solution: impl Solution) {
    let input_content = get_input(filepath);

    let part_one_answer = solution.solve_part_one(&input_content);
    let part_two_answer = solution.solve_part_two(&input_content);

    println!("Part One Answer = {part_one_answer}");
    println!("Part Two Answer = {part_two_answer}");
}
