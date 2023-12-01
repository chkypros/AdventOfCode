#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::trebuchet::*;

    const PART_ONE_ANSWER: &'static str = "142";
    const PART_TWO_ANSWER: &'static str = "answer2";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        Trebuchet {}
    }

    #[fixture]
    fn sample1_input_content() -> String {
        utils::get_labelled_input(file!(), "-sample1")
    }

    #[fixture]
    fn sample2_input_content() -> String {
        utils::get_labelled_input(file!(), "-sample2")
    }

    #[rstest]
    fn test_solve(implementation: impl solution::Solution) {
        solution_test::solve_all(file!(), implementation);
    }

    #[rstest]
    fn test_solve_part_one(implementation: impl solution::Solution, sample1_input_content: String) {
        assert_eq!(implementation.solve_part_one(&sample1_input_content), PART_ONE_ANSWER)
    }

    #[rstest]
    fn test_solve_part_two(implementation: impl solution::Solution, sample2_input_content: String) {
        assert_eq!(implementation.solve_part_two(&sample2_input_content), PART_TWO_ANSWER)
    }

    #[rstest]
    fn test_p1_two_digits(implementation: impl solution::Solution) {
        let input = "1abc2".to_string();
        assert_eq!(implementation.solve_part_one(&input), "12")
    }

    #[rstest]
    fn test_p1_single_digit(implementation: impl solution::Solution) {
        let input = "ab1cd".to_string();
        assert_eq!(implementation.solve_part_one(&input), "11")
    }

    #[rstest]
    fn test_p1_multiple_entries(implementation: impl solution::Solution) {
        let input = "1abc2\nab1cd".to_string();
        assert_eq!(implementation.solve_part_one(&input), "23")
    }
}
