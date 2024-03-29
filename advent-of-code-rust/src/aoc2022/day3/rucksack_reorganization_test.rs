#[cfg(test)]
mod tests {
    use rstest::{fixture, rstest};
    use crate::prelude::*;
    use super::super::rucksack_reorganization::*;

    const PART_ONE_ANSWER: &'static str = "157";
    const PART_TWO_ANSWER: &'static str = "70";

    #[fixture]
    fn implementation() -> impl solution::Solution {
        RucksackReorganization {}
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
    fn test_single_item_in_each_compartment(implementation: impl solution::Solution) {
        let input = "aa".to_string();
        assert_eq!(implementation.solve_part_one(&input), "1")
    }

    #[rstest]
    fn test_single_uppercase_item_in_each_compartment(implementation: impl solution::Solution) {
        let input = "AA".to_string();
        assert_eq!(implementation.solve_part_one(&input), "27")
    }

    #[rstest]
    fn test_multiple_item_in_each_compartment(implementation: impl solution::Solution) {
        let input = "aa\nBB".to_string();
        assert_eq!(implementation.solve_part_one(&input), "29")
    }
}
