#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::rock_paper_scissors::*;

    const PART_ONE_ANSWER: &'static str = "15";
    const PART_TWO_ANSWER: &'static str = "12";

    #[fixture]
    fn solution() -> impl Solution {
        RockPaperScissors {}
    }

    #[fixture]
    fn sample_input_content() -> String {
        get_labelled_input(file!(), "-sample")
    }

    #[rstest]
    fn test_solve(solution: impl Solution) {
        solve_all(file!(), solution);
    }

    #[rstest]
    fn test_solve_part_one(solution: impl Solution, sample_input_content: String) {
        assert_eq!(solution.solve_part_one(&sample_input_content), PART_ONE_ANSWER)
    }

    #[rstest]
    fn test_solve_part_two(solution: impl Solution, sample_input_content: String) {
        assert_eq!(solution.solve_part_two(&sample_input_content), PART_TWO_ANSWER)
    }
}
