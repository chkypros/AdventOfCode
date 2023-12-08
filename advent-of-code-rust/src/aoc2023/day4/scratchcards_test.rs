#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::scratchcards::*;

    const PART_ONE_ANSWER: &'static str = "13";
    const PART_TWO_ANSWER: &'static str = "answer2";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        Scratchcards {}
    }

    #[fixture]
    fn sample_input_content() -> String {
        utils::get_labelled_input(file!(), "-sample")
    }

    #[rstest]
    fn test_solve(implementation: impl solution::Solution) {
        solution_test::solve_all(file!(), implementation);
    }

    #[rstest]
    fn test_solve_part_one(implementation: impl solution::Solution, sample_input_content: String) {
        assert_eq!(implementation.solve_part_one(&sample_input_content), PART_ONE_ANSWER)
    }

    #[rstest]
    fn test_solve_part_two(implementation: impl solution::Solution, sample_input_content: String) {
        assert_eq!(implementation.solve_part_two(&sample_input_content), PART_TWO_ANSWER)
    }

    #[rstest]
    fn test_p1_no_winning(implementation: impl solution::Solution) {
        let input = "Card 1: 1 2 | 3 4".to_string();
        assert_eq!(implementation.solve_part_one(&input), "0")
    }
}
